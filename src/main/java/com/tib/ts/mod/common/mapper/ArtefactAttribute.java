package com.tib.ts.mod.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Configuration
@ConfigurationProperties(prefix = "mappings")
@PropertySource(value = "classpath:artefactsConfig.yaml", factory = YamlPropertySourceFactory.class)
public class ArtefactAttribute extends MappingRule {

	public ArtefactAttribute(Map<String, List<MappingDetail>> modAttributes) {
		super(modAttributes);
	}

}
