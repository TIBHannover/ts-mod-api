package com.tib.ts.mod.common;

import org.apache.coyote.BadRequestException;

import com.google.gson.JsonElement;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface ServiceHandler {
	
	public String preHandler(RequestDTO request) throws BadRequestException;
	
	public String execute(RequestDTO request) throws BadRequestException;
	
	public <T> T postHandler(RequestDTO request, String response);

}
