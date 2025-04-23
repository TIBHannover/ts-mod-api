package com.tib.ts.mod.common.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Component
@ConfigurationProperties(prefix = "mappings")
public class MappingRule {

	private Map<String, List<MappingDetail>> modAttributes;

	@Data
	public static class MappingDetail {
		private String jsonPath;

		private int priority;

		private String type;
		
		private String contextReference;

		private boolean toBeIncluded;
	}
	
	public void merge(MappingRule rule, String display) {
		if (rule != null && rule.getModAttributes() != null) {
			if(this.modAttributes == null) {
				this.modAttributes = new HashMap<String, List<MappingDetail>>();
			}
			rule.getModAttributes().forEach((key, value) -> {
				
				List<MappingDetail> filteredMappingRule = value;
				
				if (!display.toLowerCase().equalsIgnoreCase("all")) {

					List<String> filterByAttributes = Arrays.stream(display.toLowerCase().split(","))
															.map(String::trim)
															.collect(Collectors.toList());
					
					if (!filterByAttributes.contains(key.toLowerCase()) && filteredMappingRule.stream().noneMatch(MappingDetail::isToBeIncluded)) {
						filteredMappingRule = new ArrayList<MappingDetail>();
					}
				}
				
				if (!filteredMappingRule.isEmpty()) {
					this.modAttributes.merge(key, filteredMappingRule, (existing, newVal) -> {
						existing.addAll(newVal);
						return existing;
					});
				}
			});
				
		}
	}

}
