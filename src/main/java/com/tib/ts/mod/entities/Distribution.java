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
public class Distribution {
	
	@JsonProperty("dcat:accessService")
	private DataService accessService;
	
	@JsonProperty("dcterms:accessRights")
    private List<Map<String, String>> accessRights;

    @JsonProperty("dcat:accessURL")
    private List<Map<String, String>> accessURL;

    @JsonProperty("dcat:byteSize")
    private List<Map<String, String>> byteSize;

    @JsonProperty("dcat:compressFormat")
    private List<Map<String, String>> compressFormat;

    @JsonProperty("dcterms:conformsTo")
    private List<Map<String, String>> conformsTo;

    @JsonProperty("dcterms:description")
    private List<Map<String, String>> description;

    @JsonProperty("dcat:downloadURL")
    private List<Map<String, String>> downloadURL;

    @JsonProperty("dcterms:format")
    private List<Map<String, String>> format;

    @JsonProperty("odrl:hasPolicy")
    private List<Map<String, String>> hasPolicy;

    @JsonProperty("dcterms:issued")
    private List<Map<String, String>> issued;

    @JsonProperty("dcterms:license")
    private List<Map<String, String>> license;

    @JsonProperty("dcat:mediaType")
    private List<Map<String, String>> mediaType;

    @JsonProperty("dcterms:modified")
    private List<Map<String, String>> modified;

    @JsonProperty("dcat:packageFormat")
    private List<Map<String, String>> packageFormat;

    @JsonProperty("dcterms:rights")
    private List<Map<String, String>> rights;

    @JsonProperty("dcat:spatialResolutionInMeters")
    private List<Map<String, String>> spatialResolutionInMeters;

    @JsonProperty("dcat:temporalResolution")
    private List<Map<String, String>> temporalResolution;

    @JsonProperty("dcterms:title")
    private List<Map<String, String>> title;

}
