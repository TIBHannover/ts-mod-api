package com.tib.ts.mod.common;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

/**
 * The {@code ServiceHandler} interface defines the contract for handling 
 * service requests with pre-processing, execution, and post-processing logic.
 */
public interface ServiceHandler {
	
	/**
	 * Performs pre-processing logic on the given request.
	 *
	 * @param request - data transfer object containing input details
	 * @return a validation message for further steps
	 * @throws BadRequestException if the request is invalid or malformed
	 */
	public String preHandler(RequestDTO request) throws BadRequestException;
	
	/**
     * Executes the core business logic using the provided request data.
     *
     * @param request - data transfer object containing input details
     * @return a response representing the outcome of the ols rest-api call
     * @throws BadRequestException if the request is invalid or malformed
     */
	public String execute(RequestDTO request) throws BadRequestException;
	
	 /**
     * Performs post-processing on the given request and response data.
     *
     * @param <T> the generic type of the post-processed data
     * @param request - the original request data transfer object
     * @param response - the response data generated from the execute step
     * @return the post-processed data of type {@code T}
     */
	public <T> T postHandler(RequestDTO request, String response);

}
