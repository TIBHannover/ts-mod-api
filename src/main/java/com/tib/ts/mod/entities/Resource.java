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
	
	@JsonProperty("accessRights")
	private List<Map<String, String>> accessRights;
	
	@JsonProperty("conformsTo")
	private List<Map<String, String>> conformsTo;
	
	@JsonProperty("contactPoint")
	private List<Map<String, String>> contactPoint;
	
	@JsonProperty("creator")
	private List<Map<String, String>> creator;
	
	@JsonProperty("description")
	private List<Map<String, String>> description;
	
	@JsonProperty("hasPolicy")
	private List<Map<String, String>> hasPolicy;
	
	@JsonProperty("identifier")
	private List<Map<String, String>> identifier;
	
	@JsonProperty("isReferencedBy")
	private List<Map<String, String>> isReferencedBy;
	
	@JsonProperty("issued")
	private List<Map<String, String>> issued;
	
	@JsonProperty("keyword")
	private List<Map<String, String>> keyword;
	
	@JsonProperty("landingPage")
	private List<Map<String, String>> landingPage;
	
	@JsonProperty("language")
	private List<Map<String, String>> language;
	
	@JsonProperty("license")
	private List<Map<String, String>> license;
	
	@JsonProperty("modified")
	private List<Map<String, String>> modified;
	
	@JsonProperty("publisher")
	private List<Map<String, String>> publisher;
	
	@JsonProperty("qualifiedAttribution")
	private List<Map<String, String>> qualifiedAttribution;
	
	@JsonProperty("qualifiedRelation")
	private List<Map<String, String>> qualifiedRelation;
	
	@JsonProperty("relation")
	private List<Map<String, String>> relation;
	
	@JsonProperty("rights")
	private List<Map<String, String>> rights;
	
	@JsonProperty("subject")
	private List<Map<String, String>> subject;
	
	@JsonProperty("title")
	private List<Map<String, String>> title;
	
	@JsonProperty("type")
	private List<Map<String, String>> type;

}
