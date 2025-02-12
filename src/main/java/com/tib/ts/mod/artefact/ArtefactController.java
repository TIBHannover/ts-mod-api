package com.tib.ts.mod.artefact;

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
@RequestMapping("/artefacts")
@Tag(name = "Artefacts", description = "Get information about ontologies")
public class ArtefactController {
	
	@GetMapping
	@Operation(
			summary = "Get information about all ontology",
			description = "Retrieves a collection of all ontology"
			)
	public String getAllArtefacts(
			@RequestParam (defaultValue = "html")
			@Parameter (
					description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`."
					)
			FormatOption format,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pagesize,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam (value = "display", defaultValue = "all")
			@Parameter (
					description = "The parameters to display"
					)
			String display
			) {
		return null;
	}
	
	@GetMapping("/{artefactID}")
	@Operation(
			summary = "Get information about a ontology",
			description = "Retrieves information about a ontology"
			)
	public String getArtefactByArtefactId(
			@PathVariable (value = "artefactID")
			@Parameter (description = "The ID of the artefact")
			String artefactId,
			@RequestParam (value = "display", defaultValue = "all")
			@Parameter (
					description = "The parameters to display"
					)
			String display,
			@RequestParam (defaultValue = "html")
			@Parameter (
					description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`."
					)
			FormatOption format
			) {
		return null;
	}
}
