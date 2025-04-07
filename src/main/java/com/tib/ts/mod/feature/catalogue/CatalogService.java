package com.tib.ts.mod.feature.catalogue;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

interface CatalogService {
	
	public String getCatalog(RequestDTO request) throws BadRequestException;

}
