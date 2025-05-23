package com.tib.ts.mod.feature.record;

import java.util.List;

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

import com.tib.ts.mod.config.DefaultFields;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.entities.enums.ResponseType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Deepan Anbalagan
 * @email deepan.anbalagan@tib.eu 
 * TIB-Leibniz Information Center for Science and Technology
 *        
 */

@RestController
@RequestMapping("/")
@Tag(name = "Record", description = "Get Semantic artefact catlogue records")
public class RecordController {
	
	@Autowired
	RecordService recordService;

	@GetMapping("records")
	@Operation(summary = "Get information about all semantic artefact catalog records", description = "Retrieves a collection of semantic artefact catalog records")
	public ResponseEntity<String> getAllCatalogRecord(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "pagesize", defaultValue = "50") Integer pagesize,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "display", defaultValue = DefaultFields.SEMANTIC_ARTEFACT_CATALOG_RECORD) @Parameter(description = "The parameters to display") List<String> display,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ONTOLOGIES)
										   .setFormat(format)
										   .setPage(page)
										   .setPageSize(pagesize)
										   .setDisplay(display)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = recordService.getAllArtefactRecord(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}

	@GetMapping(value = { "records/{artefactID}", "artefacts/{artefactID}/record" })
	@Operation(summary = "Get information about semantic artefact catalog record", description = "Retrieves information about semantic artefact catalog record")
	public ResponseEntity<String> getRecordByArtefact(
			@PathVariable(value = "artefactID") @Parameter(description = "The ID of the artefact") String artefactId,
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `json`, `ttl` and `xml`. The default value is `jsonld`.") FormatOption format,
			@RequestParam(value = "display", defaultValue = DefaultFields.SEMANTIC_ARTEFACT_CATALOG_RECORD) @Parameter(description = "The parameters to display") List<String> display,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.ONTOLOGY_BY_ONTOLOGY_ID)
										   .setArtefactId(UriUtils.decode(artefactId, "UTF-8"))
										   .setFormat(format)
										   .setDisplay(display)
										   .setBaseUrl(baseUrl)
										   .build();

		// invoke service impl
		String response = recordService.getArtefactRecordByArtefactId(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}
}
