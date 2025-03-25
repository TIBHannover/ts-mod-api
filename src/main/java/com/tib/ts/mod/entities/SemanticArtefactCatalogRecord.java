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

	@JsonProperty("dcterms:dateSubmitted")
	private List<Map<String, String>> dateSubmitted;

	@JsonProperty("foaf:homepage")
	private List<Map<String, String>> homepage;

	@JsonProperty("dcterms:created")
	private List<Map<String, String>> created;

	@JsonProperty("mod:curatedBy")
	private List<Map<String, String>> curatedBy;

	@JsonProperty("mod:curatedOn")
	private List<Map<String, String>> curatedOn;

}
