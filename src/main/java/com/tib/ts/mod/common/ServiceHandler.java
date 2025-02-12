package com.tib.ts.mod.common;

import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface ServiceHandler {
	
	public String PreHandler(RequestDTO request);
	
	public String execute(RequestDTO request);
	
	public String PostHandler();

}
