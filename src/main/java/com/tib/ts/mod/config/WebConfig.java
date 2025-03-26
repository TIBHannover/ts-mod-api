package com.tib.ts.mod.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET");
    }

}
