package com.tib.ts.mod.record;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

/**
 * The {@code RecordService} interface defines the contract to manage 
 * and retrieve artefact record data 
 * <p>
 * It defines operations for fetching all artefacts record and retrieving artefacts record by their unique identifier.
 * </p>
 */
interface RecordService {
	
	/**
     * Retrieves a list of all available artefactsRecord based on the provided request details.
     *
     * @param request - the data transfer object containing input details
     * @return a formatted(@see FormatOption) String response representing all artefactsRecord
     * @throws BadRequestException if the request is invalid or missing required parameters
     */
	public String getAllArtefactRecord(RequestDTO request) throws BadRequestException;
	
	/**
     * Retrieves a specific artefactRecord by its unique identifier.
     *
     * @param request - the data transfer object containing the artefact ID
     * @return a formatted(@see FormatOption) String response containing the artefact details
     * @throws BadRequestException if the artefact ID or other paramters is invalid
     */
	public String getArtefactRecordByArtefactId(RequestDTO request) throws BadRequestException;

}
