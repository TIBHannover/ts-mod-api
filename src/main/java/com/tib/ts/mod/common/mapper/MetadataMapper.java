package com.tib.ts.mod.common.mapper;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class MetadataMapper {
	
	private final Map<String, List<MappingRule>> config;

	public MetadataMapper(Map<String, List<MappingRule>> config) {
		this.config = config;
	}

	public <T> T mapJsonToDto(String apiResponse, Class<T> dtoClass) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(apiResponse);
		
		T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();
		
		
		return null;
		
	}
	
	
}
