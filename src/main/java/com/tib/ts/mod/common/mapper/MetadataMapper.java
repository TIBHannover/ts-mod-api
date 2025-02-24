package com.tib.ts.mod.common.mapper;

import java.lang.reflect.Field;
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
	private MappingRule config;

	public <T> T mapJsonToDto(String apiResponse, Class<T> dtoClass) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		//JsonNode rootNode = objectMapper.readTree(apiResponse);

		return constructDTO(apiResponse, dtoClass, objectMapper);
	}

	private <T> T constructDTO(String apiResponse, Class<T> dtoClass, ObjectMapper objectMapper) throws Exception {

		T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();

		List<Field> fields = getAllFields(new ArrayList<>(), dtoClass);
		for (Field field : fields) {
			field.setAccessible(true);
			String attributeName = field.getName();
			logger.debug("Processing Attribute: {}", attributeName);
			
			if(attributeName.equalsIgnoreCase("semanticArtefactId")) {
				logger.info("expected attribute");
			}

			List<MappingDetail> details = config.getModAttributes().get(attributeName);
			if (details == null)
				continue;
			details.sort(Comparator.comparingInt(MappingDetail::getPriority));
			for (MappingDetail detail : details) {
				Object value = null;
				try {
					value = JsonPath.read(apiResponse, detail.getJsonPath());
				}catch (Exception e) {
					logger.info("Path not available in response: {}", detail.getJsonPath());
				}
				if (value == null)
					continue;
				/*if (isList(field)) {
					handleListField(dtoInstance, field, detail, value);
				} else if (isMap(field)) {
					handleMapField(dtoInstance, field, detail, value);
				} else if (isDTO(field)) {
					Object nestedDto = constructDTO(apiResponse, field.getType(), objectMapper);
					field.set(dtoInstance, nestedDto);
				} else {
					if(attributeName.equalsIgnoreCase("type"))
						field.set(dtoInstance, value);
					field.set(dtoInstance, value);
				}*/
				if(value instanceof List) {
					handleList(dtoInstance, field, detail, (List)value);
				}else if (isMap(field)) {
					handleMapField(dtoInstance, field, detail, value);
				} else if (isDTO(field)) {
					Object nestedDto = constructDTO(apiResponse, field.getType(), objectMapper);
					field.set(dtoInstance, nestedDto);
				} else {
					field.set(dtoInstance, value);
				}
				
			}

		}
		return dtoInstance;
	}

	private <T> void handleList(T dtoInstance, Field field, MappingDetail detail, List<String> value) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> mapField = new HashMap<>();
		List<String> mapFieldValue = new ArrayList<String>();
		List<String> keys = detail.getKeys();
		for (String key : keys) {
			if (key.equalsIgnoreCase("@id")) {
				if(isNullorEmpty(value)) {
					mapField = null;
					break;
				}
				for(String s : value) {
					mapFieldValue.add(s);
				}
				mapField.put(key, mapFieldValue);
			} else if (key.equalsIgnoreCase("@type")) {
				mapField.put(key, detail.getType());
			}
		}
		field.set(dtoInstance, mapField);
	}

	private <T> void handleMapField(T dtoInstance, Field field, MappingDetail detail, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, String> mapField = (Map<String, String>) field.get(dtoInstance);
		if (mapField == null) {
			mapField = new HashMap<String, String>();
		}
		List<String> keys = detail.getKeys();
		for (String key : keys) {
			if (key.equalsIgnoreCase("@id")) {
				if(isNullorEmpty(value)) {
					mapField = null;
					break;
				}
				mapField.put(key, value.toString());
			} else if (key.equalsIgnoreCase("@type")) {
				mapField.put(key, detail.getType());
			}
		}
		field.set(dtoInstance, mapField);
	}

	private boolean isNullorEmpty(Object value) {
		if(value != null) {
			if(value instanceof List) {
				return !(((List) value).size() > 0);
			}
		}
		return false;
	}

	private <T> void handleListField(T dtoInstance, Field field, MappingDetail detail, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		List<Map<String, String>> listMapField = (List<Map<String, String>>) field.get(dtoInstance);
		Map<String, String> mapField = new HashMap<String, String>();
		if (listMapField == null) {
			listMapField = new ArrayList<Map<String, String>>();
		}
		List<String> keys = detail.getKeys();
		for (String key : keys) {
			mapField = new HashMap<String, String>();
			if (key.equalsIgnoreCase("@id")) {
				mapField.put(key, value.toString());
			} else if (key.equalsIgnoreCase("@type")) {
				mapField.put(key, detail.getType());
			}
			listMapField.add(mapField);
		}
		field.set(dtoInstance, listMapField);
	}

	private boolean isDTO(Field field) {
		return !field.getType().isPrimitive() && field.getType().getTypeName() != "java.lang.Object";
	}

	private boolean isList(Field field) {
		return List.class.isAssignableFrom(field.getType());
	}

	private boolean isMap(Field field) {
		return Map.class.isAssignableFrom(field.getType());
	}

	private List<Field> getAllFields(List<Field> fields, Class<?> classType) {
		if (classType.getSuperclass() != null) {
			getAllFields(fields, classType.getSuperclass());
		}
		fields.addAll(List.of(classType.getDeclaredFields()));
		return fields;
	}

}
