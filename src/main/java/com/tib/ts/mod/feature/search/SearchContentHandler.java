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
import com.tib.ts.mod.common.Helper;
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
		
		return olsSearchResult;
	}

	
	@Override
	public String postHandler(RequestDTO request, String response) {

		String results = "";

		List<ArtefactResource> artefactResources = new LinkedList<ArtefactResource>();

		var responseObject = JsonParser.parseString(response).getAsJsonObject();

		if (!responseObject.has("elements") || responseObject.get("elements").isJsonNull()) {
			logger.warn("Response does not contain any resources");
			throw new NotFoundException(ErrorMessage.RESOURCE_NOT_FOUND);
		}

		var resources = responseObject.get("elements").getAsJsonArray();

		if (resources.isEmpty()) {
			logger.warn("Response does not contain any resources");
			throw new NotFoundException(ErrorMessage.RESOURCE_NOT_FOUND);
		}

		for (JsonElement resource : resources) {
			if (resource == null || resource.isJsonNull()) {
				logger.debug("Skipping null resource");
				continue;
			}

			ArtefactResource artefactResource = artefactResourceMapper.mapJsonToDTO(request, resource.toString());

			logger.debug("Mapped Artefact Resource: {}", artefactResource);

			if (artefactResource != null) {
				artefactResources.add(artefactResource);
			}
		}

		ResponseDTO<List<ArtefactResource>> responseDto = new ResponseDTO<List<ArtefactResource>>();
		responseDto.setContext(Context.getContext());

		if (!artefactResources.isEmpty()) {

			if (request.getFormat().equals(FormatOption.jsonld)) {
				responseDto.setJsonResult(artefactResources);
				Context.addPaginationContext();
				responseDto.setView(helper.getView(request.getBaseUrl(), responseObject));
				responseDto.setId(request.getBaseUrl());
				responseDto.setType("Collection");
				responseDto.setTotalItems(responseObject.get("totalElements").getAsInt());
				responseDto.setItemsPerPage(responseObject.get("numElements").getAsInt());
			} else {
				responseDto.setOtherFormatResult(artefactResources);
			}

			responseDto.setContext(Context.getContext());
			results = ResponseConverter.convert(responseDto, request.getFormat(), null);
		}
		return results;
	}
}
