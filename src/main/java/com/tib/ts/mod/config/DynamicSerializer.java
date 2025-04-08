package com.tib.ts.mod.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/


public class DynamicSerializer<T> extends StdSerializer<T> {
	
	private final List<String> defaultFields;
	
	public DynamicSerializer(Class<T> t) {
		super(t);
		this.defaultFields = null;
	}

	public DynamicSerializer(Class<T> t, List<String> defaultFields) {
		super(t);
		this.defaultFields = defaultFields;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		List<Field> fields = getAllFields(new ArrayList<>(), value.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object attributeValue = field.get(value);
				String attributeName = field.getName();
				
				if(attributeName.equalsIgnoreCase("uriregexpattern")) {
					System.out.println("Found");
				}

				if (attributeValue != null && isDTO(attributeValue)) {
					JsonRootName jsonProperty = attributeValue.getClass().getDeclaredAnnotation(JsonRootName.class);
					//attributeValue.getClass().getDeclaredFields()
					if (jsonProperty != null && !jsonProperty.value().isEmpty()) {
						attributeName = jsonProperty.value();
					}
					gen.writeFieldName(attributeName);
					serialize((T) attributeValue, gen, provider);
					
				} else if(attributeValue != null && isListOfDTO(attributeValue)) {
					JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

					if (jsonProperty != null && !jsonProperty.value().isEmpty()) {
						attributeName = jsonProperty.value();
					}
					gen.writeFieldName(attributeName);
					gen.writeStartArray();
					List<?> list = (List<?>) attributeValue;
					for(Object item : list) {
						serialize((T) item, gen, provider);
					}
					gen.writeEndArray();
				} else {

					JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

					if (jsonProperty != null && !jsonProperty.value().isEmpty()) {
						attributeName = jsonProperty.value();
					}

					if (attributeValue != null || defaultFields.contains(attributeName)) {
						gen.writeFieldName(attributeName);
						gen.writeObject(attributeValue);
					}
				}
			} catch (IllegalAccessException e) {
				throw new IOException("Error while accessing field: " + field.getName(), e);
			}
		}

		gen.writeEndObject();
	}
	
	private List<Field> getAllFields(List<Field> fields, Class<?> classType) {
		if (classType.getSuperclass() != null) {
			getAllFields(fields, classType.getSuperclass());
		}
		fields.addAll(List.of(classType.getDeclaredFields()));
		return fields;
	}
	
	private boolean isDTO(Object attributeValue) {
		return !attributeValue.getClass().isPrimitive() && !attributeValue.getClass().getName().startsWith("java.")
				&& attributeValue.getClass().getName().startsWith("com.tib.ts.mod.entities.");
	}
	
	private boolean isListOfDTO(Object attributeValue) {
		if(attributeValue instanceof List<?> ) {
			List<?> list = (List<?>) attributeValue;
			for(Object item : list) {
				if(item.getClass().getName().startsWith("com.tib.ts.mod.entities.")) {
					return true;
				}
				break;
			}
		}
		return false;
	}
}
