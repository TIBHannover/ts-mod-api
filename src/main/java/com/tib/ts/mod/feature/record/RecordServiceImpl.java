package com.tib.ts.mod.feature.record;

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

/**
 * Implementation of the {@link RecordService} interface that handles 
 * artefactRecord retrieval operations.
 * <p>
 * This class handles artefactRecord data retrieval
 * </p>
 */
@Service
class RecordServiceImpl implements RecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(RecordService.class);
	
	@Autowired
	GetAllArtefactRecordHandler getAllArtefactRecordHandler;
	
	@Autowired
	GetArtefactRecordHandler getArtefactRecordHandler;
	
	/**
     * {@inheritDoc}
     * <p>
     * This method retrieves all available artefactsRecord.
     * </p>
     *
     * @throws BadRequestException if the request is null or invalid
     */
	@Override
	public String getAllArtefactRecord(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get all artefacts Record");

		// invoke preHandler for validating the request
		String validationMessage = getAllArtefactRecordHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getAllArtefactRecordHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		//invoke postHandler to process the response
		String modResponse = getAllArtefactRecordHandler.postHandler(request, olsResponse);
		
		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		return modResponse;
	}

	 /**
     * {@inheritDoc}
     * <p>
     * This method retrieves an artefact by its unique identifier.
     * </p>
     *
     * @throws BadRequestException if the artefact ID is missing or invalid
     */
	@Override
	public String getArtefactRecordByArtefactId(RequestDTO request) throws BadRequestException{
		logger.info("Received request to get all artefacts");

		// invoke preHandler for validating the request
		String validationMessage = getArtefactRecordHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getArtefactRecordHandler.execute(request);
		
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		String modResponse = getArtefactRecordHandler.postHandler(request, olsResponse);
		
		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		return modResponse;
	}

}
