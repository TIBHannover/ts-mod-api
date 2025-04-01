package com.tib.ts.mod.feature.resource;

import java.util.LinkedList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
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
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetArtefactResourceClassHandler implements ServiceHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(GetArtefactResourceClassHandler.class);
	
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
		
		boolean isartefactIdValid = Validation.ValidateURI(request.getArtefactId());
		
		if (!isartefactIdValid)
			logger.info(ErrorMessage.INVALID_ARTEFACT_ID_MSG, request.getArtefactId());
		
		return isartefactIdValid ? ErrorMessage.NO_ERROR : ErrorMessage.INVALID_PARAMETERS;
	}

	@Override
	public String execute(RequestDTO request) throws BadRequestException {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();

		ActionType defaultActionType = request.getOperationType();

		request.setOperationType(ActionType.ONTOLOGIES_BY_ONTOLOGY_IRI);

		String ontology = terminologyService.call(request);

		String ontologyId = helper.fetchOntologyId(ontology);

		if (ontologyId == null || ontologyId.isBlank())
			throw new BadRequestException("Invalid artefactId provided.");

		request.setOntologyId(ontologyId);
		request.setOperationType(defaultActionType);
		
		String result = terminologyService.call(request);
		
		return result;
	}

	@Override
	public String postHandler(RequestDTO request, String response) {

		String result = "";

		ArtefactResource artefactResource = null;

		artefactResource = artefactResourceMapper.mapJsonToDTO(request, response);

		logger.debug("Mapped Artefact Resource: {}", artefactResource);

		
		artefactResource.setContext(Context.getContext());
		result = ResponseConverter.convert(artefactResource, request.getFormat());

		
		return result;
	}

}
