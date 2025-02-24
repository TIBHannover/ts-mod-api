package com.tib.ts.mod.common.mapper;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
@Component
public class SemanticArtefactId {
	
	private String jsonPath;
	
	private int priority;

}
