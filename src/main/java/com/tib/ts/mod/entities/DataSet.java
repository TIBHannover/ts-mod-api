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
public class DataSet extends Resource {
	
	@JsonProperty("accrualPeriodicity")
	private Object accrualPeriodicity;

	@JsonProperty("distribution")
	private Object distribution;

	@JsonProperty("spatial")
	private Object spatial;

	@JsonProperty("spatialResolutionInMeters")
	private Object spatialResolutionInMeters;

	@JsonProperty("temporal")
	private Object temporal;

	@JsonProperty("temporalResolution")
	private Object temporalResolution;

	@JsonProperty("wasGeneratedBy")
	private Object wasGeneratedBy;

}
