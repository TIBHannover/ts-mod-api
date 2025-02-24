package com.tib.ts.mod.entities.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tib.ts.mod.entities.Context;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 * @param <T>
 * @param <T>
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class ResponseDTO<T> {
	
	@JsonProperty("@context")
	private Map<String, String> context;

	private T result;

}
