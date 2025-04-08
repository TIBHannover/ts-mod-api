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
public class CatalogRecord {
	
	@JsonProperty("conformsTo")
	private Object catalogRecordConformsTo;

	@JsonProperty("description")
	private Object catalogRecordDescription;

	@JsonProperty("issued")
	private Object catalogRecordIssued;

	@JsonProperty("modified")
	private Object catalogRecordModified;

	@JsonProperty("title")
	private Object catalogRecordTitle;

	@JsonProperty("primaryTopic")
	private Resource catalogRecordPrimaryTopic;

}
