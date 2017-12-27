package com.Configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppIntializer  extends AbstractAnnotationConfigDispatcherServletInitializer{


	@Override
	protected Class<?>[] getRootConfigClasses() {
	return new Class[]{DBConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
	return new String[]{"/"};

	}


}