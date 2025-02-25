package com.tib.ts.mod.common.constants;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class ErrorMessage {
	
	public static final String INVALID_PARAMETERS = "The provided parameters are incorrect.";
	public static final String NULL_REQUEST_MSG = "Request is null";
	public static final String INVALID_PAGE_MSG = "Invalid Page parameters: page={}, pageSize={}";
	public static final String INVALID_DISPLAY_MSG = "Invalid display parameter: display{}";
	public static final String VALIDATION_EXCEPTION_MSG = "Validation failed: {}";
	public static final String MAPPER_EXCEPTION_MSG = "Error mapping JSON to response: {}";
	public static final String MAPPER_FIELD_EXCEPTION_MSG = "Error setting field value: {}";
	public static final String MAPPER_RESPONSE_CONSTRUCT_EXCEPTION_MSG = "Error constructing response: {}";
	public static final String MAPPER_HANDLE_LIST_EXCEPTION_MSG = "Error handling list field: {}";
	public static final String MAPPER_HANDLE_MAP_EXCEPTION_MSG = "Error handling map field: {}";
	public static final String OLS_EXCEPTION_MSG = "Expected at least 1 result for solr getFirst";
}
