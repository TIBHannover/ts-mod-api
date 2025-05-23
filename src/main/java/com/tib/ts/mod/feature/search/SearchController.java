package com.tib.ts.mod.feature.search;

import java.util.List;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.ArtefactResourceType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.entities.enums.ResponseType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestController
@RequestMapping("/search")
@Tag(name = "Search", description = "Search the metadata and catalogue content")
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@GetMapping
	@Operation(summary = "Search all of the metadata and content in a catlogue", description = "The returned data should include a decription of the type of data that is being returned.")
	public ResponseEntity<String> SearchMetadataAndContent(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {

		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.V1SEARCH)
										   .setQuery(q)
										   .setFormat(format)
										   .setDisplay(List.of(display))
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();
		// invoke service impl
		String response = searchService.searchMetadataAndContent(request);
		
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/content")
	@Operation(summary = "Search all of the content in a catalogue", description = "The returned data should include a decription of the type of data that is being returned. For example the resturned content could be SKOS Concepts or OWL Classes.")
	public ResponseEntity<String> SearchContent(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.SEARCH_ENTITIES)
										   .setQuery(q)
										   .setResourceType(ArtefactResourceType.ANY)
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();
		// invoke service impl
		String response = searchService.searchContent(request);

		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
	
	@GetMapping("/metadata")
	@Operation(summary = "Search all of the metadata in a catlogue", description = "The returned data should include a decription of the type of data that is being returned.")
	public ResponseEntity<String> SearchMetadata(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.V1SEARCH)
										   .setQuery(q)
										   .setFilterByType(Set.of("ontology"))
										   .setFormat(format)
										   .setDisplay(List.of(display))
										   .setPage(page)
										   .setPageSize(pageSize)
										   .setBaseUrl(baseUrl)
										   .build();
		// invoke service impl
		String response = searchService.searchMetadataAndContent(request);

		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
}
