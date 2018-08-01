package com.mpersd.spring.configuracion;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringInicio extends AbstractAnnotationConfigDispatcherServletInitializer {

	public SpringInicio() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SpringConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CORSFilter()};
	}

}
