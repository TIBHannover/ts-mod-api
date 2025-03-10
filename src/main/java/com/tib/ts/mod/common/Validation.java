package com.tib.ts.mod.common;

import org.springframework.stereotype.Component;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class Validation {
	
	public static boolean ValidatePage(Integer page, Integer pageSize) {
		return page != null && pageSize != null && page >= 0 && pageSize >=1;
	}
	
	public static boolean ValidateDisplay(String display) {		
		return true; //display != null && (display.equalsIgnoreCase("all") || display.equalsIgnoreCase("default"));
	}

}
