package com.tib.ts.mod.entities;

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
public class CatalogRecord {
	
	@JsonProperty("dct:conformsTo")
	private Map<String, String> conformsTo;

	@JsonProperty("dct:description")
	private Map<String, String> description;

	@JsonProperty("dct:issued")
	private Map<String, String> issued;

	@JsonProperty("dct:modified")
	private Map<String, String> modified;

	@JsonProperty("dct:title")
	private Map<String, String> title;

	@JsonProperty("foaf:primaryTopic")
	private Resource primaryTopic;

}
