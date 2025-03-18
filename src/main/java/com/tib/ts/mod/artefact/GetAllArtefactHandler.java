package com.tib.ts.mod.artefact;

import java.util.LinkedList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.FormatOption;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

/**
 * Implementation of the {@link ServiceHandler} interface that provides
 * logic to get all artefacts.
 */
@Service
class GetAllArtefactHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetAllArtefactHandler.class);
	
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

		boolean isPaginationValid = Validation.ValidatePage(request.getPage(), request.getPageSize());
		boolean isDisplayValid = Validation.ValidateDisplay(request.getDisplay());
		
		if (!isPaginationValid) {
			logger.info(ErrorMessage.INVALID_PAGE_MSG, request.getPage(), request.getPageSize());
		}

		if (!isDisplayValid) {
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());
		}

		return (isPaginationValid && isDisplayValid) ? ErrorMessage.NO_ERROR : ErrorMessage.INVALID_PARAMETERS;
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

		String result = terminologyService.call(request);

		MappingRule rules = configLoader.mergeConfiguration(request.getDisplay(),
															AttributeFile.SEMANTIC_ARTEFACT,
															AttributeFile.DCAT_RESOURCE,
															AttributeFile.DCAT_DATA_SERVICE);

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

		List<SemanticArtefact> semanticArtefacts = new LinkedList<SemanticArtefact>();
		String results = "";
		try {
			var responseObject = JsonParser.parseString(response).getAsJsonObject();

			if (!responseObject.has("elements") || responseObject.get("elements").isJsonNull()) {
				logger.warn("Response does not contain any ontologies");
				return "";
			}

			var ontologies = responseObject.get("elements").getAsJsonArray();

			for (JsonElement ontology : ontologies) {
				if (ontology == null || ontology.isJsonNull()) {
					logger.debug("Skipping null ontology");
					continue;
				}
				
				SemanticArtefact semanticArtefact = mapper.mapJsonToDto(ontology.toString(), SemanticArtefact.class, request.getMappingRule());
				
				logger.debug("Mapped SemanticArtefact: {}", semanticArtefact);
				
				if (semanticArtefact != null) {
					semanticArtefacts.add(semanticArtefact);
				}
			}
			
			ResponseDTO<List<SemanticArtefact>> responseDto = new ResponseDTO<List<SemanticArtefact>>();
			
			if (!semanticArtefacts.isEmpty()) {
				responseDto.setResult(semanticArtefacts);
				
				if(request.getFormat().equals(FormatOption.jsonld)) {
					Context.addPaginationContext();
					responseDto.setView(helper.getView(request.getBaseUrl(), responseObject));
					responseDto.setId(request.getBaseUrl());
					responseDto.setType("Collection");
					responseDto.setTotalItems(responseObject.get("totalElements").getAsInt());
					responseDto.setItemsPerPage(responseObject.get("numElements").getAsInt());
				}
				
				responseDto.setContext(Context.getContext());
				results = ResponseConverter.convert(responseDto, request.getFormat());
			} 
		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return results;
	}

	

}
