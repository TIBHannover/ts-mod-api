package com.tib.ts.mod.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class HydraView {

	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("@type")
	private String type = "hydra: PartialCollecitonView";
	
	@JsonProperty("first")
	private String first;
	
	@JsonProperty("last")
	private String last;
	
	@JsonProperty("next")
	private String next;
	
	@JsonProperty("previous")
	private String previous;
}
