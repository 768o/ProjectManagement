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
import com.xhSmart.model.JurisdictionGroup;
import com.xhSmart.service.JurisdictionGroupService;
import com.xhSmart.service.JurisdictionService;
/**
 * 
 * 负责权限组功能的处理（这个类暂时不会用到）
 * 
 * @deprecated
 * @author lin
 *
 */
@Controller
@RequestMapping("/jurisdictionGroup")
public class JurisdictionGroupController {

	@Autowired
	private JurisdictionGroupService Service;
	@Autowired
	private JurisdictionService jurisdictionService;
	
	/**
	 * 获取所有组列表
	 * @deprecated
	 * @param request
	 * @return allJurisdictionGroup.jsp
	 */
	@RequestMapping("/getAllJurisdictionGroup")
	public String getAll(HttpServletRequest request){
		
		List<JurisdictionGroup> findAll = Service.findAll();
		
		request.setAttribute("jurisdictionGroupList", findAll);
		return "/jurisdictionGroup/allJurisdictionGroup";
	}
	
	/**
	 * 跳转到添加组界面
	 * @deprecated
	 * @param request
	 * @return addJurisdictionGroup.jsp
	 */
	@RequestMapping("/toAddJurisdictionGroup")
	public String toAdd(HttpServletRequest request){
        List<Jurisdiction> findAll = jurisdictionService.findAll();
		request.setAttribute("jurisdictionList", findAll);   
		return "/jurisdictionGroup/addJurisdictionGroup";
	}
	/**
	 * 添加组并重定向
	 * @deprecated
	 * @param object
	 * @param request
	 * @return getAllJurisdictionGroup
	 */
	@RequestMapping("/addJurisdictionGroup")
	public String add(JurisdictionGroup object,HttpServletRequest request){
		
		Service.save(object);
		return "redirect:/jurisdictionGroup/getAllJurisdictionGroup";
	}
	
	/**
	 *编辑组
	 *@deprecated
	 * @param object
	 * @param request
	 * @return getAllJurisdictionGroup
	 */
	@RequestMapping("/updateJurisdictionGroup")
	public String update(JurisdictionGroup object,HttpServletRequest request){
		
		if(Service.update(object)){
			object = Service.findById(object.getJurisdictionGroup_id());
			request.setAttribute("jurisdictionGroup", object);
			return "redirect:/jurisdictionGroup/getAllJurisdictionGroup";
		}else{
			return "/error";
		}
	}
	/**
	 * 根据id查询单个组
	 * @param id
	 * @deprecated
	 * @param request
	 * @return editJurisdictionGroup.jsp
	 */
	@RequestMapping("/getJurisdiction")
	public String get(int id,HttpServletRequest request){
		
		request.setAttribute("jurisdictionGroup", Service.findById(id));
		return "/jurisdictionGroup/editJurisdictionGroup";
	}
	/**
	 * 删除组
	 * @deprecated
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delJurisdictionGroup")
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

