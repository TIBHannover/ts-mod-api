package com.tib.ts.mod.common.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@AllArgsConstructor
public class MappingRule {
	
	private String jsonPath;
	
	private int priority;
	
	private String type;
	
}
