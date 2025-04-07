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
import com.tib.ts.mod.entities.Context;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class MetadataMapper {

	private static final Logger logger = LoggerFactory.getLogger(MetadataMapper.class);

	private MappingRule config;
	
	//private Set<String> processedClasses;
	
	public <T> T mapJsonToDto(String apiResponse, Class<T> dtoClass, MappingRule mergedConfigs) {

		this.config = mergedConfigs;
		
		//this.processedClasses = new HashSet<String>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return constructDTO(apiResponse, dtoClass, objectMapper, new HashSet<String>());
		}catch(Exception e) {
			logger.debug(ErrorMessage.MAPPER_EXCEPTION_MSG, e.getMessage(), e);
			return null;
		}
	}

	private <T> T constructDTO(String apiResponse, Class<T> dtoClass, ObjectMapper objectMapper, Set<String> processedClasses) throws Exception {
		try {
			T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();

			List<Field> fields = getAllFields(new ArrayList<>(), dtoClass);
			for (Field field : fields) {
				field.setAccessible(true);
				String attributeName = field.getName();

				logger.debug("Processing Attribute: {}", attributeName);			
				
				if(attributeName.equalsIgnoreCase("numberOfClasses")) {
					logger.info("Found");
				}
				
				if (isDTO(field)) {
					if (processedClasses.add(dtoClass.getName())) {
						Object nestedDto = constructDTO(apiResponse, field.getType(), objectMapper, processedClasses);
						field.set(dtoInstance, nestedDto);
					}
				}
				
				//System.out.println("processedClasses in : " + dtoClass.getName() + ": " + processedClasses);

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
							Context.addContext(field.getName(), detail.getContextReference());
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
			logger.info(ErrorMessage.MAPPER_RESPONSE_CONSTRUCT_EXCEPTION_MSG, dtoClass.getSimpleName(), e);
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
					String type = detail.getType();
					
					if (type != null) {
						switch (detail.getType()) {
							case "rdfs:Literal" -> mapField.put("@value", str);
							case "skos:Concept" -> mapField.put("skos:prefLabel", str);
							default -> mapField.put(check_URL(str) ? "@id" : "@value", str);
						}

						if (!detail.getType().equalsIgnoreCase("rdfs:resource")
								|| !detail.getType().equalsIgnoreCase("rdfs:Literal")) {
							mapField.put("@type", detail.getType());
						}
					}else {
						mapField.put(check_URL(str) ? "@id" : "@value", str);
					}
					
					listMapField.add(mapField);
				}
				Context.addContext(field.getName(), detail.getContextReference());
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
