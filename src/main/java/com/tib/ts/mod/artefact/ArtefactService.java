package com.tib.ts.mod.artefact;

import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

interface ArtefactService {
	
	public String getAllArtefact(RequestDTO request);
	
	public String getArtefactByArtefactId(RequestDTO request);

}
