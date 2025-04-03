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
	
	@JsonProperty("conformsTo")
	private List<Map<String, String>> catalogRecordConformsTo;

	@JsonProperty("description")
	private List<Map<String, String>> catalogRecordDescription;

	@JsonProperty("issued")
	private List<Map<String, String>> catalogRecordIssued;

	@JsonProperty("modified")
	private List<Map<String, String>> catalogRecordModified;

	@JsonProperty("title")
	private List<Map<String, String>> catalogRecordTitle;

	@JsonProperty("primaryTopic")
	private Resource catalogRecordPrimaryTopic;

}
