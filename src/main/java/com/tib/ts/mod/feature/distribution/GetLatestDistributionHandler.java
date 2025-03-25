package com.tib.ts.mod.feature.distribution;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.AttributeFile;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.DynamicConfigLoader;
import com.tib.ts.mod.common.mapper.MappingRule;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefactDistribution;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetLatestDistributionHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetLatestDistributionHandler.class);
	
	@Autowired
	OlsRepository terminologyService;
	
	@Autowired
	MetadataMapper mapper;
	
	@Autowired
	DynamicConfigLoader configLoader;
	
	@Autowired
	public GetLatestDistributionHandler(OlsRepository terminologyService, MetadataMapper mapper,
			DynamicConfigLoader configLoader) {
		this.terminologyService = terminologyService;
		this.mapper = mapper;
		this.configLoader = configLoader;
	}

	@Override
	public String preHandler(RequestDTO request) throws BadRequestException {
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
		
		MappingRule rules = configLoader.mergeConfiguration(request.getDisplay(),
															AttributeFile.SEMANTIC_ARTEFACT_DISTRIBUTION,
															AttributeFile.DCAT_DISTRIBUTION,
															AttributeFile.DCAT_RESOURCE,
															AttributeFile.DCAT_DATA_SERVICE,
															AttributeFile.DCAT_DATA_SET);

		request.setMappingRule(rules);
		
		return result;
	}

	@Override
	public String postHandler(RequestDTO request, String response) {
		SemanticArtefactDistribution semanticArtefactDistribution = null;
		String result = "";
		try {
			semanticArtefactDistribution = mapper.mapJsonToDto(response, SemanticArtefactDistribution.class, request.getMappingRule());
			
			logger.debug("Mapped SemanticArtefactDistribution: {}", semanticArtefactDistribution);
			
			if (semanticArtefactDistribution != null) {
				semanticArtefactDistribution.setContext(Context.getContext());
				result = ResponseConverter.convert(semanticArtefactDistribution, request.getFormat());
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return result;
	}

}
