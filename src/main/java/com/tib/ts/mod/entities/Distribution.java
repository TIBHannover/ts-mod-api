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
	
	@JsonProperty("accessService")
	private DataService accessService;
	
	@JsonProperty("accessRights")
    private List<Map<String, String>> accessRights;

    @JsonProperty("accessURL")
    private List<Map<String, String>> accessURL;

    @JsonProperty("byteSize")
    private List<Map<String, String>> byteSize;

    @JsonProperty("compressFormat")
    private List<Map<String, String>> compressFormat;

    @JsonProperty("conformsTo")
    private List<Map<String, String>> conformsTo;

    @JsonProperty("description")
    private List<Map<String, String>> description;

    @JsonProperty("downloadURL")
    private List<Map<String, String>> downloadURL;

    @JsonProperty("format")
    private List<Map<String, String>> format;

    @JsonProperty("hasPolicy")
    private List<Map<String, String>> hasPolicy;

    @JsonProperty("issued")
    private List<Map<String, String>> issued;

    @JsonProperty("license")
    private List<Map<String, String>> license;

    @JsonProperty("mediaType")
    private List<Map<String, String>> mediaType;

    @JsonProperty("modified")
    private List<Map<String, String>> modified;

    @JsonProperty("packageFormat")
    private List<Map<String, String>> packageFormat;

    @JsonProperty("rights")
    private List<Map<String, String>> rights;

    @JsonProperty("spatialResolutionInMeters")
    private List<Map<String, String>> spatialResolutionInMeters;

    @JsonProperty("temporalResolution")
    private List<Map<String, String>> temporalResolution;

    @JsonProperty("title")
    private List<Map<String, String>> title;

}
