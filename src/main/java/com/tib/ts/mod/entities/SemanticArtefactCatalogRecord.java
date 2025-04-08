package com.tib.ts.mod.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tib.ts.mod.config.DynamicSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter@JsonSerialize(using = DynamicSerializer.class)
@JsonRootName(value = "mod:SemanticArtefactCatalogRecord")
public class SemanticArtefactCatalogRecord extends CatalogRecord {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactCatalogRecordId;
	
	@JsonProperty("@type")
	private Object semanticArtefactCatalogRecordType;

	@JsonProperty("dateSubmitted")
	private Object dateSubmitted;

	@JsonProperty("homepage")
	private Object homepage;

	@JsonProperty("created")
	private Object created;

	@JsonProperty("curatedBy")
	private Object curatedBy;

	@JsonProperty("curatedOn")
	private Object curatedOn;

}
