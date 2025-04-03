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
public class Catalog {
	
	@JsonProperty("dataset")
	private DataSet dataset;

	@JsonProperty("hasPart")
	private Resource hasPart;

	@JsonProperty("homepage")
	private List<Map<String, String>> homepage;

	@JsonProperty("record")
	private CatalogRecord record;

	@JsonProperty("resource")
	private Resource resource;

	@JsonProperty("service")
	private DataService service;

	@JsonProperty("themeTaxonomy")
	private List<Map<String, String>> themeTaxonomy;


}
