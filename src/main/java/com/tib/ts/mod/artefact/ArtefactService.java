package com.tib.ts.mod.artefact;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;
import com.tib.ts.mod.entities.dto.ResponseDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

interface ArtefactService {
	
	public ResponseDTO getAllArtefact(RequestDTO request) throws BadRequestException;
	
	public String getArtefactByArtefactId(RequestDTO request) throws BadRequestException;

}
