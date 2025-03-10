package com.tib.ts.mod.resource;

import java.util.LinkedList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.ArtefactResourceMapper;
import com.tib.ts.mod.entities.ArtefactResource;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class GetAllArtefactResourceHandler implements ServiceHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(GetAllArtefactResourceHandler.class);
	
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
		
		boolean isPaginationValid = Validation.ValidatePage(request.getPage(), request.getPageSize());
		
		if (!isPaginationValid) {
			logger.info(ErrorMessage.INVALID_PAGE_MSG, request.getPage(), request.getPageSize());
		}
		
		return isPaginationValid ? ErrorMessage.NO_ERROR : ErrorMessage.INVALID_PARAMETERS;
	}

	@Override
	public String execute(RequestDTO request) throws BadRequestException {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();

		String result = terminologyService.call(request);
		
		return result;
	}

	@Override
	public String postHandler(RequestDTO request, String response) {
		
		String results = "";
		
		List<ArtefactResource> artefactResources = new LinkedList<ArtefactResource>();
		
		var responseObject = JsonParser.parseString(response).getAsJsonObject();
		
		if (!responseObject.has("elements") || responseObject.get("elements").isJsonNull()) {
			logger.warn("Response does not contain any resources");
			return "";
		}
		
		var resources = responseObject.get("elements").getAsJsonArray();

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
			responseDto.setResult(artefactResources);
			results = ResponseConverter.convert(responseDto, request.getFormat());
		} 
		return results;
	}

}
