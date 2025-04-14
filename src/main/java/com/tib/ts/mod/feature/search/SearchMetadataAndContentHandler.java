package com.tib.ts.mod.feature.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.tib.ts.mod.common.Helper;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.AttributeFile;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.DynamicConfigLoader;
import com.tib.ts.mod.common.mapper.MappingRule;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class SearchMetadataAndContentHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchMetadataAndContentHandler.class);
	
	@Autowired
	OlsRepository terminologyService;
	
	@Autowired
	DynamicConfigLoader configLoader;
	
	@Autowired
	MetadataMapper mapper;
	
	@Autowired
	Helper helper;

	@Override
	public String preHandler(RequestDTO request) throws BadRequestException {
		if (request == null) {
			logger.info(ErrorMessage.NULL_REQUEST_MSG);
			return ErrorMessage.INVALID_PARAMETERS;
		}

		boolean isDisplayValid = Validation.ValidateDisplay(request.getDisplay());
		
		if (!isDisplayValid)
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());

		return (isDisplayValid) ? "" : ErrorMessage.INVALID_PARAMETERS;
	}

	@Override
	public String execute(RequestDTO request) throws BadRequestException {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();

		String olsSearchResult = terminologyService.call(request);
		
		request.setFilterByOntology(fetchOntologies(olsSearchResult));
		request.setOperationType(ActionType.ONTOLOGIES);
		
		if(request.getFilterByOntology().isEmpty()) {
			return null;
		}
		String result = terminologyService.call(request);
		
		
		MappingRule rules = configLoader.mergeConfiguration(String.join(",", request.getDisplay()),
															AttributeFile.SEMANTIC_ARTEFACT,
															AttributeFile.DCAT_RESOURCE,
															AttributeFile.DCAT_DATA_SERVICE);

		request.setMappingRule(rules);
		
		return result;
	}

	@Override
	public String postHandler(RequestDTO request, String response) {
		
		List<SemanticArtefact> semanticArtefacts = new LinkedList<SemanticArtefact>();
		String results = "";
		try {
			var responseObject = JsonParser.parseString(response).getAsJsonObject();

			if (!responseObject.has("elements") || responseObject.get("elements").isJsonNull()) {
				logger.warn("Response does not contain any ontologies");
				return "";
			}

			var ontologies = responseObject.get("elements").getAsJsonArray();

			for (JsonElement ontology : ontologies) {
				if (ontology == null || ontology.isJsonNull()) {
					logger.debug("Skipping null ontology");
					continue;
				}
				
				SemanticArtefact semanticArtefact = mapper.mapJsonToDto(ontology.toString(), SemanticArtefact.class, request.getMappingRule());
				
				logger.debug("Mapped SemanticArtefact: {}", semanticArtefact);
				
				if (semanticArtefact != null) {
					semanticArtefact.setSemanticArtefactType("mod:SemanticArtefact");
					semanticArtefacts.add(semanticArtefact);
				}
			}
			
			ResponseDTO<List<SemanticArtefact>> responseDto = new ResponseDTO<List<SemanticArtefact>>();
			responseDto.setContext(Context.getContext());
			
			if (!semanticArtefacts.isEmpty()) {
				if(request.getFormat().equals(FormatOption.jsonld)) {
					responseDto.setJsonResult(semanticArtefacts);
					Context.addPaginationContext();
					responseDto.setView(helper.getView(request.getBaseUrl(), responseObject));
					responseDto.setId(request.getBaseUrl());
					responseDto.setType("Collection");
					responseDto.setTotalItems(responseObject.get("totalElements").getAsInt());
					responseDto.setItemsPerPage(responseObject.get("numElements").getAsInt());
				}else {
					responseDto.setOtherFormatResult(semanticArtefacts);
				}
				results = ResponseConverter.convert(responseDto, request.getFormat(), request.getDisplay());
			} 
		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return results;
	}
	
	/**
	 * @param olsSearchResult
	 * @throws JsonSyntaxException
	 */
	private Set<String> fetchOntologies(String olsSearchResult) throws JsonSyntaxException {
		
		var resultObject = JsonParser.parseString(olsSearchResult).getAsJsonObject();
		
		if(Optional.ofNullable(resultObject.getAsJsonObject("response"))
				.map(response -> response.getAsJsonArray("docs"))
				.filter(docs -> !docs.isEmpty())
				.isEmpty()) {
			logger.warn("Response does not contain any search result");
			return new HashSet<String>();
		}
		
		var solrDocs = resultObject.getAsJsonObject("response").getAsJsonArray("docs");
		
		return StreamSupport.stream(solrDocs.spliterator(), false)
												   .filter(doc -> doc != null && !doc.isJsonNull())
												   .map(doc -> doc.getAsJsonObject().get("ontology_name").getAsString())
												   .collect(Collectors.toSet());
	}

}
