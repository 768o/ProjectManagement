package com.xhSmart.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 暂时没用的类
 * @deprecated
 * @author lin
 *
 */
public class PermissionHandlerInterceptor implements HandlerInterceptor{
	
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public void postHandle1(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
				return true;
		// TODO Auto-generated method stub
		
//		String path = ((HttpServletRequest)request).getRequestURI();
//		//System.out.println(path);
//		if(path.contains(".css") || path.contains(".js") || path.contains(".png")|| path.contains(".jpg")|| path.contains(".ttf")|| path.contains(".woff")){
//			return true;
//		}else{
//		System.out.println(path);
//		HttpSession session = ((HttpServletRequest) request).getSession();
//		User user = (User)session.getAttribute("user");
//		if(user.getIsadmin()==0){
//			
//		}
//		}
//		return true;
//	}
	    //response.sendRedirect("/xhSmart/NoPermission.jsp");
		
}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
   
	
	
