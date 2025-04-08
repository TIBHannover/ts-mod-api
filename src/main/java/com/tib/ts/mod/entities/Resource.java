package com.tib.ts.mod.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Setter
@JsonSerialize(using = DynamicSerializer.class)
public class Resource {
	
	@JsonProperty("accessRights")
	private Object accessRights;
	
	@JsonProperty("conformsTo")
	private Object conformsTo;
	
	@JsonProperty("contactPoint")
	private Object contactPoint;
	
	@JsonProperty("creator")
	private Object creator;
	
	@JsonProperty("description")
	private Object description;
	
	@JsonProperty("hasPolicy")
	private Object hasPolicy;
	
	@JsonProperty("identifier")
	private Object identifier;
	
	@JsonProperty("isReferencedBy")
	private Object isReferencedBy;
	
	@JsonProperty("issued")
	private Object issued;
	
	@JsonProperty("keyword")
	private Object keyword;
	
	@JsonProperty("landingPage")
	private Object landingPage;
	
	@JsonProperty("language")
	private Object language;
	
	@JsonProperty("license")
	private Object license;
	
	@JsonProperty("modified")
	private Object modified;
	
	@JsonProperty("publisher")
	private Object publisher;
	
	@JsonProperty("qualifiedAttribution")
	private Object qualifiedAttribution;
	
	@JsonProperty("qualifiedRelation")
	private Object qualifiedRelation;
	
	@JsonProperty("relation")
	private Object relation;
	
	@JsonProperty("rights")
	private Object rights;
	
	@JsonProperty("subject")
	private Object subject;
	
	@JsonProperty("title")
	private Object title;
	
	@JsonProperty("type")
	private Object type;

}
