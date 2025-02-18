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
public class Distribution {
	
	@JsonProperty("dcat:accessService")
	private DataService accessService;
	
	@JsonProperty("dcterms:accessRights")
    private Map<String, String> accessRights;

    @JsonProperty("dcat:accessURL")
    private Map<String, String> accessURL;

    @JsonProperty("dcat:byteSize")
    private Map<String, String> byteSize;

    @JsonProperty("dcat:compressFormat")
    private Map<String, String> compressFormat;

    @JsonProperty("dcterms:conformsTo")
    private Map<String, String> conformsTo;

    @JsonProperty("dcterms:description")
    private Map<String, String> description;

    @JsonProperty("dcat:downloadURL")
    private Map<String, String> downloadURL;

    @JsonProperty("dcterms:format")
    private Map<String, String> format;

    @JsonProperty("odrl:hasPolicy")
    private Map<String, String> hasPolicy;

    @JsonProperty("dcterms:issued")
    private Map<String, String> issued;

    @JsonProperty("dcterms:license")
    private Map<String, String> license;

    @JsonProperty("dcat:mediaType")
    private Map<String, String> mediaType;

    @JsonProperty("dcterms:modified")
    private Map<String, String> modified;

    @JsonProperty("dcat:packageFormat")
    private Map<String, String> packageFormat;

    @JsonProperty("dcterms:rights")
    private Map<String, String> rights;

    @JsonProperty("dcat:spatialResolutionInMeters")
    private Map<String, String> spatialResolutionInMeters;

    @JsonProperty("dcat:temporalResolution")
    private Map<String, String> temporalResolution;

    @JsonProperty("dcterms:title")
    private Map<String, String> title;

}
