package com.tib.ts.mod.feature.documentation;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestController
class DocumentationController {
	
	@GetMapping("/doc/api")
	public void getAPIDocs(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui/index.html");
	}

}
