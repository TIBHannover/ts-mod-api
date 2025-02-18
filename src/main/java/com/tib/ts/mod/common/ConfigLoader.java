package com.tib.ts.mod.common;

import java.io.File;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/


public class ConfigLoader {

	private static final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> loadConfig(String filePath) throws Exception {
        return yamlMapper.readValue(new File(filePath), Map.class);
    }
}
