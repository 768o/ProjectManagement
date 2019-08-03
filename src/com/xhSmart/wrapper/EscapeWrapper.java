package com.xhSmart.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang.StringEscapeUtils;
/**
 * 防止xss攻击
 * @author lin
 *
 */
public class EscapeWrapper extends HttpServletRequestWrapper{

	public EscapeWrapper(HttpServletRequest request) {
		super(request);
	}
	@Override
	public String getParameter(String name){
		String value = getRequest().getParameter(name);
		return StringEscapeUtils.escapeHtml(value);
	}
	@Override
	public String[] getParameterValues(String name){
		String[] values = super.getParameterValues(name);
		if( values != null){
		for(int i = 0; i < values.length; i++){
			values[i] = StringEscapeUtils.escapeHtml(values[i]);	
		}
		}
		return values; 
	}

}
