package com.tib.ts.mod.artefact;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

/**
 * The {@code ArtefactService} interface defines the contract to manage 
 * and retrieve artefact data 
 * <p>
 * It defines operations for fetching all artefacts and retrieving artefacts by their unique identifier.
 * </p>
 */
interface ArtefactService {
	
	/**
     * Retrieves a list of all available artefacts based on the provided request details.
     *
     * @param request - the data transfer object containing input details
     * @return a formatted(@see FormatOption) String response representing all artefacts
     * @throws BadRequestException if the request is invalid or missing required parameters
     */
	public String getAllArtefact(RequestDTO request) throws BadRequestException;
	
	/**
     * Retrieves a specific artefact by its unique identifier.
     *
     * @param request - the data transfer object containing the artefact ID
     * @return a formatted(@see FormatOption) String response containing the artefact details
     * @throws BadRequestException if the artefact ID or other paramters is invalid
     */
	public String getArtefactByArtefactId(RequestDTO request) throws BadRequestException;

}
