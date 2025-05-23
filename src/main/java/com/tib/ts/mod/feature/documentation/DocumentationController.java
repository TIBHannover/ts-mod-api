package com.tib.ts.mod.feature.documentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@RestController
@Tag(name = "Documentation", description = "Get documentation about the service")
class DocumentationController {
	
	@GetMapping("/doc/api")
    @Operation(summary = "Get API Documentation", description = "Returns the API documentation link")
    public String showApiDocs(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("baseUrl") @Parameter(hidden = true) String baseUrl) {
		String forwardedPrefix = "/terminology/mod";
		forwardedPrefix = baseUrl.contains(forwardedPrefix) ? forwardedPrefix : "";
		baseUrl = baseUrl.contains("api") ? baseUrl.replace("api", "") : baseUrl;
		String redirectURL = baseUrl + "/swagger-ui/index.html";
		response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        response.setHeader("Location", forwardedPrefix+"/swagger-ui/index.html");
        return redirectURL;
    }
}
