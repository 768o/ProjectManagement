package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.Department;
import com.xhSmart.service.DepartmentService;
/**
 * 
 * 负责部门功能的处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService Service;
	
	/**
	 * 获取所有部门列表
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllDepartment")
	public ModelAndView getAllDepartment(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/department/allDepartment");
		mav.addObject("departmentList", Service.findAll());
		return mav;
	}
	
	/**
	 * 跳转到添加部门界面
	 * @param request Request对象
	 * @return addDepartment.jsp
	 */
	@RequestMapping("/toAddDepartment")
	public ModelAndView toAddDepartment(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/department/addDepartment");
		return mav;
	}
	/**
	 * 添加部门并重定向
	 * @param object 部门对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addDepartment")
	public ModelAndView addDepartment(Department object,HttpServletRequest request){
		
		Service.save(object);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/department/getAllDepartment");
		return mav;
	}
	
	/**
	 *编辑部门
	 * @param object 部门对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updateDepartment")
	public ModelAndView updateDepartment(Department object,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		if(Service.update(object)){
			mav.setViewName("redirect:/department/getAllDepartment");
		}else{
			mav.setViewName("/error");
		}
		return mav;
	}
	/**
	 * 根据id查询部门
	 * @param id 部门id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getDepartment")
	public ModelAndView getDepartmentr(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/department/editDepartment");
		mav.addObject("department", Service.findById(id));
		return mav;
	}
	/**
	 * 根据id删除部门
	 * @param id 部门id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delDepartment")
	public void delDepartment(int id,HttpServletRequest request,HttpServletResponse response){
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

