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
public class CatalogRecord {
	
	@JsonProperty("dcterms:conformsTo")
	private List<Map<String, String>> catalogRecordConformsTo;

	@JsonProperty("dcterms:description")
	private List<Map<String, String>> catalogRecordDescription;

	@JsonProperty("dcterms:issued")
	private List<Map<String, String>> catalogRecordIssued;

	@JsonProperty("dcterms:modified")
	private List<Map<String, String>> catalogRecordModified;

	@JsonProperty("dcterms:title")
	private List<Map<String, String>> catalogRecordTitle;

	@JsonProperty("foaf:primaryTopic")
	private Resource catalogRecordPrimaryTopic;

}
