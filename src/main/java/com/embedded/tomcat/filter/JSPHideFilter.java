package com.embedded.tomcat.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter( urlPatterns = { "/*"} )
public class JSPHideFilter implements jakarta.servlet.Filter {
	@Override public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(httpServletRequest.getRequestURI().endsWith(".jsp")) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			chain.doFilter(request, response);
		}
	}
}
