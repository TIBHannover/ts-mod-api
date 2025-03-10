package com.tib.ts.mod.resource;

import org.apache.coyote.BadRequestException;

import com.tib.ts.mod.entities.dto.RequestDTO;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public interface ArtefactResourceService {

	public String getAllArtefactResource(RequestDTO request) throws BadRequestException;

	public String getArtefactResourceByArtefactIdAndResourceId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourceClassesByArtefactId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourceConceptByArtefactId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourcePropertiesByArtefactId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourceIndividualsByArtefactId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourceSchemesByArtefactId(RequestDTO request) throws BadRequestException;
	
	public String getArtefactResourceCollectionByArtefactId(RequestDTO request) throws BadRequestException;

}
