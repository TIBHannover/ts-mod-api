package com.tib.ts.mod.artefact;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

interface ArtefactService {
	
	public String getAllArtefact(RequestDTO request) throws BadRequestException;
	
	public String getArtefactByArtefactId(RequestDTO request) throws BadRequestException;

}
