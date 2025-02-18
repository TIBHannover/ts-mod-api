package com.tib.ts.mod.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class Context {
	
	public static Map<String, String> context = new HashMap<String, String>();
	
	static {
		context.put("adms", "http://www.w3.org/ns/adms#");
		context.put("cc", "http://creativecommons.org/ns#");
		context.put("dc", "http://purl.org/dc/elements/1.1/");
		context.put("dcat", "http://www.w3.org/ns/dcat#");
		context.put("dcterms", "http://purl.org/dc/terms/");
		context.put("doap", "http://usefulinc.com/ns/doap#");
		context.put("foaf", "http://xmlns.com/foaf/0.1/");
		context.put("mod", "https://w3id.org/mod#");
		context.put("odrl", "http://www.w3.org/ns/odrl/2/");
		context.put("owl", "http://www.w3.org/2002/07/owl#");
		context.put("prov", "http://www.w3.org/ns/prov#");
		context.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		context.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		context.put("schema", "https://schema.org/");
		context.put("sd", "http://www.w3.org/ns/sparql-service-description#");
		context.put("skos", "http://www.w3.org/2004/02/skos/core#");
		context.put("vann", "https://vocab.org/vann/");
		context.put("vcard", "http://www.w3.org/2006/vcard/ns#");
		context.put("void", "http://rdfs.org/ns/void#");
		context.put("xsd", "http://www.w3.org/2001/XMLSchema#");
	}
	
	public static Map<String, String> getContext(){
		return context;
	}
}
