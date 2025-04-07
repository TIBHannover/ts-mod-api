package com.tib.ts.mod.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/


public class DynamicSerializer<T> extends StdSerializer<T> {
	
	private final List<String> defaultFields;
	
	public DynamicSerializer(Class<T> t, List<String> defaultFields) {
		super(t);
		this.defaultFields = defaultFields;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		
		for (Field field : value.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object attributeValue = field.get(value);
				String attributeName = field.getName();
				
				JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
				
				if (jsonProperty != null && !jsonProperty.value().isEmpty()) {
					attributeName = jsonProperty.value();
				}
				
				if (attributeValue != null || defaultFields.contains(attributeName)) {
					gen.writeFieldName(attributeName);
					gen.writeObject(attributeValue);
				}
			}catch(IllegalAccessException e) {
				throw new IOException("Error while accessing field: " + field.getName(), e);
			}
		}
		
		gen.writeEndObject();
	}
}
