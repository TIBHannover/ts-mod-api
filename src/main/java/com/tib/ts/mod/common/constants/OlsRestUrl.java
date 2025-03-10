package com.tib.ts.mod.common.constants;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class OlsRestUrl {
	
	private static final String TS = "https://api.terminology.tib.eu/api/v2";
	public static final String GET_ALL_ONTOLOGIES = TS + "/ontologies";
	public static final String GET_ONTOLOGY_BY_ONTOLOGY_ID = TS + "/ontologies/";
	public static final String GET_ALL_ENTITIES_BY_ONTOLOGY_ID = TS + "/ontologies/{0}/entities";
	
}
