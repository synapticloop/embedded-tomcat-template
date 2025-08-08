package com.embedded.tomcat.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter( urlPatterns = { "/*"} )
public class Filter implements jakarta.servlet.Filter {
	@Override public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		System.out.println("Doing filter for request: " + httpServletRequest.getRequestURI());
		chain.doFilter(request, response);
	}
}
