package com.tib.ts.mod.feature.record;

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
import com.tib.ts.mod.entities.SemanticArtefactCatalogRecord;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.ActionType;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

/**
 * Implementation of the {@link ServiceHandler} interface that provides
 * logic to get artefactRecord.
 */
@Service
class GetArtefactRecordHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetArtefactRecordHandler.class);
	
	@Autowired
	OlsRepository terminologyService;
	
	@Autowired
	MetadataMapper mapper;
	
	@Autowired
	DynamicConfigLoader configLoader;
	
	@Autowired
	Helper helper;

	/**
     * {@inheritDoc}
     * <p>
     * This method performs request validation before execution.
     * </p>
     *
     * @throws BadRequestException if the request data is null or invalid
     */
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

	/**
     * {@inheritDoc}
     * <p>
     * Executes the ols api call and load configuration for attribute mapping.
     * </p>
     *
     * @throws BadRequestException if the request data does not meet business rules
     */
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
															AttributeFile.DCAT_RESOURCE,
															AttributeFile.DCAT_CATALOG_RECORD,
															AttributeFile.SEMANTIC_ARTEFACT_CATALOG_RECORD);

		request.setMappingRule(rules);
		
		return result;
	}

	/**
     * {@inheritDoc}
     * <p>
     * This method process the ols response data to extract metadata.
     * </p>
     *
     */
	@Override
	public String postHandler(RequestDTO request, String response) {
		
		SemanticArtefactCatalogRecord semanticArtefactCatalogRecord = null;
		String result = "";
		try {
			semanticArtefactCatalogRecord = mapper.mapJsonToDto(response, SemanticArtefactCatalogRecord.class, request.getMappingRule());
			
			logger.debug("Mapped SemanticArtefactCatalogRecord: {}", semanticArtefactCatalogRecord);
			
			if (semanticArtefactCatalogRecord != null) {
				ResponseDTO<SemanticArtefactCatalogRecord> responseDto = new ResponseDTO<SemanticArtefactCatalogRecord>();
				responseDto.setContext(Context.getContext());
				semanticArtefactCatalogRecord.setSemanticArtefactCatalogRecordType("mod:SemanticArtefactCatalogRecord");
				responseDto.setJsonResult(semanticArtefactCatalogRecord);
				result = ResponseConverter.convert(responseDto, request.getFormat(), request.getDisplay());
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return result;
	}

}
