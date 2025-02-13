package com.tib.ts.mod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Configuration
public class RestClientConfig {

	@Bean
	public RestClient restClient() {
		return RestClient.create();
	}
}
