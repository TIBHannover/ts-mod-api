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

public class Resource {
	
	@JsonProperty("dcterms:accessRights")
	private Map<String, Object> accessRights;
	
	@JsonProperty("dcterms:conformsTo")
	private Map<String, Object> conformsTo;
	
	@JsonProperty("dcat:contactPoint")
	private Map<String, Object> contactPoint;
	
	@JsonProperty("dcterms:creator")
	private Map<String, Object> creator;
	
	@JsonProperty("dcterms:description")
	private Map<String, Object> description;
	
	@JsonProperty("odrl:hasPolicy")
	private Map<String, Object> hasPolicy;
	
	@JsonProperty("dcterms:identifier")
	private Map<String, Object> identifier;
	
	@JsonProperty("dcterms:isReferencedBy")
	private Map<String, Object> isReferencedBy;
	
	@JsonProperty("dcterms:issued")
	private Map<String, Object> issued;
	
	@JsonProperty("dcat:keyword")
	private Map<String, Object> keyword;
	
	@JsonProperty("dcat:landingPage")
	private Map<String, Object> landingPage;
	
	@JsonProperty("dcterms:language")
	private Map<String, Object> language;
	
	@JsonProperty("dcterms:license")
	private Map<String, Object> license;
	
	@JsonProperty("dcterms:modified")
	private Map<String, Object> modified;
	
	@JsonProperty("dcterms:publisher")
	private Map<String, Object> publisher;
	
	@JsonProperty("prov:qualifiedAttribution")
	private Map<String, Object> qualifiedAttribution;
	
	@JsonProperty("dcat:qualifiedRelation")
	private Map<String, Object> qualifiedRelation;
	
	@JsonProperty("dcterms:relation")
	private Map<String, Object> relation;
	
	@JsonProperty("dcterms:rights")
	private Map<String, Object> rights;
	
	@JsonProperty("dcterms:subject")
	private Map<String, Object> subject;
	
	@JsonProperty("dcterms:title")
	private Map<String, Object> title;
	
	@JsonProperty("dcterms:type")
	private Map<String, Object> type;

}
