package com.tib.ts.mod.feature.search;

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
class SearchServiceImpl implements SearchService{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	SearchMetadataAndContentHandler searchMetadataAndContentHandler;
	
	@Autowired
	SearchContentHandler searchContentHandler;

	@Override
	public String searchMetadataAndContent(RequestDTO request) throws BadRequestException {
		logger.info("Received request to search metadata and content");

		return processSearch(request);
	}

	@Override
	public String searchMetadata(RequestDTO request) throws BadRequestException {
		logger.info("Received request to search metadata");

		return processSearch(request);
	}

	@Override
	public String searchContent(RequestDTO request) throws BadRequestException {
		// invoke preHandler for validating the request
		String validationMessage = searchContentHandler.preHandler(request);

		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}

		// invoke execute to retrieve the data
		String olsResponse = searchContentHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		// invoke postHandler to process the response
		String modResponse = searchContentHandler.postHandler(request, olsResponse);

		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		return modResponse;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	private String processSearch(RequestDTO request) throws BadRequestException {
		// invoke preHandler for validating the request
		String validationMessage = searchMetadataAndContentHandler.preHandler(request);
		
		if (!validationMessage.isBlank()) {
			logger.info(ErrorMessage.VALIDATION_EXCEPTION_MSG, validationMessage);
			throw new BadRequestException(validationMessage);
		}
		
		// invoke execute to retrieve the data
		String olsResponse = searchMetadataAndContentHandler.execute(request);
		if (olsResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}
		
		//invoke postHandler to process the response
		String modResponse = searchMetadataAndContentHandler.postHandler(request, olsResponse);
		
		if (modResponse == null) {
			throw new BadRequestException(ErrorMessage.INVALID_PARAMETERS);
		}

		return modResponse;
	}

}
