package com.tib.ts.mod.feature.catalogue;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tib.ts.mod.config.DefaultFields;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.enums.ActionType;
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
@RequestMapping("/")
@Tag(name = "Catalogue", description = "Get information about semantic artefact catalogue")
public class CatalogueController {
	
	@Autowired
	CatalogService catalogService;
		
	@GetMapping("")
	@Operation(summary = "Get information about semantic artefact catalogue", description = "Retrieves information about semantic artefact catalogue")
	public ResponseEntity<String> getCatalogue(
			@RequestParam(defaultValue = "jsonld") @Parameter(description = "The response format.<br/> This will override any value of `Accept` in the request headers. Possible values are `html`, `json`, `ttl` and `xml`. The default value is `html`.") FormatOption format,
			@RequestParam(value = "display", defaultValue = DefaultFields.SEMANTIC_ARTEFACT_CATALOG) @Parameter(description = "The parameters to display") List<String> display,
			@ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) throws BadRequestException {
		
		// Create a request DTO
		RequestDTO request = new RequestDTO.Builder(ActionType.CATALOG)
												   .setFormat(format)
												   .setDisplay(display)
												   .setBaseUrl(baseUrl)
												   .build();

		// invoke service impl
		String response = catalogService.getCatalog(request);

		// return new ResponseEntity<>(response, headers, HttpStatus.OK);
		if (format.equals(FormatOption.rdfxml))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.RDF_XML.getType())).body(response);
		else if (format.equals(FormatOption.ttl))
			return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.TTL.getType())).body(response);

		return ResponseEntity.ok().contentType(MediaType.valueOf(ResponseType.JSON_LD.getType())).body(response);
	}

}
