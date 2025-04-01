package com.tib.ts.mod.config;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestControllerAdvice
public class DefaultValueProvider {

	@ModelAttribute("baseUrl")
	public String getApiBaseUrl(HttpServletRequest request) {
		return ServletUriComponentsBuilder
	            .fromRequestUri(request)
	            .replaceQueryParam("page")
	            .replaceQueryParam("size")
	            .toUriString();
	}
	
	@ModelAttribute("defaultSemanticArtefactAttributes")
	public List<String> getSemanticArtefactDefaultAttributes() {
		return List.of("dcterms:accessRights",
				       "mod:acronym",
				       "dcat:contactPoint", 
				       "dcterms:creator", 
				       "dcterms:description", 
				       "dcterms:identifier", 
				       "dcat:keyword", 
				       "dcat:landingPage", 
				       "dcterms:license", 
				       "dcterms:rightsHolder", 
				       "dcterms:subject", 
				       "dcterms:title", 
				       "dcterms:type", 
				       "owl:versionIRI");
	}
}
