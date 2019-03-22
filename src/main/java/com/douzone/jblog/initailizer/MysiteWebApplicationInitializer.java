package com.douzone.jblog.initailizer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.jblog.config.AppConfig;
import com.douzone.jblog.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	// <!-- Context Load Listener -->
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
	
	//<!-- Front Controller -->
	//<!-- DispatcherServlet -->
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	// <!-- encoding filter(한글처리, UTF-8) -->
	@Override
	protected Filter[] getServletFilters() {		
		return new Filter[] {new CharacterEncodingFilter("UTF-8", true)};
	}

	// <!-- Common Error Pages -->
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		return dispatcherServlet;
	}
}
