package com.tib.ts.mod.artefact;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.tib.ts.mod.common.ConfigLoader;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.entities.enums.AttributeType;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetAllArtefactHandler implements ServiceHandler{
	
	@Autowired
	OlsRepository terminologyService;
	
	private static final Logger logger = LoggerFactory.getLogger(GetAllArtefactHandler.class);

	@Override
	public String preHandler(RequestDTO request) {
		if (request == null) {
			logger.info(ErrorMessage.NULL_REQUEST_MSG);
			return ErrorMessage.INVALID_PARAMETERS;
		}

		boolean isPaginationValid = Validation.ValidatePage(request.getPage(), request.getPageSize());
		boolean isDisplayValid = Validation.ValidateDisplay(request.getDisplay());

		if (!isPaginationValid)
			logger.info(ErrorMessage.INVALID_PAGE_MSG, request.getPage(), request.getPageSize());

		if (!isDisplayValid)
			logger.info(ErrorMessage.INVALID_DISPLAY_MSG, request.getDisplay());

		return (isPaginationValid && isDisplayValid) ? "" : ErrorMessage.INVALID_PARAMETERS;
	}

	@Override
	public String execute(RequestDTO request) {
		if (request == null || request.getOperationType() == null)
			throw new IllegalArgumentException();

		String result = switch (request.getOperationType()) {
			case ONTOLOGIES -> terminologyService.getOntologies();
			default -> throw new IllegalArgumentException();
		};
		
		return result;
	}

	@Override
	public ResponseDTO postHandler(String response) {
		Map<String, Object> config = new HashMap<String, Object>();
		try {
			config = ConfigLoader.loadConfig("ontology-config.yaml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//SemanticArtefact result = MetadataMapper.mapJsonToDto(response, config, SemanticArtefact.class);
		
		
		
		ResponseDTO res = new ResponseDTO();
		String at = "ACCESS_URL";
		String ct = "CONFORMS_TO";
		res.setAccessUrl(Map.ofEntries(
								Map.entry("@id", "http://localhost:8080"),
								Map.entry("@type", AttributeType.valueOf(at).getType())));
		res.setConformsTo(Map.ofEntries(
				Map.entry("@id", "Deepan"),
				Map.entry("@type", AttributeType.valueOf(ct).getType())));
		
		res.setId("something");
		res.setContext(Context.getContext());
		return res;
	}

}
