package com.tib.ts.mod.feature.resource;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/


@Service
class ArtefactResourceServiceImpl implements ArtefactResourceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtefactResourceServiceImpl.class);
	
	@Autowired
	GetAllArtefactResourceHandler getAllArtefactResourceHandler;

	@Override
	public String getAllArtefactResource(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get all artefact resource ");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourceByArtefactIdAndResourceId(RequestDTO request) throws BadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArtefactResourceClassesByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource classes by artefact Id");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourceConceptByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource concepts by artefact Id");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourcePropertiesByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource properties by artefact Id");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourceIndividualsByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource individuals by artefact Id");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourceSchemesByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource conceptScheme by artefact Id");

		return processArtefactResource(request);
	}

	@Override
	public String getArtefactResourceCollectionByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get artefact resource collection by artefact Id");

		return processArtefactResource(request);
	}
	
	/**
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	private String processArtefactResource(RequestDTO request) throws BadRequestException {
		
		// invoke preHandler for validating the request
		String validationMessage = getAllArtefactResourceHandler.preHandler(request);

		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}

		// invoke execute to retrieve the data
		String olsResponse = getAllArtefactResourceHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		// invoke postHandler to process the response
		String modResponse = getAllArtefactResourceHandler.postHandler(request, olsResponse);

		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		return modResponse;
	}

}
