package com.tib.ts.mod.feature.catalogue;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.constants.AttributeFile;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.converter.ResponseConverter;
import com.tib.ts.mod.common.mapper.DynamicConfigLoader;
import com.tib.ts.mod.common.mapper.MappingRule;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefactCatalog;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class GetCatalogHandler implements ServiceHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GetCatalogHandler.class);
	
	@Autowired
	OlsRepository terminologyService;
	
	@Autowired
	MetadataMapper mapper;
	
	@Autowired
	DynamicConfigLoader configLoader;

	@Override
	public String preHandler(RequestDTO request) throws BadRequestException {
		return ErrorMessage.NO_ERROR;
	}

	@Override
	public String execute(RequestDTO request) throws BadRequestException {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();
		
		String result = terminologyService.call(request);
		
		MappingRule rules = configLoader.mergeConfiguration(String.join(",", request.getDisplay()),
															AttributeFile.SEMANTIC_ARTEFACT_CATALOG);

		request.setMappingRule(rules);

		return result;
	}

	@Override
	public String postHandler(RequestDTO request, String response) {
		
		SemanticArtefactCatalog semanticArtefactCatalog = null;
		String result = "";
		try {
			semanticArtefactCatalog = mapper.mapJsonToDto(response, SemanticArtefactCatalog.class, request.getMappingRule());
			
			semanticArtefactCatalog.setCatalogId(request.getBaseUrl());
			semanticArtefactCatalog.setCatalogType("mod:SemanticArtefactCatalog");
			
			logger.debug("Mapped SemanticArtefactCatalog: {}", semanticArtefactCatalog);
			
			if (semanticArtefactCatalog != null) {
				semanticArtefactCatalog.setContext(Context.getContext());
				result = ResponseConverter.convert(semanticArtefactCatalog, request.getFormat(), request.getDisplay());
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return result;
	}

}
