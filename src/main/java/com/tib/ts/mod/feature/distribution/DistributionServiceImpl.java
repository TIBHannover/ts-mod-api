package com.tib.ts.mod.feature.distribution;

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
class DistributionServiceImpl implements DistributionService{
	
	private static final Logger logger = LoggerFactory.getLogger(DistributionServiceImpl.class);
	
	private GetLatestDistributionHandler getLatestDistributionHandler;
	
	@Autowired
	public DistributionServiceImpl(GetLatestDistributionHandler getLatestDistributionHandler) {
		this.getLatestDistributionHandler = getLatestDistributionHandler;
	}

	@Override
	public String getLatestDistributionByArtefactId(RequestDTO request) throws BadRequestException {
		logger.info("Received request to get latest distribution");

		// invoke preHandler for validating the request
		String validationMessage = getLatestDistributionHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = getLatestDistributionHandler.execute(request);
		
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		String modResponse = getLatestDistributionHandler.postHandler(request, olsResponse);
		
		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		return modResponse;
	}

}
