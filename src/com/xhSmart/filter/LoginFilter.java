package com.xhSmart.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登陆拦截器
 * 
 * @author lin
 *
 */
public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		HttpServletResponse httpServletResponse =(HttpServletResponse) response;
		String  path = httpServletRequest.getRequestURI();
		HttpSession session = httpServletRequest.getSession();
		if(path.contains(".css") || path.contains(".js") || path.contains(".png")|| path.contains(".jpg")|| path.contains(".ttf")|| path.contains(".woff")){
			chain.doFilter(request, response);
		}else{
		if(!("/xhSmart/login.jsp".equals(path)||"/xhSmart/user/login".equals(path))){
			if(session.getAttribute("user")==null){
				System.out.println(path+321);
				httpServletResponse.sendRedirect("/xhSmart/login.jsp");
			}else{
				chain.doFilter(request, response);
			}
			
		}else{
			chain.doFilter(request, response);
		}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
