package com.tib.ts.mod.common.mapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.JsonPath;
import com.tib.ts.mod.entities.ArtefactResource;
import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class ArtefactResourceMapper {

	private static final Logger logger = LoggerFactory.getLogger(ArtefactResourceMapper.class);
	
	public ArtefactResource mapJsonToDTO(RequestDTO request, String apiResponse) {
		
		logger.info("In map response json for artefact resource");
		
		ArtefactResource artefactResource = new ArtefactResource();
		
		String iri = readFromJsonPath(apiResponse, "$.iri");
		
		var prefLabel = readFromJsonPath(apiResponse, "$.['http://www.w3.org/2004/02/skos/core#prefLabel']");
		
		if (prefLabel == null) {
			prefLabel = readFromJsonPath(apiResponse, "$.label");
		}
		
		var entityType = readFromJsonPath(apiResponse, "$.type");
		
		var skosType = readFromJsonPath(apiResponse, "$.['http://www.w3.org/1999/02/22-rdf-syntax-ns#type']");
		
		var skosCollection = readFromJsonPath(apiResponse, "$.['http://www.w3.org/2000/01/rdf-schema#domain']");
		
		if (entityType == null)
			return null;
		
		List<String> entityTypeList = ((entityType instanceof List)) ? (List<String>) entityType : List.of(entityType.toString());
		List<String> skosTypeList = ((skosType instanceof List)) ? (List<String>) skosType : List.of(skosType.toString());
		List<String> skosCollectionList = ((skosCollection instanceof List)) ? (List<String>) skosCollection : List.of(skosCollection != null ? skosCollection.toString() : "");
		List<String> prefLabelList = ((prefLabel instanceof List)) ? (List<String>) prefLabel : List.of(prefLabel.toString());
		
		switch (request.getResourceType()) {
			case CLASS:  {
				if (entityTypeList.contains("class")) {
					return buildArtefactResource(artefactResource, "owl:Class", iri, null);
				}
				return null;
			}
			case PROPERTY:  {
				if (entityTypeList.contains("property")) {
					return buildArtefactResource(artefactResource, "rdfs:Property", iri, null);
				}
				return null;
			}
			case INDIVIDUAL:  {
				if (entityTypeList.contains("individual")) {
					return buildArtefactResource(artefactResource, "owl:NamedIndividual", iri, null);
				}
				return null;
			}
			case COLLECTION:  {
				if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#Collection")) {
					return buildArtefactResource(artefactResource, "skos:Collection", null, prefLabelList.get(0));
				}
				return null;
			}
			case CONCEPT:  {
				if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#Concept")) {
					return buildArtefactResource(artefactResource, "skos:Concept", null, prefLabelList.get(0));
				}
				return null;
			}
			case SCHEME:  {
				if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#ConceptScheme")) {
					return buildArtefactResource(artefactResource, "skos:ConceptScheme", null, prefLabelList.get(0));
				}
				return null;
			}
			default: determineType(artefactResource, skosTypeList, entityTypeList, iri);
		};
		
		return artefactResource;
	}
	
	private ArtefactResource determineType(ArtefactResource artefactResource, List<String> skosTypeList,
			List<String> entityTypeList, String value) {
		
		if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#ConceptScheme")) {
			return buildArtefactResource(artefactResource, "skos:ConceptScheme", null, value);
		}else if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#Concept")) {
			return buildArtefactResource(artefactResource, "skos:Concept", null, value);
		}else if (skosTypeList.contains("http://www.w3.org/2004/02/skos/core#Collection")) {
			return buildArtefactResource(artefactResource, "skos:Collection", null, value);
		}else if (entityTypeList.contains("class")) {
			return buildArtefactResource(artefactResource, "owl:Class", value, null);
		}else if (entityTypeList.contains("property")) {
			return buildArtefactResource(artefactResource, "rdfs:Property", value, null);
		}else if (entityTypeList.contains("individual")) {
			return buildArtefactResource(artefactResource, "owl:NamedIndividual", value, null);
		}
		
		return artefactResource;
	}

	private ArtefactResource buildArtefactResource(ArtefactResource artefactResource, String type, String id, String prefLabel) {
		
		artefactResource.setType(type);
		
		if(id != null)
			artefactResource.setId(id);
		else if (prefLabel != null)
			artefactResource.setPrefLabel(prefLabel);
		
		return artefactResource;
	}
	
	private <T> T readFromJsonPath(String json, String jsonPath) {
		try {
			return JsonPath.read(json, jsonPath);
		}catch(Exception e) {
			logger.debug("Failed to read json path '{}'}", jsonPath);
			return null;
		}
	}
}
