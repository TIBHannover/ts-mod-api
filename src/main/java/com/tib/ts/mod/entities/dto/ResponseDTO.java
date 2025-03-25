package com.tib.ts.mod.entities.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tib.ts.mod.entities.HydraView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("@type")
	private String type;
	
	@JsonProperty("totalItems")
	private Integer totalItems;
	
	@JsonProperty("itemsPerPage")
	private Integer itemsPerPage;

	@JsonProperty("member")
	private T jsonResult;
	
	@JsonProperty("@graph")
	private T otherFormatResult;
	
	@JsonProperty("view")
	private HydraView view;

}
