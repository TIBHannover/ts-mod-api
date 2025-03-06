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
public class CatalogRecord {
	
	@JsonProperty("dct:conformsTo")
	private List<Map<String, String>> conformsTo;

	@JsonProperty("dct:description")
	private List<Map<String, String>> description;

	@JsonProperty("dct:issued")
	private List<Map<String, String>> issued;

	@JsonProperty("dct:modified")
	private List<Map<String, String>> modified;

	@JsonProperty("dct:title")
	private List<Map<String, String>> title;

	@JsonProperty("foaf:primaryTopic")
	private Resource primaryTopic;

}
