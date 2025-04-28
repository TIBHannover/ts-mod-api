package com.tib.ts.mod.common.constants;

import org.springframework.stereotype.Component;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class AttributeFile {
	
	public static final String YAML = ".yaml";
	
	public static final String SEMANTIC_ARTEFACT = "semanticArtefact" + YAML;
	public static final String SEMANTIC_ARTEFACT_DISTRIBUTION = "semanticArtefactDistribution" + YAML;
	public static final String SEMANTIC_ARTEFACT_CATALOG_RECORD = "semanticArtefactCatalogRecord" + YAML;
	public static final String SEMANTIC_ARTEFACT_CATALOG = "semanticArtefactCatalog" + YAML;
	
	public static final String DCAT_RESOURCE = "dcatResource" + YAML;
	public static final String DCAT_DATA_SERVICE = "dcatDataService" + YAML;
	public static final String DCAT_CATALOG_RECORD = "dcatCatalogRecord" + YAML;
	public static final String DCAT_DISTRIBUTION = "dcatDistribution" + YAML;
	public static final String DCAT_DATA_SET = "dcatDataSet" + YAML;
}
