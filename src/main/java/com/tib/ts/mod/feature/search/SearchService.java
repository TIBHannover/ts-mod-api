package com.tib.ts.mod.feature.search;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

interface SearchService {
	
	public String searchMetadataAndContent(RequestDTO request) throws BadRequestException;
	
	public String searchMetadata(RequestDTO request) throws BadRequestException;
	
	public String searchContent(RequestDTO request) throws BadRequestException;

}
