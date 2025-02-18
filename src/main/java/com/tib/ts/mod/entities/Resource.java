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

public class Resource {
	
	@JsonProperty("dcterms:accessRights")
	private Map<String, String> accessRights;
	
	@JsonProperty("dcterms:accessRights")
	private Map<String, String> conformsTo;
	
	@JsonProperty("dcat:contactPoint")
	private Map<String, String> contactPoint;
	
	@JsonProperty("dcterms:creator")
	private Map<String, String> creator;
	
	@JsonProperty("dcterms:description")
	private Map<String, String> description;
	
	@JsonProperty("odrl:hasPolicy")
	private Map<String, String> hasPolicy;
	
	@JsonProperty("dcterms:identifier")
	private Map<String, String> identifier;
	
	@JsonProperty("dcterms:isReferencedBy")
	private Map<String, String> isReferencedBy;
	
	@JsonProperty("dcterms:issued")
	private Map<String, String> issued;
	
	@JsonProperty("dcat:keyword")
	private Map<String, String> keyword;
	
	@JsonProperty("dcat:landingPage")
	private Map<String, String> landingPage;
	
	@JsonProperty("dcterms:language")
	private Map<String, String> language;
	
	@JsonProperty("dcterms:license")
	private Map<String, String> license;
	
	@JsonProperty("dcterms:modified")
	private Map<String, String> modified;
	
	@JsonProperty("dcterms:publisher")
	private Map<String, String> publisher;
	
	@JsonProperty("prov:qualifiedAttribution")
	private Map<String, String> qualifiedAttribution;
	
	@JsonProperty("dcat:qualifiedRelation")
	private Map<String, String> qualifiedRelation;
	
	@JsonProperty("dcterms:relation")
	private Map<String, String> relation;
	
	@JsonProperty("dcterms:rights")
	private Map<String, String> rights;
	
	@JsonProperty("dcterms:subject")
	private Map<String, String> subject;
	
	@JsonProperty("dcterms:title")
	private Map<String, String> title;
	
	@JsonProperty("dcterms:type")
	private Map<String, String> type;

}
