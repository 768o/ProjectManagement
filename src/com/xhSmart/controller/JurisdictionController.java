package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xhSmart.model.Jurisdiction;
import com.xhSmart.service.JurisdictionService;
/**
 * 
 * 负责权限功能的处理（这个类暂时不会用到）
 * 
 * @deprecated
 * @author lin
 *
 */
@Controller
@RequestMapping("/jurisdiction")
public class JurisdictionController {

	@Autowired
	private JurisdictionService Service;
	
	/**
	 * 获取所有列表
	 * @deprecated
	 * @param request
	 * @return allJurisdiction.jsp
	 */
	@RequestMapping("/getAllJurisdiction")
	public String getAll(HttpServletRequest request){
		
		List<Jurisdiction> findAll = Service.findAll();
		
		request.setAttribute("jurisdictionList", findAll);
		return "/jurisdiction/allJurisdiction";
	}
	
	/**
	 * 跳转到添加权限界面
	 * @deprecated
	 * @param request
	 * @return addJurisdiction.jsp
	 */
	@RequestMapping("/toAddJurisdiction")
	public String toAdd(HttpServletRequest request){
		
		return "/jurisdiction/addJurisdiction";
	}
	/**
	 * 添加权限并重定向
	 * @deprecated
	 * @param object
	 * @param request
	 * @return getAllJurisdiction()
	 */
	@RequestMapping("/addJurisdiction")
	public String add(Jurisdiction object,HttpServletRequest request){
		
		Service.save(object);
		return "redirect:/jurisdiction/getAllJurisdiction";
	}
	
	/**
	 *编辑权限
	 * @param object
	 * @deprecated
	 * @param request
	 * @return getAllJurisdiction
	 */
	@RequestMapping("/updateJurisdiction")
	public String update(Jurisdiction object,HttpServletRequest request){
		
		if(Service.update(object)){
			object = Service.findById(object.getJurisdiction_id());
			request.setAttribute("jurisdiction", object);
			return "redirect:/jurisdiction/getAllJurisdiction";
		}else{
			return "/error";
		}
	}
	/**
	 * 根据id查询单个权限
	 * @param id
	 * @deprecated
	 * @param request
	 * @return editJurisdiction.jsp
	 */
	@RequestMapping("/getJurisdiction")
	public String get(int id,HttpServletRequest request){
		
		request.setAttribute("jurisdiction", Service.findById(id));
		return "/jurisdiction/editJurisdiction";
	}
	/**
	 * 删除权限
	 * @param id
	 * @deprecated
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delJurisdiction")
	public void delele(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		try {
			if(Service.remove(id)){
				result = "{\"result\":\"success\"}";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

