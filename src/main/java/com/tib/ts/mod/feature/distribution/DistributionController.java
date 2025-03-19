package com.tib.ts.mod.feature.distribution;

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
 * @author Deepan Anbalagan
 * @email deepan.anbalagan@tib.eu TIB-Leibniz Information Center for Science and
 *        Technology
 */

@RestController
@RequestMapping("/artefacts/{artefactID}/distributions")
@Tag(name = "Distributions", description = "Get information about ontology distributions")
public class DistributionController {

	@GetMapping
	@Operation(summary = "Get information about a ontology distributions", description = "Retrieves a collection of a ontology distributions")
	public String getOntologyDistributions(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}

	@GetMapping("/{distributionID}")
	@Operation(summary = "Get information about a ontology distribution", description = "Retrieves information about a ontology distribution")
	public String getOntologyDistributionByDistributionId(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@PathVariable(value = "distributionID") @Parameter(description = "The ID of the distribution") String distributionId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format) {
		return null;
	}

	@GetMapping("/latest/resources")
	@Operation(summary = "Get information about a ontology resource for the latest distribution", description = "Retrieves a collection of a semantic artefact's resources for the latest distribution.")
	public String getResourceForLatestDistribution(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}

}
