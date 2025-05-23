package com.tib.ts.mod.feature.catalogue;

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
class CatalogServiceImpl implements CatalogService {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	GetCatalogHandler getCatalogHandler;
	
	@Override
	public String getCatalog(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get all artefacts Record");

		// invoke preHandler for validating the request
		String validationMessage = getCatalogHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getCatalogHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		//invoke postHandler to process the response
		String modResponse = getCatalogHandler.postHandler(request, olsResponse);
		
		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		return modResponse;
	}

}
