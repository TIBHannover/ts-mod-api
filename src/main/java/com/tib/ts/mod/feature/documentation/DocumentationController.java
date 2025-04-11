package com.tib.ts.mod.feature.documentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Controller
@Tag(name = "Documentation", description = "Get documentation about the service")
class DocumentationController {
	
	/*
	 * @GetMapping("/doc/api") public void getAPIDocs(HttpServletResponse response)
	 * throws IOException { response.sendRedirect("/swagger-ui/index.html");
	 * 
	 * }
	 */
	
	/*@GetMapping("/doc/api")
	public String getAPIDocs() {
		return "forward:/swagger-ui/index.html";
	}
	*/
	
	@GetMapping("/doc/api")
    @Operation(summary = "Get API Documentation", description = "Returns the API documentation link")
    public ResponseEntity<Map<String, String>> showApiDocs() {
        Map<String, String> apiDocs = new HashMap<>();
        apiDocs.put("swagger-ui", "/swagger-ui/index.html");
        return ResponseEntity.ok(apiDocs);
    }
}
