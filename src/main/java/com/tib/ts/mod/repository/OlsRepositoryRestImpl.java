package com.tib.ts.mod.repository;

import java.util.Optional;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.apache.jena.shared.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.constants.OlsRestUrl;
import com.tib.ts.mod.entities.dto.OlsErrorResponse;
import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class OlsRepositoryRestImpl implements OlsRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(OlsRepository.class);
	
	@Autowired
	RestClient restClient;
	
	@Override
	public String call(RequestDTO request) throws BadRequestException {
		String result = switch (request.getOperationType()) {
			case CATALOG -> getCatalog();
			case ONTOLOGIES -> getOntologies(request.getPage(), request.getPageSize(), request.getFilterByOntology());
			case ONTOLOGIES_BY_ONTOLOGY_IRI -> getOntologiesByOntologyIri(request.getPage(), request.getPageSize(), request.getArtefactId());
			case ONTOLOGY_BY_ONTOLOGY_ID -> getOntologiesByOntologyId(request.getOntologyId());
			case ENTITIES_BY_ONTOLOGY_ID -> getEntitiesByOntologyId(request.getOntologyId(), request.getPage(), request.getPageSize());
			case ENTITIES_BY_ONTOLOGY_ID_AND_IRI -> getEntitiesByOntologyIdAndIri(request.getOntologyId(), request.getResourceId() , request.getPage(), request.getPageSize());
			case CLASSES_BY_ONTOLOGY_ID -> getClassesByOntologyId(request.getOntologyId(), request.getPage(), request.getPageSize());
			case CLASSES_BY_ONTOLOGY_ID_AND_IRI -> getClassesByOntologyIdAndIri(request.getOntologyId(), request.getResourceId());
			case INDIVIDUALS_BY_ONTOLOGY_ID -> getIndividualsByOntologyId(request.getOntologyId(), request.getPage(), request.getPageSize());
			case INDIVIDUALS_BY_ONTOLOGY_ID_AND_IRI -> getIndividualsByOntologyIdAndIri(request.getOntologyId(), request.getResourceId());
			case PROPERTIES_BY_ONTOLOGY_ID -> getPropertiesByOntologyId(request.getOntologyId(), request.getPage(), request.getPageSize());
			case PROPERTIES_BY_ONTOLOGY_ID_AND_IRI -> getPropertiesByOntologyIdAndIri(request.getOntologyId(), request.getResourceId());
			case V1SEARCH -> searchMetadataAndContent(request.getQuery(), request.getPage(), request.getPageSize(), request.getFilterByType());
			case SEARCH_ENTITIES -> searchContent(request.getQuery(), request.getPage(), request.getPageSize());
			default -> throw new IllegalArgumentException();
		};

		return result;
	}
	
	private String searchContent(String query, Integer page, Integer pageSize) {
		var builder = UriComponentsBuilder.fromUriString(OlsRestUrl.SEARCH_ENTITIES)
										  .queryParam("search", query)
										  .queryParam("page", page)
										  .queryParam("size", pageSize);


		String url = builder.toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String getCatalog() {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_CATALOG).toUriString();
		
		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String getEntitiesByOntologyIdAndIri(String ontologyId, String resourceId, Integer page, Integer pageSize) {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ENTITY_BY_ONTOLOGY_ID_AND_IRI)
										 .buildAndExpand(ontologyId, resourceId).toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String getOntologiesByOntologyIri(Integer page, Integer pageSize, String artefactId) {
		var builder = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_ONTOLOGIES)
										  .queryParam("search", artefactId)
										  .queryParam("searchFields", "iri")
										  .queryParam("start", page)
										  .queryParam("rows", pageSize);


		String url = builder.toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String searchMetadataAndContent(String query, Integer page, Integer pageSize, Set<String> filterByType) {
		
		var builder = UriComponentsBuilder.fromUriString(OlsRestUrl.SEARCH)
										  .queryParam("q", query)
										  .queryParam("start", page)
										  .queryParam("rows", pageSize);
		
		Optional.ofNullable(filterByType).ifPresent(type -> builder.queryParam("type", type));
		
		String url = builder.toUriString();
		
		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String getPropertiesByOntologyId(String artefactId, Integer page, Integer pageSize) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_PROPERTIES_BY_ONTOLOGY_ID)
										 .queryParam("page", page)
										 .queryParam("size", pageSize)
										 .buildAndExpand(artefactId)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}
	
	private String getPropertiesByOntologyIdAndIri(String artefactId, String iri) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_PROPERTIES_BY_ONTOLOGY_ID_AND_IRI)
										 .buildAndExpand(artefactId, iri)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}
	
	private String getIndividualsByOntologyId(String artefactId, Integer page, Integer pageSize) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_INDIVIDUALS_BY_ONTOLOGY_ID)
										 .queryParam("page", page)
										 .queryParam("size", pageSize)
										 .buildAndExpand(artefactId)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}
	
	private String getIndividualsByOntologyIdAndIri(String artefactId, String iri) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_INDIVIDUALS_BY_ONTOLOGY_ID_AND_IRI)
										 .buildAndExpand(artefactId, iri)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}
	
	private String getClassesByOntologyId(String artefactId, Integer page, Integer pageSize) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_CLASSES_BY_ONTOLOGY_ID)
										 .queryParam("page", page)
										 .queryParam("size", pageSize)
										 .buildAndExpand(artefactId)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}
	
	private String getClassesByOntologyIdAndIri(String artefactId, String iri) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_CLASSES_BY_ONTOLOGY_ID_AND_IRI)
										 .buildAndExpand(artefactId, iri)
										 .toUriString();

		logger.info("calling external service: {}", url);

		return invokeRest(url);
	}

	private String getEntitiesByOntologyId(String artefactId, Integer page, Integer pageSize) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_ENTITIES_BY_ONTOLOGY_ID)
										 .queryParam("page", page)
										 .queryParam("size", pageSize)
										 .buildAndExpand(artefactId)
										 .toUriString();
		
		logger.info("calling external service: {}", url);
		
		return invokeRest(url);
	}

	private String getOntologiesByOntologyId(final String artefactId) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ONTOLOGY_BY_ONTOLOGY_ID)
				                         .path(artefactId)
				                         .toUriString();
				
		logger.info("calling external service: {}", url);
		
		return invokeRest(url);
	}

	private String getOntologies(final Integer page, final Integer size, Set<String> filterByOntology) throws BadRequestException {
				
		var builder = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_ONTOLOGIES)
				 						  .queryParam("page", page)
				 						  .queryParam("size", size);
		
		Optional.ofNullable(filterByOntology).ifPresent(ontology -> builder.queryParam("ontology", ontology));
		
		String url = builder.toUriString();
		
		logger.info("calling external service: {}", url);
		
		return invokeRest(url);
	}
	
	private String invokeRest(String url) {
		try {
			String response = restClient.get().uri(url).retrieve().body(String.class);
			return response;
		}catch (HttpClientErrorException e) {
			logger.error("HTTP error while calling {}: {}, {}", url, e.getStatusCode(), e.getMessage());
			throw new RuntimeException("Failed to fetch artefacts: "+ e.getStatusCode());
		}catch(RestClientResponseException e) {
			OlsErrorResponse errorResponse = e.getResponseBodyAs(OlsErrorResponse.class);
			if (errorResponse != null && errorResponse.getMessage().equalsIgnoreCase(ErrorMessage.OLS_EXCEPTION_MSG)) {
				logger.error("No record found while calling {}: {}", url, e.getMessage());
				throw new NotFoundException(ErrorMessage.INVALID_PARAMETERS);
			}
			logger.error("Network error while calling {}: {}", url, e.getMessage());
			throw new RuntimeException("Failed to fetch artefacts due to network issue.");
		}catch(ResourceAccessException e) {
			logger.error("Timeout error while calling {}: {}", url, e.getMessage());
			throw new RuntimeException("Failed to fetch artefacts dut to timeout");
		}catch(Exception e) {
			logger.error("Unexpected error while calling {}: {}", url, e.getMessage());
			throw new RuntimeException("An Unexpected error occurred while fetching artefacts");
		}
	}
}
