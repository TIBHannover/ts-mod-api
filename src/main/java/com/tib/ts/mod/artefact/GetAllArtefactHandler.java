package com.tib.ts.mod.artefact;

import org.springframework.stereotype.Service;

import com.tib.ts.mod.common.ServiceHandler;
import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class GetAllArtefactHandler implements ServiceHandler{

	@Override
	public String PreHandler(RequestDTO request) {
		
		return null;
	}

	@Override
	public String execute(RequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String PostHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
