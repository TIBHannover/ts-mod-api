package com.tib.ts.mod.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
@Getter
@Setter
@NoArgsConstructor
public class olsRestlUrl {
	
	/*private static String V2_TS = "https://api.terminology.tib.eu/api/v2";
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
	public static final String SEARCH_ENTITIES = V2_TS + "/entities";
	public static final String GET_CATALOG = "https://api.terminology.tib.eu/v3/api-docs";
	*/
	
	
	@Value("${app.ols4.v2.getAllOntologies}")
	private String allOntologies;
	@Value("${app.ols4.v2.getOntologyByOntologyId}")
	private String ontologyByOntologyId;
	@Value("${app.ols4.v2.getAllEntitiesByOntologyId}")
	private String allEntitiesByOntologyId;
	@Value("${app.ols4.v2.getEntityByOntologyIdAndIri}")
	private String entityByOntologyIdAndIri;
	@Value("${app.ols4.v2.getAllClassesByOntologyId}")
	private String allClassesByOntologyId;
	@Value("${app.ols4.v2.getClassesByOntologyIdAndIri}")
	private String classesByOntologyIdAndIri;
	@Value("${app.ols4.v2.getAllIndividualsByOntologyId}")
	private String allIndividualsByOntologyId;
	@Value("${app.ols4.v2.getIndividualsByOntologyIdAndIri}")
	private String individualsByOntologyIdAndIri;
	@Value("${app.ols4.v2.getAllPropertiesByOntologyId}")
	private String allPropertiesByOntologyId;
	@Value("${app.ols4.v2.getPropertiesByOntologyIdAndIri}")
	private String propertiesByOntologyIdAndIri;
	@Value("${app.ols4.v1.search}")
	private String search;
	@Value("${app.ols4.v2.searchEntities}")
	private String searchEntities;
	@Value("${app.ols4.docs}")
	private String catalog;
}