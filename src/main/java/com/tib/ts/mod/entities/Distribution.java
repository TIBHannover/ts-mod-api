package com.tib.ts.mod.entities;

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
    private Object accessRights;

    @JsonProperty("accessURL")
    private Object accessURL;

    @JsonProperty("byteSize")
    private Object byteSize;

    @JsonProperty("compressFormat")
    private Object compressFormat;

    @JsonProperty("conformsTo")
    private Object conformsTo;

    @JsonProperty("description")
    private Object description;

    @JsonProperty("downloadURL")
    private Object downloadURL;

    @JsonProperty("format")
    private Object format;

    @JsonProperty("hasPolicy")
    private Object hasPolicy;

    @JsonProperty("issued")
    private Object issued;

    @JsonProperty("license")
    private Object license;

    @JsonProperty("mediaType")
    private Object mediaType;

    @JsonProperty("modified")
    private Object modified;

    @JsonProperty("packageFormat")
    private Object packageFormat;

    @JsonProperty("rights")
    private Object rights;

    @JsonProperty("spatialResolutionInMeters")
    private Object spatialResolutionInMeters;

    @JsonProperty("temporalResolution")
    private Object temporalResolution;

    @JsonProperty("title")
    private Object title;

}
