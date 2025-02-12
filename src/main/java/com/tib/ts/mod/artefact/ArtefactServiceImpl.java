package com.tib.ts.mod.artefact;

import org.springframework.stereotype.Service;

import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
class ArtefactServiceImpl implements ArtefactService {
	
	@Override
	public String getAllArtefact(RequestDTO request) {
		GetAllArtefactHandler getAllArtefactHandler = new GetAllArtefactHandler();
		
		getAllArtefactHandler.PreHandler(request);
		getAllArtefactHandler.execute(request);
		getAllArtefactHandler.PostHandler();
		
		return null;
	}

	@Override
	public String getArtefactByArtefactId(RequestDTO request) {
		GetArtefactHandler getArtefactHandler = new GetArtefactHandler();
		
		getArtefactHandler.PreHandler(request);
		getArtefactHandler.execute(request);
		getArtefactHandler.PostHandler();
		return null;
	}

}
