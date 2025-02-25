package com.tib.ts.mod.common.converter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tib.ts.mod.entities.enums.FormatOption;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class ResponseConverter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseConverter.class);

	public static <T> String convert(T response, FormatOption format) {
		String results = "";

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			results = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			logger.error("Error in converting response to JSON-LD");
		}
		
		results = switch (format) {
			case rdfxml -> convertToRDF(results);
			default -> results;
		};

		return results;

	}

	private static String convertToRDF(String inputToConvert) {
		String results = "";

		Model model = ModelFactory.createDefaultModel();
		try (InputStream jsonLdStream = new ByteArrayInputStream(inputToConvert.getBytes())) {
			// Read JSON-LD into an RDF model
			RDFDataMgr.read(model, jsonLdStream, null, Lang.JSONLD);
		} catch (Exception e) {
			logger.error("Error in converting JSON-LD to RDF/xml");
		}

		// Convert RDF model to RDF/XML format
		StringWriter writer = new StringWriter();
		RDFDataMgr.write(writer, model, RDFFormat.RDFXML_PRETTY);
		results = writer.toString();

		return results;
	}

}
