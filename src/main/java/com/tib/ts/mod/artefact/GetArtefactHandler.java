package com.tib.ts.mod.artefact;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetArtefactHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetArtefactHandler.class);
	
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

		boolean isDisplayValid = Validation.ValidateDisplay(request.getDisplay());

		if (!isDisplayValid)
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());

		return (isDisplayValid) ? "" : ErrorMessage.INVALID_PARAMETERS;
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
		
		SemanticArtefact semanticArtefact = null;
		String result = "";
		try {
			semanticArtefact = mapper.mapJsonToDto(response, SemanticArtefact.class);
			
			logger.debug("Mapped SemanticArtefact: {}", semanticArtefact);
			
			if (semanticArtefact != null) {
				semanticArtefact.setContext(Context.getContext());
				result = ResponseConverter.convert(semanticArtefact, request.getFormat());
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return result;
	}

}
