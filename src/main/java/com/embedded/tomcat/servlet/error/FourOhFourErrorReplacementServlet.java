package com.embedded.tomcat.servlet.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * <p>This servlet will attempt to look up a JSP with the path of the requested
 * URL path.</p>
 *
 * <p><string>!!! WARNING  !!!</string> This can be incredibly dangerous as it
 * can walk the directory tree (including the normally hidden <code>WEB-INF</code>
 * directory... but is here as an example.</p>
 */
@WebServlet(name="FourOhFourErrorReplacementServlet", urlPatterns = { "/error/404" } )
public class FourOhFourErrorReplacementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);

		// these are the attributes that you can retrieve from the incoming request
		Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
		Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
		String servletName = (String) req.getAttribute("jakarta.servlet.error.servlet_name");
		String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");
		String errorMessage = (String) req.getAttribute("jakarta.servlet.error.message");

		if(requestUri.endsWith("/") && !requestUri.startsWith("/WEB-INF")) {
			String testJSP = requestUri.substring(0, requestUri.length() - 1) + ".jsp";
			URL resource = req.getServletContext().getResource(testJSP);
			if(resource != null) {
				try {
					RequestDispatcher requestDispatcher = req.getRequestDispatcher(testJSP);
					requestDispatcher.include(req, resp);
					return;
				} catch (ServletException | IOException e) {
					// there was an issue - perhaps we couldn't look up the JSP
					// just carry on
				}
			}
		}

		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.println("4-oh-4 not found");
	}
}
