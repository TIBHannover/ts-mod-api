package com.tib.ts.mod.artefact;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.common.Validation;
import com.tib.ts.mod.common.constants.ErrorMessage;
import com.tib.ts.mod.common.mapper.MetadataMapper;
import com.tib.ts.mod.entities.Context;
import com.tib.ts.mod.entities.SemanticArtefact;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;
import com.tib.ts.mod.repository.OlsRepository;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetAllArtefactHandler implements ServiceHandler {

	@Autowired
	OlsRepository terminologyService;

	@Autowired
	MetadataMapper mapper;

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
	public List<SemanticArtefact> postHandler(String response) {

		List<SemanticArtefact> results = new LinkedList<SemanticArtefact>();
		try {
			var responseObject = JsonParser.parseString(response).getAsJsonObject();

			if (!responseObject.has("_embedded") || responseObject.get("_embedded").isJsonNull()) {
				logger.warn("Response does not contain any ontologies");
				return results;
			}

			var embedded = responseObject.get("_embedded").getAsJsonObject();

			if (!embedded.has("ontologies") || embedded.get("ontologies").isJsonNull()) {
				logger.warn("Response does not contain any ontologies");
				return results;
			}

			var ontologies = embedded.getAsJsonArray("ontologies");

			for (JsonElement ontology : ontologies) {
				if (ontology == null || ontology.isJsonNull()) {
					logger.debug("Skipping null ontology");
					continue;
				}
				SemanticArtefact result = mapper.mapJsonToDto(ontology.toString(), SemanticArtefact.class);
				logger.debug("Mapped SemanticArtefact: {}", result);
				result.setContext(Context.getContext());
				results.add(result);
			}

		} catch (Exception e) {
			logger.error("Error processing response in postHandler", e);
		}

		return results;
	}

}
