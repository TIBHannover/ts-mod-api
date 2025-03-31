package com.tib.ts.mod.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Configuration
public class SwaggerConfig {
	
	//@Value("${server.url}")
	//private String MOD_SERVER_URL;;
	private final String description = "This Application Programming Interface (API) has been developed to promote interoperability of Ontology Catalogues in the European Open Science Cloud (EOSC) ecosystem and beyond.";
	private final String title = "MOD API";

	@Bean
	public OpenAPI customAPI(ServletContext context) {
		//String serverUrl = context.getContextPath().equals("") ? MOD_SERVER_URL : context.getContextPath();
		return new OpenAPI(SpecVersion.V31)
			//	.addServersItem(new Server().url(serverUrl))
				.info(new Info()
						.title(title)
						.description(description)
						.version("0.0.1")
						);
	}
}
