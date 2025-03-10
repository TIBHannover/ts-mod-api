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
		
		String value = JsonPath.read(apiResponse, "$.iri");
		
		var entityType = JsonPath.read(apiResponse, "$.type");
		
		var skosType = JsonPath.read(apiResponse, "$.['http://www.w3.org/1999/02/22-rdf-syntax-ns#type']");
		
		if (entityType == null)
			return artefactResource;
		
		List<String> entityTypeList = ((entityType instanceof List)) ? (List<String>) entityType : List.of(entityType.toString());
		List<String> skosTypeList = ((skosType instanceof List)) ? (List<String>) skosType : List.of(skosType.toString());
		
		
		artefactResource = switch (request.getResourceType()) {
			case CLASS -> buildArtefactResource(artefactResource, "owl:Class", value, null);
			case PROPERTY -> buildArtefactResource(artefactResource, "rdfs:Property", value, null);
			case INDIVIDUAL -> buildArtefactResource(artefactResource, "owl:NamedIndividual", value, null);
			case COLLECTION -> buildArtefactResource(artefactResource, "skos:Collection", null, value);
			case CONCEPT -> buildArtefactResource(artefactResource, "skos:Concept", null, value);
			case SCHEME -> buildArtefactResource(artefactResource, "skos:ConceptScheme", null, value);
			default -> determineType(artefactResource, skosTypeList, entityTypeList, value);
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
}
