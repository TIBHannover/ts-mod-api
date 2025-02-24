package com.tib.ts.mod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tib.ts.mod.common.mapper.ArtefactAttribute;
import com.tib.ts.mod.common.mapper.MappingRule;

@SpringBootApplication
public class ModApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModApplication.class, args);
	}

}
