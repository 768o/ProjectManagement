package com.xhSmart.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
/**
 * 查看运行时间的拦截器，没什么用可删
 * @author lin
 *
 */
public class RunTimeFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
	    Logger logger = Logger.getLogger(requestURI);
		long begin = System.currentTimeMillis();
		chain.doFilter(request, response);
		long end=System.currentTimeMillis();
		logger.info(requestURI+"运行时间" + (end - begin) + "毫秒");

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
