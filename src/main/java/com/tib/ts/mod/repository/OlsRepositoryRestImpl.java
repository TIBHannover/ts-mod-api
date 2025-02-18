package com.tib.ts.mod.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tib.ts.mod.common.constants.OlsRestUrl;

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
	public String getOntologies() {
		String url = OlsRestUrl.GET_ALL_ONTOLOGIES;
		
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
		}catch(RestClientException e) {
			logger.error("Network error while calling {}: {}", url, e.getMessage());
			throw new RuntimeException("Failed to fetch artefacts due to network issue.");
		}catch(Exception e) {
			logger.error("Unexpected error while calling {}: {}", url, e.getMessage());
			throw new RuntimeException("An Unexpected error occurred while fetching artefacts");
		}
	}
}
