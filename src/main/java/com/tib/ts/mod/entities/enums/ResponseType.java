package com.tib.ts.mod.entities.enums;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public enum ResponseType {
	JSON_LD("application/json"),
	RDF_XML("application/xml"),
	TTL("text/turtle");
	
	private String type;
	
	ResponseType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
