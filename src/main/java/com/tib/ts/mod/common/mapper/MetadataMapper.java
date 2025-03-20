package com.tib.ts.mod.common.mapper;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private MappingRule config;
	
	private Set<String> processedClasses;
	
	public <T> T mapJsonToDto(String apiResponse, Class<T> dtoClass, MappingRule mergedConfigs) {

		this.config = mergedConfigs;
		
		this.processedClasses = new HashSet<String>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			long start = System.currentTimeMillis();
			T s = constructDTO(apiResponse, dtoClass, objectMapper);
			System.out.println("Time taken for mapping: " +(System.currentTimeMillis() - start));
			return s;
		}catch(Exception e) {
			logger.debug(ErrorMessage.MAPPER_EXCEPTION_MSG, e.getMessage(), e);
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
				
				if (isDTO(field)) {
					if (processedClasses.add(dtoClass.getName())) {
						Object nestedDto = constructDTO(apiResponse, field.getType(), objectMapper);
						field.set(dtoInstance, nestedDto);
					}
				}

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
						} else {
							field.set(dtoInstance, value);
						}
					} catch (IllegalAccessException e) {
						logger.error(ErrorMessage.MAPPER_FIELD_EXCEPTION_MSG, field.getName(), e);
					}
					// break the loop when the first priority assignment has value
					break;
				}
			}
			return dtoInstance;
		} catch (Exception e) {
			logger.debug(ErrorMessage.MAPPER_RESPONSE_CONSTRUCT_EXCEPTION_MSG, dtoClass.getSimpleName(), e);
			return null;
		}
	}
	
	private <T> void handleListField(T dtoInstance, Field field, MappingDetail detail, Object value) {
		try {
			List<Map<String, String>> listMapField = (List<Map<String, String>>) field.get(dtoInstance);

			if (listMapField == null) {
				listMapField = new ArrayList<>();
			}

			List<String> valueList = ((value instanceof List)) ? (List<String>) value : List.of(value.toString());
			if (!valueList.isEmpty()) {
				for (String str : valueList) {
					var mapField = new HashMap<String, String>();
					
					switch (detail.getType().toLowerCase()) {
						case "rdfs:Literal" -> mapField.put("@value", str);
						case "skos:Concept" -> mapField.put("skos:prefLabel", str);
						default -> mapField.put(check_URL(str) ? "@id" : "@value", str);
					}
					
					if (!detail.getType().equalsIgnoreCase("rdfs:resource") ||
						!detail.getType().equalsIgnoreCase("rdfs:Literal")) {
						mapField.put("@type", detail.getType());
					}
					
					listMapField.add(mapField);
				}
				field.set(dtoInstance, listMapField);
			}
		} catch (IllegalAccessException e) {
			logger.debug(ErrorMessage.MAPPER_HANDLE_LIST_EXCEPTION_MSG, field.getName(), e);
		}
	}

	private boolean isList(Field field) {
		return List.class.isAssignableFrom(field.getType());
	}
	
	private boolean isDTO(Field field) {
		return !field.getType().isPrimitive() && !field.getType().getName().startsWith("java.");
	}

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
