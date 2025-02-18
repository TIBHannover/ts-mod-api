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
public class Catalog {
	
	@JsonProperty("dcat:dataset")
	private DataSet dataset;

	@JsonProperty("dct:hasPart")
	private Resource hasPart;

	@JsonProperty("foaf:homepage")
	private Map<String, String> homepage;

	@JsonProperty("dcat:record")
	private CatalogRecord record;

	@JsonProperty("dcat:resource")
	private Resource resource;

	@JsonProperty("dcat:service")
	private DataService service;

	@JsonProperty("dcat:themeTaxonomy")
	private Map<String, String> themeTaxonomy;


}
