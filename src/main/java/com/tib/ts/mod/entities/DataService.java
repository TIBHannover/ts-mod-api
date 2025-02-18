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
public class DataService extends Resource{

	@JsonProperty("dcat:endpointDescription")
	private Map<String, String> endpointDescription;

	@JsonProperty("dcat:endpointURL")
	private Map<String, String> endpointURL;

	@JsonProperty("dcat:servesDataset")
	private DataSet servesDataset;
}
