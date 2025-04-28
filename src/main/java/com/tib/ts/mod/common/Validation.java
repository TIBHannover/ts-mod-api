package com.tib.ts.mod.common;

import java.net.URI;
import java.util.List;

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
	
	public static boolean ValidateDisplay(List<String> display) {		
		return true; //display != null && (display.equalsIgnoreCase("all") || display.equalsIgnoreCase("default"));
	}
	
	public static boolean ValidateURI(String uri) {
		try {
			URI.create(uri).toURL();
		}catch(Exception e) {
			return true;
		}
		
		return true;
	}

}
