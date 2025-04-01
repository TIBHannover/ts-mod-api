package com.tib.ts.mod.feature.resource;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.ArtefactResourceType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.entities.enums.ResponseType;

import io.swagger.v3.oas.annotations.Hidden;
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
	
	@Autowired
	private ArtefactResourceService service;
	
	@GetMapping
	@Operation(summary = "Get a list of all resources within an ontology", description = "Retrieves a list of all resources within an ontology")
	public ResponseEntity<String> getAllResourceByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ENTITIES_BY_ONTOLOGY_ID)
										   .setResourceType(ArtefactResourceType.ENTITIES)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = service.getAllArtefactResource(request);
		
		// return response
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/{resourceID}")
	@Operation(summary = "Get a specific resource within an ontology", description = "Retrieves a specific resource within an ontology")
	public ResponseEntity<String> getResourceByResourceId(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@PathVariable(value = "resourceID") @Parameter(description = "The ID of the resource") String resourceId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ENTITIES_BY_ONTOLOGY_ID_AND_IRI)
										   .setResourceType(ArtefactResourceType.ANY)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setResourceId(resourceId)
										   .setFormat(format)
										   .build();

		// invoke service impl
		String response = service.getArtefactResourceByArtefactIdAndResourceId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/classes")
	@Operation(summary = "Get a list of all owl:classes within an ontology", description = "Retrieves a list of all owl:classes within an ontology")
	public ResponseEntity<String> getAllClassByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.CLASSES_BY_ONTOLOGY_ID)
										   .setResourceType(ArtefactResourceType.CLASS)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = service.getArtefactResourceClassesByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/concepts")
	@Operation(summary = "Get a list of all skos:Concept within an ontology", description = "Retrieves a list of all skos:Concept within an ontology")
	public ResponseEntity<String> getAllConceptByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ENTITIES_BY_ONTOLOGY_ID)
				   						   .setResourceType(ArtefactResourceType.CONCEPT)
				   						   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
				   						   .setFormat(format)
				   						   .setPage(page)
				   						   .setPageSize(pageSize)
				   						   .setBaseUrl(baseUrl)
				   						   .build();

		// invoke service impl
		String response = service.getArtefactResourceConceptByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/properties")
	@Operation(summary = "Get a list of all rdf:Property within an ontology", description = "Retrieves a list of all rdf:Property within an ontology")
	public ResponseEntity<String> getAllPropertiesByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.PROPERTIES_BY_ONTOLOGY_ID)
										   .setResourceType(ArtefactResourceType.PROPERTY)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = service.getArtefactResourcePropertiesByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/individuals")
	@Operation(summary = "Get a list of all the instances (owl individuals) within an ontology", description = "Retrieves a list of all he instances (owl individuals) within an ontology")
	public ResponseEntity<String> getAllIndividualsByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.INDIVIDUALS_BY_ONTOLOGY_ID)
				   					       .setResourceType(ArtefactResourceType.INDIVIDUAL)
				   					       .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
				   					       .setFormat(format)
				   					       .setPage(page)
				   					       .setPageSize(pageSize)
				   					       .setBaseUrl(baseUrl)
				   					       .build();

		// invoke service impl
		String response = service.getArtefactResourceIndividualsByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/schemes")
	@Operation(summary = "Get a list of all skos:Scheme within an ontology", description = "Retrieves a list of all skos:Scheme within an ontology")
	public ResponseEntity<String> getAllSchemeByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ENTITIES_BY_ONTOLOGY_ID)
										   .setResourceType(ArtefactResourceType.SCHEME)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = service.getArtefactResourceSchemesByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/collection")
	@Operation(summary = "Get a list of all skos:Collection within an ontology", description = "Retrieves a list of all skos:Collection within an ontology")
	public ResponseEntity<String> getAllCollectionByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ENTITIES_BY_ONTOLOGY_ID)
										   .setResourceType(ArtefactResourceType.COLLECTION)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = service.getArtefactResourceCollectionByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@Hidden
	@GetMapping("/labels")
	@Operation(summary = "Get a list of all skos-xl:Label within an ontology", description = "Retrieves a list of all skos-xl:Label within an ontology")
	public String getAllLabelByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}

}
