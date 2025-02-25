package com.tib.ts.mod.common.mapper;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.mapper.MappingRule.MappingDetail;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class MetadataMapper {

	private static final Logger logger = LoggerFactory.getLogger(MetadataMapper.class);

	@Autowired
	private ArtefactAttribute config;

	public <T> T mapJsonToDto(String apiResponse, Class<T> dtoClass) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return constructDTO(apiResponse, dtoClass, objectMapper);
		}catch(Exception e) {
			logger.error(ErrorMessage.MAPPER_EXCEPTION_MSG, e.getMessage(), e);
			return null;
		}
	}

	private <T> T constructDTO(String apiResponse, Class<T> dtoClass, ObjectMapper objectMapper) throws Exception {
		try {
			T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();

			List<Field> fields = getAllFields(new ArrayList<>(), dtoClass);
			for (Field field : fields) {
				field.setAccessible(true);
				String attributeName = field.getName();

				logger.debug("Processing Attribute: {}", attributeName);

				List<MappingDetail> details = config.getModAttributes().get(attributeName);
				if (details == null)
					continue;
				details.sort(Comparator.comparingInt(MappingDetail::getPriority));
				for (MappingDetail detail : details) {
					Object value = null;
					try {
						value = JsonPath.read(apiResponse, detail.getJsonPath());
					} catch (Exception e) {
						logger.warn("Path not available in response: {}", detail.getJsonPath());
					}
					if (value == null)
						continue;
					try {
						if (isList(field)) {
							handleListField(dtoInstance, field, detail, value);
						}  else if (isDTO(field)) {
							Object nestedDto = constructDTO(apiResponse, field.getType(), objectMapper);
							field.set(dtoInstance, nestedDto);
						} else {
							field.set(dtoInstance, value);
						}
					} catch (IllegalAccessException e) {
						logger.error(ErrorMessage.MAPPER_FIELD_EXCEPTION_MSG, field.getName(), e);
					}

				}

			}
			return dtoInstance;
		} catch (Exception e) {
			logger.error(ErrorMessage.MAPPER_RESPONSE_CONSTRUCT_EXCEPTION_MSG, dtoClass.getSimpleName(), e);
			return null;
		}
	}

	/*
	 * private <T> void handleList(T dtoInstance, Field field, MappingDetail detail,
	 * List<String> value) { try { Map<String, Object> mapField = new HashMap<>();
	 * List<String> mapFieldValue = new ArrayList<String>(); List<String> keys =
	 * detail.getKeys(); for (String key : keys) { if (key.equalsIgnoreCase("@id"))
	 * { if (isNullorEmpty(value)) { mapField = null; break; } for (String s :
	 * value) { mapFieldValue.add(s); } mapField.put(key, mapFieldValue); } else if
	 * (key.equalsIgnoreCase("@type")) { mapField.put(key, detail.getType()); } }
	 * field.set(dtoInstance, mapField); } catch (IllegalArgumentException |
	 * IllegalAccessException e) {
	 * logger.error(ErrorMessage.MAPPER_HANDLE_LIST_EXCEPTION_MSG, field.getName(),
	 * e); } }
	 */
	
	private <T> void handleListField(T dtoInstance, Field field, MappingDetail detail, Object value) {
		try {
			List<Map<String, String>> listMapField = (List<Map<String, String>>) field.get(dtoInstance);

			if (listMapField == null) {
				listMapField = new ArrayList<>();
			}

			List<String> keys = detail.getKeys();
			List<String> valueList = (value instanceof List) ? (List<String>) value : List.of(value.toString());

			for (String str : valueList) {
				var mapField = new HashMap<String, String>();
				if (check_URL(str)) {
					mapField.put("@id", str);
				} else if (detail.getType().equalsIgnoreCase("rdfs:Literal")) {
					mapField.put("@value", str);
				} else {
					mapField.put("rdfs:label", str);
				}
				if(!detail.getType().equalsIgnoreCase("rdfs:resource")) {
					mapField.put("@type", detail.getType());
				}
				listMapField.add(mapField);
			}
			field.set(dtoInstance, listMapField);
		} catch (IllegalAccessException e) {
			logger.error(ErrorMessage.MAPPER_HANDLE_LIST_EXCEPTION_MSG, field.getName(), e);
		}
	}

	/*
	 * private <T> void handleMapField(T dtoInstance, Field field, MappingDetail
	 * detail, Object value) { try { Map<String, String> mapField = (Map<String,
	 * String>) field.get(dtoInstance); if (mapField == null) { mapField = new
	 * HashMap<String, String>(); } List<String> keys = detail.getKeys(); for
	 * (String key : keys) { if (key.equalsIgnoreCase("@id")) { if
	 * (isNullorEmpty(value)) { mapField = null; break; } mapField.put(key,
	 * value.toString()); } else if (key.equalsIgnoreCase("@type")) {
	 * mapField.put(key, detail.getType()); } }
	 * 
	 * field.set(dtoInstance, mapField); } catch (IllegalArgumentException |
	 * IllegalAccessException e) {
	 * logger.error(ErrorMessage.MAPPER_HANDLE_MAP_EXCEPTION_MSG, field.getName(),
	 * e); } }
	 */

	/*
	 * private boolean isNullorEmpty(Object value) { return value instanceof List &&
	 * ((List<?>) value).isEmpty(); }
	 */

	private boolean isList(Field field) {
		return List.class.isAssignableFrom(field.getType());
	}
	
	private boolean isDTO(Field field) {
		return !field.getType().isPrimitive() && !field.getType().getTypeName().equals("java.lang.Object");
	}

	/*
	 * private boolean isMap(Field field) { return
	 * Map.class.isAssignableFrom(field.getType()); }
	 */

	private List<Field> getAllFields(List<Field> fields, Class<?> classType) {
		if (classType.getSuperclass() != null) {
			getAllFields(fields, classType.getSuperclass());
		}
		fields.addAll(List.of(classType.getDeclaredFields()));
		return fields;
	}
	
	private boolean check_URL(String str) {
		try {
			new URL(str).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
