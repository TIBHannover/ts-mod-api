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
public class SemanticArtefactCatalogRecord extends CatalogRecord {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactCatalogRecordId;
	
	@JsonProperty("@type")
	private Object semanticArtefactCatalogRecordType;

	@JsonProperty("dateSubmitted")
	private List<Map<String, String>> dateSubmitted;

	@JsonProperty("homepage")
	private List<Map<String, String>> homepage;

	@JsonProperty("created")
	private List<Map<String, String>> created;

	@JsonProperty("curatedBy")
	private List<Map<String, String>> curatedBy;

	@JsonProperty("curatedOn")
	private List<Map<String, String>> curatedOn;

}
