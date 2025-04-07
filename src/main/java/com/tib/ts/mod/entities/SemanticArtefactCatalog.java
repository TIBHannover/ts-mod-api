package com.tib.ts.mod.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
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
public class SemanticArtefactCatalog {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private String catalogId;
	
	@JsonProperty("@type")
	private String catalogType;
	
	@JsonProperty("accessRights")
	private String accessRights;
	
	@JsonProperty("accessURL")
	private String accessUrl;
	
	@JsonProperty("contactPoint")
	private String contactPoint;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("creator")
	private String creator;
	
	@JsonProperty("description")
	private String escription;

	@JsonProperty("identifier")
	private String identifier;

	@JsonProperty("keyword")
	private String keyword;

	@JsonProperty("landingPage")
	private String landingPage;
	
	@JsonProperty("license")
	private String license;
	
	@JsonProperty("modified")
	private String modified;
	
	@JsonProperty("rightsHolder")
	private String rightsHolder;
	
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("title")
	private String title;
	
	@JsonGetter("@type")
    public String getTypeAsString() {
        return catalogType != null ? catalogType : "mod:SemanticArtefactCatalog";
    }

}
