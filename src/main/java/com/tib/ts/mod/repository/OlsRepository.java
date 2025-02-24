package com.tib.ts.mod.repository;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface OlsRepository {
	
	public String call(RequestDTO request);

}
