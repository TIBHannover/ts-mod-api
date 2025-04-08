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
public class DataService extends Resource {

	@JsonProperty("endpointDescription")
	private Object endpointDescription;

	@JsonProperty("endpointURL")
	private Object endpointURL;

	@JsonProperty("servesDataset")
	private DataSet servesDataset;
}
