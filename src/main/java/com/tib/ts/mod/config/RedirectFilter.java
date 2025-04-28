package com.tib.ts.mod.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Component
public class RedirectFilter {

	public FilterRegistrationBean<CrossOriginResourceSharingFilter> corsFilter() {
		FilterRegistrationBean<CrossOriginResourceSharingFilter> registrationBean = new FilterRegistrationBean<CrossOriginResourceSharingFilter>();
		
		registrationBean.setFilter(new CrossOriginResourceSharingFilter());
		registrationBean.addUrlPatterns("*");
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return registrationBean;
	}
}
