package com.tib.ts.mod.common.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.riot.RDFParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tib.ts.mod.config.DynamicSerializer;
import com.tib.ts.mod.entities.enums.FormatOption;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class ResponseConverter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseConverter.class);
	
	private ObjectMapper objectMapper = new ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	public static <T> String convert(T response, FormatOption format, List<String> defaultFields) {
		String results = "";
		
		try {
			results = convertToJsonld(format, response, defaultFields);//objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);

			return results = switch (format) {
				case rdfxml -> convertToRDF(results);
				case ttl -> convertToTTL(results);
				default -> results;
			};
		} catch (JsonProcessingException e) {
			logger.error("Error in converting response to JSON-LD");
			return "";
		}
	}

	private static <T> String convertToJsonld(FormatOption format, T response, List<String> defaultFields) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		
		switch (format) {
			case jsonld: {
				if (defaultFields != null && !defaultFields.isEmpty()) {
					module.addSerializer(new DynamicSerializer<T>((Class<T>) response.getClass(), defaultFields));
					objectMapper.registerModule(module);
				} else {
					objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				}
			}
			default: {
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			};
		};
		
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
	}

	private static String convertToRDF(String inputToConvert) {

		Model model = ModelFactory.createDefaultModel();
		try (InputStream jsonLdStream = new ByteArrayInputStream(inputToConvert.getBytes())) {
			RDFDataMgr.read(model, jsonLdStream, null, Lang.JSONLD);
		} catch (Exception e) {
			logger.error("Error in converting JSON-LD to RDF/xml");
			return "";
		}
		return writeModel(model, RDFFormat.RDFXML_PRETTY);
	}

	private static String convertToTTL(String inputToConvert) {

		Model model = ModelFactory.createDefaultModel();
		try {
			RDFParser.create().source(new StringReader(inputToConvert)).lang(Lang.JSONLD).parse(model);
		} catch (Exception e) {
			logger.error("Error converting JSON-LD to Turtle", e);
		}
		return writeModel(model, RDFFormat.TURTLE);
	}

	private static String writeModel(Model model, RDFFormat format) {

		try (StringWriter writer = new StringWriter()) {
			RDFDataMgr.write(writer, model, format);
			return writer.toString();
		} catch (IOException e) {
			logger.error("Error writing format:{}", format, e);
			return "";
		}
	}

}
