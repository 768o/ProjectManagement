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

import com.xhSmart.model.User;
/**
 * 权限拦截器，所有权限都在这里做
 * @author lin
 *
 */
public class PermissionFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		HttpServletResponse httpServletResponse =(HttpServletResponse) response;
		String  path = httpServletRequest.getRequestURI();
		//System.out.println("test"+path);
		HttpSession session = httpServletRequest.getSession();
		if(path.contains(".css") || path.contains(".js") || path.contains(".png")|| path.contains(".jpg")|| path.contains(".ttf")|| path.contains(".woff")){
			chain.doFilter(request, response);
		}else{
			
			User user = (User)session.getAttribute("user");
			if(user!=null){
			if(user.getIsadmin() == 0){
				//System.out.println("test");
				if(((path.indexOf("getAllUser")+path.indexOf("department")+(path.indexOf("post"))+(path.indexOf("userTask"))+(path.indexOf("project/changeProjectState"))
						+(path.indexOf("allocation/toAddAllocation"))+(path.indexOf("project/toAddProject")))>0)){
					//System.out.println("test2");
					httpServletResponse.sendRedirect("/xhSmart/NoPermission.jsp");	
					return;
				}		
			}else {
				//System.out.println("test3");
			}
			
			}
			chain.doFilter(request, response);
			return;
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
