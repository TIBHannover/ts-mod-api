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
public class DataService extends Resource{

	@JsonProperty("endpointDescription")
	private List<Map<String, String>> endpointDescription;

	@JsonProperty("endpointURL")
	private List<Map<String, String>> endpointURL;

	@JsonProperty("servesDataset")
	private DataSet servesDataset;
}
