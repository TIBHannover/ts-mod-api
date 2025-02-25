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
	private List<Map<String, String>> accessRights;
	
	@JsonProperty("dcterms:conformsTo")
	private List<Map<String, String>> conformsTo;
	
	@JsonProperty("dcat:contactPoint")
	private List<Map<String, String>> contactPoint;
	
	@JsonProperty("dcterms:creator")
	private List<Map<String, String>> creator;
	
	@JsonProperty("dcterms:description")
	private List<Map<String, String>> description;
	
	@JsonProperty("odrl:hasPolicy")
	private List<Map<String, String>> hasPolicy;
	
	@JsonProperty("dcterms:identifier")
	private List<Map<String, String>> identifier;
	
	@JsonProperty("dcterms:isReferencedBy")
	private List<Map<String, String>> isReferencedBy;
	
	@JsonProperty("dcterms:issued")
	private List<Map<String, String>> issued;
	
	@JsonProperty("dcat:keyword")
	private List<Map<String, String>> keyword;
	
	@JsonProperty("dcat:landingPage")
	private List<Map<String, String>> landingPage;
	
	@JsonProperty("dcterms:language")
	private List<Map<String, String>> language;
	
	@JsonProperty("dcterms:license")
	private List<Map<String, String>> license;
	
	@JsonProperty("dcterms:modified")
	private List<Map<String, String>> modified;
	
	@JsonProperty("dcterms:publisher")
	private List<Map<String, String>> publisher;
	
	@JsonProperty("prov:qualifiedAttribution")
	private List<Map<String, String>> qualifiedAttribution;
	
	@JsonProperty("dcat:qualifiedRelation")
	private List<Map<String, String>> qualifiedRelation;
	
	@JsonProperty("dcterms:relation")
	private List<Map<String, String>> relation;
	
	@JsonProperty("dcterms:rights")
	private List<Map<String, String>> rights;
	
	@JsonProperty("dcterms:subject")
	private List<Map<String, String>> subject;
	
	@JsonProperty("dcterms:title")
	private List<Map<String, String>> title;
	
	@JsonProperty("dcterms:type")
	private List<Map<String, String>> type;

}
