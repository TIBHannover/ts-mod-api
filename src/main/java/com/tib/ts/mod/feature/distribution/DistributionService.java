package com.tib.ts.mod.feature.distribution;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
interface DistributionService {

	/**
     * Retrieves a specific distribution by its artefact identifier.
     *
     * @param request - the data transfer object containing the artefact ID
     * @return a formatted(@see FormatOption) String response containing the artefact details
     * @throws BadRequestException if the artefact ID or other paramters is invalid
     */
	public String getLatestDistributionByArtefactId(RequestDTO request) throws BadRequestException;
}
