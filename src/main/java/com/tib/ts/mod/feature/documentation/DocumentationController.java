package com.tib.ts.mod.feature.documentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Controller
class DocumentationController {
	
	/*
	 * @GetMapping("/doc/api") public void getAPIDocs(HttpServletResponse response)
	 * throws IOException { response.sendRedirect("/swagger-ui/index.html");
	 * 
	 * }
	 */
	
	@GetMapping("/doc/api")
	public String getAPIDocs() {
		return "forward:/swagger-ui/index.html";
	}
}
