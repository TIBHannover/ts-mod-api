package com.tib.ts.mod.search;

import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/search")
@Tag(name = "Search", description = "Search the metadata and catalogue content")
public class SearchController {

	@GetMapping
	@Operation(summary = "Search all of the metadata and content in a catlogue", description = "The returned data should include a decription of the type of data that is being returned.")
	public String SearchMetadataAndContent(
			@RequestParam(defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display) {
		return null;
	}
	
	@GetMapping("/content")
	@Operation(summary = "Search all of the content in a catlogue", description = "The returned data should include a decription of the type of data that is being returned. For example the resturned content could be SKOS Concepts or OWL Classes.")
	public String SearchContent(
			@RequestParam(defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q) {
		return null;
	}
	
	@GetMapping("/metadata")
	@Operation(summary = "Search all of the metadata in a catlogue", description = "The returned data should include a decription of the type of data that is being returned.")
	public String SearchMetadata(
			@RequestParam(defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "q", required = true) @Parameter(description = "The search query") String q,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display) {
		return null;
	}
}
