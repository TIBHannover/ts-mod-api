package com.tib.ts.mod.common;

import com.google.gson.JsonElement;
import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface ServiceHandler {
	
	public String preHandler(RequestDTO request);
	
	public String execute(RequestDTO request);
	
	public ResponseDTO postHandler(String response);

}
