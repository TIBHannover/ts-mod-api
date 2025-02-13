package com.tib.ts.mod.common;

import com.google.gson.JsonElement;
import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface ServiceHandler {
	
	public String preHandler(RequestDTO request);
	
	public JsonElement execute(RequestDTO request);
	
	public String postHandler(JsonElement response);

}
