package com.valoyes.patterns.interceptingfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 * Esta clase es la que implementara el Intercepting Filter pattern
 * Su trabajo especifico sera utilizar el parametro User-Agent de la request
 * para filtrarla
 *
 */
@WebFilter("/browserFilter")
public class BrowserFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// se llama justo antes que el Filter es deleted
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String usertAgentHeader = ((HttpServletRequest) request).getHeader("User-Agent");
		// usamos contains en vez de equals, pq este parametro contiene otra informacion
		// ademas del navegador
		if(usertAgentHeader.contains("Chrome")) {
			chain.doFilter(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("badBrowser.jsp");
			dispatcher.forward(request, response);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// este metodo se llama once the filter is created by the Servlet Container, durante el creation process
	}

}
