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
public class DataSet extends Resource {
	
	@JsonProperty("dcterms:accrualPeriodicity")
	private List<Map<String, String>> accrualPeriodicity;

	@JsonProperty("dcat:distribution")
	private Distribution distribution;

	@JsonProperty("dcterms:spatial")
	private List<Map<String, String>> spatial;

	@JsonProperty("dcat:spatialResolutionInMeters")
	private List<Map<String, String>> spatialResolutionInMeters;

	@JsonProperty("dcterms:temporal")
	private List<Map<String, String>> temporal;

	@JsonProperty("dcat:temporalResolution")
	private List<Map<String, String>> temporalResolution;

	@JsonProperty("prov:wasGeneratedBy")
	private List<Map<String, String>> wasGeneratedBy;

}
