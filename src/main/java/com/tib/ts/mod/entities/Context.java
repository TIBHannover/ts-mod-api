package com.tib.ts.mod.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class Context {
	
	public static ThreadLocal<Map<String, String>> context = ThreadLocal.withInitial(HashMap::new);
		
	public static Map<String, String> getContext(){
		return context.get();
	}

	public static void addPaginationContext() {
		Context.context.get().put("hydra", "http://www.w3.org/ns/hydra/core#");
		Context.context.get().put("Collection", "hydra:Collection");
		Context.context.get().put("member", "hydra:member");
		Context.context.get().put("totalItems", "hydra:totalItems");
		Context.context.get().put("itemsPerPage", "hydra:itemsPerPage");
		Context.context.get().put("view", "hydra:view");
		Context.context.get().put("firstPage", "hydra:first");
		Context.context.get().put("lastPage", "hydra:last");
		Context.context.get().put("previousPage", "hydra:previous");
		Context.context.get().put("nextPage", "hydra:next");
	}
	
	public static void addContext(String key, String value) {
		Context.context.get().putIfAbsent(key, value);
	}
	
	public static void initContext() {
		context.remove();
		
		context.get().put("adms", "http://www.w3.org/ns/adms#");
		context.get().put("cc", "http://creativecommons.org/ns#");
		context.get().put("dc", "http://purl.org/dc/elements/1.1/");
		context.get().put("dcat", "http://www.w3.org/ns/dcat#");
		context.get().put("dcterms", "http://purl.org/dc/terms/");
		context.get().put("doap", "http://usefulinc.com/ns/doap#");
		context.get().put("foaf", "http://xmlns.com/foaf/0.1/");
		context.get().put("mod", "https://w3id.org/mod#");
		context.get().put("odrl", "http://www.w3.org/ns/odrl/2/");
		context.get().put("owl", "http://www.w3.org/2002/07/owl#");
		context.get().put("prov", "http://www.w3.org/ns/prov#");
		context.get().put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		context.get().put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		context.get().put("schema", "https://schema.org/");
		context.get().put("sd", "http://www.w3.org/ns/sparql-service-description#");
		context.get().put("skos", "http://www.w3.org/2004/02/skos/core#");
		context.get().put("vann", "https://vocab.org/vann/");
		context.get().put("vcard", "http://www.w3.org/2006/vcard/ns#");
		context.get().put("void", "http://rdfs.org/ns/void#");
		context.get().put("xsd", "http://www.w3.org/2001/XMLSchema#");
	}
	
}
