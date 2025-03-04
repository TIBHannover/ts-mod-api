package com.tib.ts.mod.artefact;

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
import com.tib.ts.mod.common.mapper.MetadataMapper;
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
class GetAllArtefactHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetAllArtefactHandler.class);
	
	private static final String EMPTY_STRING = "";

	@Autowired
	OlsRepository terminologyService;

	@Autowired
	MetadataMapper mapper;

	@Override
	public String preHandler(RequestDTO request) {
		if (request == null) {
			logger.info(ErrorMessage.NULL_REQUEST_MSG);
			return ErrorMessage.INVALID_PARAMETERS;
		}

		boolean isPaginationValid = Validation.ValidatePage(request.getPage(), request.getPageSize());
		boolean isDisplayValid = Validation.ValidateDisplay(request.getDisplay());

		if (!isPaginationValid)
			logger.info(ErrorMessage.INVALID_PAGE_MSG, request.getPage(), request.getPageSize());

		if (!isDisplayValid)
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());

		return (isPaginationValid && isDisplayValid) ? EMPTY_STRING : ErrorMessage.INVALID_PARAMETERS;
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

		List<SemanticArtefact> semanticArtefacts = new LinkedList<SemanticArtefact>();
		String results = EMPTY_STRING;
		try {
			var responseObject = JsonParser.parseString(response).getAsJsonObject();

			if (!responseObject.has("elements") || responseObject.get("elements").isJsonNull()) {
				logger.warn("Response does not contain any ontologies");
				return EMPTY_STRING;
			}

			var ontologies = responseObject.get("elements").getAsJsonArray();

			for (JsonElement ontology : ontologies) {
				if (ontology == null || ontology.isJsonNull()) {
					logger.debug("Skipping null ontology");
					continue;
				}
				SemanticArtefact semanticArtefact = mapper.mapJsonToDto(ontology.toString(), SemanticArtefact.class);
				
				logger.debug("Mapped SemanticArtefact: {}", semanticArtefact);
				
				if (semanticArtefact != null) {
					semanticArtefact.setContext(Context.getContext());
					semanticArtefacts.add(semanticArtefact);
				}
			}
			
			/*
			 * ResponseDTO<List<SemanticArtefact>> responseDto = new
			 * ResponseDTO<List<SemanticArtefact>>();
			 * responseDto.setContext(Context.getContext());
			 * if(!semanticArtefacts.isEmpty()) { responseDto.setResult(semanticArtefacts);
			 * results = ResponseConverter.convert(responseDto, request.getFormat()); }
			 */
			
			if(!semanticArtefacts.isEmpty()) {
				results = ResponseConverter.convert(semanticArtefacts, request.getFormat());
			}
		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return results;
	}

}
