package com.tib.ts.mod.catalogue;

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
@RequestMapping("/")
@Tag(name = "Catalogue", description = "Get information about semantic artefact catalogue")
public class CatalogueController {
	
	@GetMapping("")
	@Operation(summary = "Get information about semantic artefact catalogue", description = "Retrieves information about semantic artefact catalogue")
	public String getCatalogue(
			@RequestParam(defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display) {
		return null;
	}

}
