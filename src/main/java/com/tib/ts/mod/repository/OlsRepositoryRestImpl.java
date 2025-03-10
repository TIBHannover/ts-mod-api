package com.tib.ts.mod.repository;

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
			case ONTOLOGIES -> getOntologies(request.getPage(), request.getPageSize());
			case ONTOLOGYBYONTOLOGYID -> getOntologiesByOntologyId(request.getArtefactId());
			case ENTITIESBYONTOLOGYID -> getEntitiesByOntologyId(request.getArtefactId(), request.getPage(), request.getPageSize());
			default -> throw new IllegalArgumentException();
		};

		return result;
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

	private String getOntologies(final Integer page, final Integer size) throws BadRequestException {
		String url = UriComponentsBuilder.fromUriString(OlsRestUrl.GET_ALL_ONTOLOGIES)
										 .queryParam("page", page)
										 .queryParam("size", size)
										 .toUriString();
		
		logger.info("calling external service: {}", url);
		
		return invokeRest(url);
	}
	
	private String invokeRest(String url) throws BadRequestException {
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
