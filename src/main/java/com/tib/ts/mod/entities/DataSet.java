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
	
	@JsonProperty("accrualPeriodicity")
	private List<Map<String, String>> accrualPeriodicity;

	@JsonProperty("distribution")
	private Distribution distribution;

	@JsonProperty("spatial")
	private List<Map<String, String>> spatial;

	@JsonProperty("spatialResolutionInMeters")
	private List<Map<String, String>> spatialResolutionInMeters;

	@JsonProperty("temporal")
	private List<Map<String, String>> temporal;

	@JsonProperty("temporalResolution")
	private List<Map<String, String>> temporalResolution;

	@JsonProperty("wasGeneratedBy")
	private List<Map<String, String>> wasGeneratedBy;

}
