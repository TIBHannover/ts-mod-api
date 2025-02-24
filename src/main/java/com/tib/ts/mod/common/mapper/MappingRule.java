package com.tib.ts.mod.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "mappings")
public class MappingRule {

	private Map<String, List<MappingDetail>> modAttributes;

	@Getter
	@Setter
	public static class MappingDetail {
		private String jsonPath;

		private int priority;

		private String type;

		private List<String> keys;
	}

}
