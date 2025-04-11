package com.tib.ts.mod.feature.distribution;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.Helper;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.AttributeFile;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.DynamicConfigLoader;
import com.tib.ts.mod.common.mapper.MappingRule;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.SemanticArtefactDistribution;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.ActionType;
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
	Helper helper;
	
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
		boolean isartefactIdValid = Validation.ValidateURI(request.getArtefactId());
		
		if (!isDisplayValid)
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());
		
		if (!isartefactIdValid)
			logger.info(ErrorMessage.INVALID_ARTEFACT_ID_MSG, request.getArtefactId());

		return (isDisplayValid && isartefactIdValid) ? ErrorMessage.NO_ERROR : ErrorMessage.INVALID_PARAMETERS;
	}

	@Override
	public String execute(RequestDTO request) throws BadRequestException {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();
		
		/*
		 * ActionType defaultActionType = request.getOperationType();
		 * 
		 * request.setOperationType(ActionType.ONTOLOGIES_BY_ONTOLOGY_IRI);
		 * 
		 * String ontology = terminologyService.call(request);
		 * 
		 * String ontologyId = helper.fetchOntologyId(ontology);
		 * 
		 * if (ontologyId == null || ontologyId.isBlank()) throw new
		 * BadRequestException("Invalid artefactId provided.");
		 * 
		 * request.setOntologyId(ontologyId);
		 * request.setOperationType(defaultActionType);
		 */
		
		request.setOntologyId(request.getArtefactId());
		String result = terminologyService.call(request);
		
		MappingRule rules = configLoader.mergeConfiguration(String.join(",", request.getDisplay()),
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
				//ResponseDTO<SemanticArtefactDistribution> responseDto = new ResponseDTO<SemanticArtefactDistribution>();
				//responseDto.setContext(Context.getContext());
				semanticArtefactDistribution.setContext(Context.getContext());
				semanticArtefactDistribution.setSemanticArtefactDistributionType("mod:SemanticArtefactDistribution");
				//responseDto.setJsonResult(semanticArtefactDistribution);
				result = ResponseConverter.convert(semanticArtefactDistribution, request.getFormat(), request.getDisplay());
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return result;
	}

}
