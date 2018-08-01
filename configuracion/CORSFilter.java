package com.mpersd.spring.configuracion;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORSFilter
 */
//@WebFilter("/CORSFilter")
public class CORSFilter implements Filter {


	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtrando conexion de " + request.getRemoteHost());
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		resp.addHeader("Access-Control-Max-Age", "3600");
		resp.addHeader("Access-Control-Allow-Headers", "x-request-width");
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {}

}
