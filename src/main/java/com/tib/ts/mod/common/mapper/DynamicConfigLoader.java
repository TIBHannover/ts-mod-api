package com.tib.ts.mod.common.mapper;

import java.io.IOException;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class DynamicConfigLoader {

	private static final Logger logger = LoggerFactory.getLogger(DynamicConfigLoader.class);
	
	private final ObjectMapper yamlMapper;
	
	@Autowired
	public DynamicConfigLoader() {
		this.yamlMapper = new ObjectMapper(new YAMLFactory());
	}
	
	public MappingRule mergeConfiguration( String display, String... configFiles) throws BadRequestException {
		MappingRule mergedConfig = new MappingRule();
		
		for (String configFile : configFiles) {
			logger.info("Loading config file: {}", configFile);
			MappingRule rule = loadConfiguration(configFile, display);
			mergedConfig.merge(rule, display);
		}
		
		if (mergedConfig != null && !mergedConfig.getModAttributes().isEmpty())
			return mergedConfig;
		else {
			logger.error("Incorrect display parameter provided: {}", display);
			throw new BadRequestException("Incorrect display parameter provided: "+ display);
		}
	}

	private MappingRule loadConfiguration(String configFile, String display) {

		try {
			Resource resource = new ClassPathResource(configFile);
			return yamlMapper.readValue(resource.getInputStream(), MappingRule.class);
		} catch(IOException e) {
			logger.error("Failed to load yaml file: {}", configFile);
			return null;
		}
	}
}
