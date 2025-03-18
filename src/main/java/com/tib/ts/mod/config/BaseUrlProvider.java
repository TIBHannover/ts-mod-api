package com.tib.ts.mod.config;

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
public class BaseUrlProvider {

	@ModelAttribute("baseUrl")
	public String getApiBaseUrl(HttpServletRequest request) {
		return ServletUriComponentsBuilder
	            .fromRequestUri(request)
	            .replaceQueryParam("page")
	            .replaceQueryParam("size")
	            .toUriString();
	}
}
