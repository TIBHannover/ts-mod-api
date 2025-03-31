package com.tib.ts.mod.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class OlsRestUrl {
	
	//@Value("${ols.service.url.v2:https://api.terminology.tib.eu/api/v2}")
	private static String V2_TS = "https://api.terminology.tib.eu/api/v2";
	//@Value("${ols.service.url.v1:https://api.terminology.tib.eu/api}")
	private static String V1_TS = "https://api.terminology.tib.eu/api";
	
	public static final String GET_ALL_ONTOLOGIES = V2_TS + "/ontologies";
	public static final String GET_ONTOLOGY_BY_ONTOLOGY_ID = V2_TS + "/ontologies/";
	public static final String GET_ALL_ENTITIES_BY_ONTOLOGY_ID = V2_TS + "/ontologies/{0}/entities";
	public static final String GET_ENTITY_BY_ONTOLOGY_ID_AND_IRI = V2_TS + "/ontologies/{0}/entities/{1}";
	public static final String GET_ALL_CLASSES_BY_ONTOLOGY_ID = V2_TS + "/ontologies/{0}/classes";
	public static final String GET_CLASSES_BY_ONTOLOGY_ID_AND_IRI = V2_TS + "/ontologies/{0}/classes/{1}";
	public static final String GET_ALL_INDIVIDUALS_BY_ONTOLOGY_ID = V2_TS + "/ontologies/{0}/individuals";
	public static final String GET_INDIVIDUALS_BY_ONTOLOGY_ID_AND_IRI = V2_TS + "/ontologies/{0}/individuals/{1}";
	public static final String GET_ALL_PROPERTIES_BY_ONTOLOGY_ID = V2_TS + "/ontologies/{0}/properties";
	public static final String GET_PROPERTIES_BY_ONTOLOGY_ID_AND_IRI = V2_TS + "/ontologies/{0}/properties/{1}";
	public static final String SEARCH = V1_TS + "/search";
	
}
