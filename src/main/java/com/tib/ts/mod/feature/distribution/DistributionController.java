package com.tib.ts.mod.feature.distribution;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.entities.enums.ResponseType;

import io.swagger.v3.oas.annotations.Hidden;
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
	
	private DistributionService distributionService;
	
	
	@Autowired
	public DistributionController(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	@Hidden
	@GetMapping
	@Operation(summary = "Get information about a ontology distributions", description = "Retrieves a collection of a ontology distributions")
	public String getOntologyDistributions(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pageSize) {
		return null;
	}

	@Hidden
	@GetMapping("/{distributionID}")
	@Operation(summary = "Get information about a ontology distribution", description = "Retrieves information about a ontology distribution")
	public String getOntologyDistributionByDistributionId(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@PathVariable(value = "distributionID") @Parameter(description = "The ID of the distribution") String distributionId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "html") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format) {
		return null;
	}

	@GetMapping("/latest")
	@Operation(summary = "Get information about a ontology resource for the latest distribution", description = "Retrieves a collection of a semantic artefact's resources for the latest distribution.")
	public ResponseEntity<String> getResourceForLatestDistribution(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(value = "display", defaultValue = "all") @Parameter(description = "The parameters to display") String display,
			@RequestParam(value = "format", defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ONTOLOGY_BY_ONTOLOGY_ID).setArtefactId(UriUtils.decode(artefactId, "UTF-8")).setDisplay(List.of(display)).setFormat(format).build();

		// invoke service impl
		String response = distributionService.getLatestDistributionByArtefactId(request);

		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}

}
