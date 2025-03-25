package com.tib.ts.mod.feature.artefact;


import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.entities.enums.ResponseType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestController
@RequestMapping("/artefacts")
@Tag(name = "Artefacts", description = "Get information about ontologies")
public class ArtefactController {
		
	@Autowired
	private ArtefactService service;
	
	@GetMapping(produces = "application/ld+json")
	@Operation(summary = "Get information about all ontology", description = "Retrieves a collection of all ontology")
	private ResponseEntity<String> getAllArtefacts(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pagesize,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		
		long start = System.currentTimeMillis();
		//Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ONTOLOGIES)
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pagesize)
										   .setDisplay(display)
										   .setBaseUrl(baseUrl)
										   .build();
		
		//invoke service impl
		String response = service.getAllArtefact(request);
		System.out.println("Total time take: "+ (System.currentTimeMillis() - start));
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping(path = "/{artefactID}", produces = "application/ld+json")
	@Operation(summary = "Get information about a ontology", description = "Retrieves information about a ontology")
	@ApiResponse(responseCode = "200", description = "Successful response",useReturnTypeSchema = true)
	@ApiResponse(responseCode = "404", description = "Not found", content = {@Content(mediaType = "text/html")})
	@ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "text/html")})
	public ResponseEntity<String> getArtefactByArtefactId(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format) throws BadRequestException {
		
		//Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ONTOLOGY_BY_ONTOLOGY_ID).setArtefactId(artefactId).setFormat(format).setDisplay(display).build();
		
		//invoke service impl
		String response = service.getArtefactByArtefactId(request);
		
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
}
