package com.tib.ts.mod.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tib.ts.mod.entities.enums.FormatOption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestController
@RequestMapping("/artefacts/{artefactID}/resources")
@Tag(name = "Resources", description = "Get information about resources within an Ontology")
public class ResourceController {
	
	@GetMapping
	@Operation(summary = "Get a list of all resources within an ontology", description = "Retrieves a list of all resources within an ontology")
	public String getAllResourceByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/{resourceID}")
	@Operation(summary = "Get a specific resource within an ontology", description = "Retrieves a specific resource within an ontology")
	public String getResourceByResourceId(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@PathVariable(value = "resourceID") @Parameter(description = "The ID of the resource") String resourceId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/classes")
	@Operation(summary = "Get a list of all owl:classes within an ontology", description = "Retrieves a list of all owl:classes within an ontology")
	public String getAllClassByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/concepts")
	@Operation(summary = "Get a list of all skos:Concept within an ontology", description = "Retrieves a list of all skos:Concept within an ontology")
	public String getAllConceptByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/properties")
	@Operation(summary = "Get a list of all rdf:Property within an ontology", description = "Retrieves a list of all rdf:Property within an ontology")
	public String getAllPropertiesByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/individuals")
	@Operation(summary = "Get a list of all the instances (owl individuals) within an ontology", description = "Retrieves a list of all he instances (owl individuals) within an ontology")
	public String getAllIndividualsByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/schemes")
	@Operation(summary = "Get a list of all skos:Scheme within an ontology", description = "Retrieves a list of all skos:Scheme within an ontology")
	public String getAllSchemeByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/collection")
	@Operation(summary = "Get a list of all skos:Collection within an ontology", description = "Retrieves a list of all skos:Collection within an ontology")
	public String getAllCollectionByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}
	
	@GetMapping("/labels")
	@Operation(summary = "Get a list of all skos-xl:Label within an ontology", description = "Retrieves a list of all skos-xl:Label within an ontology")
	public String getAllLabelByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}

}
