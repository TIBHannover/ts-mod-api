package com.tib.ts.mod.artefact;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class ArtefactServiceImpl implements ArtefactService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtefactService.class);
	
	@Autowired
	GetAllArtefactHandler getAllArtefactHandler;
	
	@Autowired
	GetArtefactHandler getArtefactHandler;
	
	@Override
	public List<SemanticArtefact> getAllArtefact(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get all artefacts");

		// invoke preHandler for validating the request
		String validationMessage = getAllArtefactHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getAllArtefactHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		//invoke postHandler to process the response
		List<SemanticArtefact> modResponse = getAllArtefactHandler.postHandler(olsResponse);

		return modResponse;
	}

	@Override
	public SemanticArtefact getArtefactByArtefactId(RequestDTO request) throws BadRequestException{
		logger.info("Received request to get all artefacts");

		// invoke preHandler for validating the request
		String validationMessage = getArtefactHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getArtefactHandler.execute(request);
		
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		SemanticArtefact modResponse = getArtefactHandler.postHandler(olsResponse);
		return modResponse;
	}

}
