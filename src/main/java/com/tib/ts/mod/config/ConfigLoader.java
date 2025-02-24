package com.tib.ts.mod.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.tib.ts.mod.common.mapper.MappingRule;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Configuration
@EnableConfigurationProperties(MappingRule.class)
public class ConfigLoader {

}
