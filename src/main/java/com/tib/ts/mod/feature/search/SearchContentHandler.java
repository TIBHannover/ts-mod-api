package com.tib.ts.mod.feature.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;

import org.apache.coyote.BadRequestException;
import org.apache.jena.shared.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.ArtefactResourceMapper;
import com.tib.ts.mod.entities.ArtefactResource;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.entities.enums.ArtefactResourceType;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class SearchContentHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchContentHandler.class);
	
	@Autowired
	OlsRepository terminologyService;
	
	@Autowired
	ArtefactResourceMapper artefactResourceMapper;
	

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
		
		List<RequestDTO> searchRequests = constructAPIRequest(olsSearchResult);
		
		List<CompletableFuture<JsonObject>> futures = searchRequests.stream()
					  												.map(searchRequest -> CompletableFuture.supplyAsync(() -> makeAPICall(searchRequest)))
					  												.toList();
		
		
		JsonArray response = futures.stream()
									.map(CompletableFuture::join)
									.filter(json -> json != null && !json.isJsonNull())
									.collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
		
		return response.toString();
	}

	private List<RequestDTO> constructAPIRequest(String olsSearchResult) {
		
		var responseObject = JsonParser.parseString(olsSearchResult).getAsJsonObject();
		
		if(Optional.ofNullable(responseObject)
				.map(response -> response.getAsJsonObject("response"))
				.map(response -> response.getAsJsonArray("docs"))				
				.filter(doc -> !doc.isEmpty())
				.isEmpty()) {
			logger.warn("Response does not contain any search result");
			return new ArrayList<RequestDTO>();
		}
		
		var solrDocs = responseObject.getAsJsonObject("response").getAsJsonArray("docs");
		
		return StreamSupport.stream(solrDocs.spliterator(), true)
										.filter(doc -> doc != null && !doc.isJsonNull())
										.map(doc -> {
											JsonObject jsonObject = doc.getAsJsonObject();
											String type = jsonObject.get("type").getAsString();
											String ontologyId = jsonObject.get("ontology_name").getAsString();
											String iri = jsonObject.get("iri").getAsString();
											ActionType actionType = switch(type) {
												case "class" -> ActionType.CLASSES_BY_ONTOLOGY_ID_AND_IRI;
												case "property" -> ActionType.PROPERTIES_BY_ONTOLOGY_ID_AND_IRI;
												case "individual" -> ActionType.INDIVIDUALS_BY_ONTOLOGY_ID_AND_IRI;
												default -> throw new IllegalArgumentException("Unexpected value: " + type);
											};
											RequestDTO request = new RequestDTO.Builder(actionType)
																			   .setArtefactId(ontologyId)
																			   .setResourceId(UriUtils.encode(iri, "UTF-8"))
																			   .build();
											return request;
										})
										.toList();
	}

	@Override
	public String postHandler(RequestDTO request, String response) {

		String results = "";

		List<ArtefactResource> artefactResources = new LinkedList<ArtefactResource>();

		var resources = JsonParser.parseString(response).getAsJsonArray();

		if (resources.isEmpty()) {
			logger.warn("Response does not contain any content");
			throw new NotFoundException(ErrorMessage.RESOURCE_NOT_FOUND);
		}

		for (JsonElement resource : resources) {
			if (resource == null || resource.isJsonNull()) {
				logger.debug("Skipping null resource");
				continue;
			}
			
			JsonArray ontologyTypes = resource.getAsJsonObject().getAsJsonArray("type");
			
			var resourceType = StreamSupport.stream(ontologyTypes.spliterator(), false)
											.map(JsonElement::getAsString)
											.map(String::toLowerCase)
											.map(type -> switch(type) {
												case "property" -> ArtefactResourceType.PROPERTY;
												case "individual" -> ArtefactResourceType.INDIVIDUAL;
												default -> ArtefactResourceType.CLASS;
											})
											.findFirst()
											.orElse(ArtefactResourceType.CLASS);
			
			request.setResourceType(resourceType);

			ArtefactResource artefactResource = artefactResourceMapper.mapJsonToDTO(request, resource.toString());

			logger.debug("Mapped Artefact Resource: {}", artefactResource);

			if (artefactResource != null) {
				artefactResources.add(artefactResource);
			}
		}

		ResponseDTO<List<ArtefactResource>> responseDto = new ResponseDTO<List<ArtefactResource>>();
		responseDto.setContext(Context.getContext());

		if (!artefactResources.isEmpty()) {
			if(request.getFormat().equals(FormatOption.jsonld)) {
				responseDto.setJsonResult(artefactResources);
			}else {
				responseDto.setOtherFormatResult(artefactResources);
			}
			results = ResponseConverter.convert(responseDto, request.getFormat(), null);
		}
		return results;
	}
	
	private JsonObject makeAPICall(RequestDTO request){
		String result = "";
		try {
			result = terminologyService.call(request);
		} catch (BadRequestException e) {
			logger.error("Invalid request for artefact: {} and iri: {}", request.getArtefactId(), request.getResourceId());
			return null;
		} catch (Exception e) {
			logger.error("Unexpected error occurred for artefact: {} and iri: {}", request.getArtefactId(), request.getResourceId());
			return null;
		}
		return JsonParser.parseString(result).getAsJsonObject();
	}

}
