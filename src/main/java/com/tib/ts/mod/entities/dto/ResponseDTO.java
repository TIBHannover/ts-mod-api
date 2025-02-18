package com.tib.ts.mod.entities.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tib.ts.mod.entities.Context;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class ResponseDTO {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("dcterms:accessURL")
	private Map<String, String> accessUrl;
	
	@JsonProperty("dcterms:conformsTo")
	private Map<String, String> conformsTo;

}
