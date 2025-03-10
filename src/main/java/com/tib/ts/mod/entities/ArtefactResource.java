package com.tib.ts.mod.entities;

import java.util.List;
import java.util.Map;

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
public class ArtefactResource {
	
	@JsonProperty("@type")
	private String type;
	
	@JsonProperty("@id")
	private String id;
	
	@JsonProperty("skos:prefLabel")
	private String prefLabel;

}
