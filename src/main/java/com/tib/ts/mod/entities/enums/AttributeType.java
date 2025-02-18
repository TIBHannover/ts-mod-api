package com.tib.ts.mod.entities.enums;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public enum AttributeType {
	ACCESS_URL("dcterms:RightStatement"),
	CONFORMS_TO("dcterms:Standard");
	
	private String type;
	
	AttributeType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
