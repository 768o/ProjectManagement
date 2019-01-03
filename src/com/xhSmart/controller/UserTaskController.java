package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.UserTask;
import com.xhSmart.service.UserTaskService;
/**
 * 
 * 负责任务功能的相关处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/userTask")
public class UserTaskController {

	@Autowired
	private UserTaskService Service;
	
	/**
	 * 获取所有用户任务列表
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllUserTask")
	public ModelAndView getAll(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/userTask/allUserTask");
		mav.addObject("userTaskList", Service.findAll());
		return mav;
	}
	
	/**
	 * 跳转到添加用户任务界面
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddUserTask")
	public ModelAndView toAdd(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/userTask/addUserTask");
		return mav;
	}
	/**
	 * 添加用户任务并重定向
	 * @param object userTask对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addUserTask")
	public ModelAndView add(UserTask object,HttpServletRequest request){
		
		Service.save(object);
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	/**
	 * 编辑用户任务
	 * @param object userTask对象
	 * @param request Request对象 
	 * @return ModelAndView
	 */
	@RequestMapping("/updateUserTask")
	public ModelAndView update(UserTask object,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		if(Service.update(object)){
			mav.setViewName("redirect:/userTask/getAllUserTask");
		}else{
			mav.setViewName("/error");
		}
		return mav;
	}
	/**
	 * 根据id查询单个用户任务
	 * @param id userTask的id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getUserTask")
	public ModelAndView get(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/userTask/editUserTask");
		mav.addObject("userTask", Service.findById(id));
		return mav;
	}
	/**
	 * 删除用户任务
	 * @param id  userTask的id
	 * @param request Request对象
	 * @param response 
	 */
	@RequestMapping("/delUserTask")
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


