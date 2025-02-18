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
public class SemanticArtefactCatalogRecord extends CatalogRecord {

	@JsonProperty("dcterms:dateSubmitted")
	private Map<String, String> dateSubmitted;

	@JsonProperty("foaf:homepage")
	private Map<String, String> homepage;

	@JsonProperty("dcterms:created")
	private Map<String, String> created;

	@JsonProperty("mod:curatedBy")
	private Map<String, String> curatedBy;

	@JsonProperty("mod:curatedOn")
	private Map<String, String> curatedOn;

}
