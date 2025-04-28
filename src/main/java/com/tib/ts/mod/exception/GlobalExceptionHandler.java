package com.tib.ts.mod.exception;

import org.apache.coyote.BadRequestException;
import org.apache.jena.shared.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleException(BadRequestException ex){
		return ResponseEntity.badRequest()
							 .body(ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleException(NotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleException(RuntimeException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleException(IllegalArgumentException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
	}
}
