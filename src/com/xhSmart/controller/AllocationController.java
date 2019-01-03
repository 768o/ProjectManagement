package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.Allocation;

import com.xhSmart.service.AllocationService;
import com.xhSmart.service.DepartmentService;
import com.xhSmart.service.UserService;
import com.xhSmart.service.UserTaskService;
/**
 * 负责分工功能的相关处理
 * @author lin
 */
@Controller
@RequestMapping("/allocation")
public class AllocationController {
	@Autowired
	private AllocationService Service;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	
	private static final String JSP_TO_ALLALLOCATION = "/allocation/allAllocation";
	private static final String JSP_TO_ADDALLOCATION = "/allocation/addAllocation";
	private static final String JSP_TO_EDITALLOCATION = "/allocation/editAllocation";
	private static final String JSP_TO_ERROR = "error";
	
	private static final String CONTROLLER_TO_GETALLALLOCATIONBYPRO = "/allocation/getAllAllocationByPro";

	/**
	 * 根据项目的id查询该项目下的所有成员
	 * @param id 项目的id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllAllocationByPro")
	public ModelAndView getAllAllocationByPro(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_ALLALLOCATION);
		mav.addObject("allocationList", Service.findAllByPro(id));
		mav.addObject("project_id", id);
		return mav;
	}
	/**
	 * 根据项目id跳转到这个项目的添加成员界面 
	 * @param id 项目的id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddAllocation")
	public ModelAndView toAdd(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_ADDALLOCATION);
		mav.addObject("taskList", userTaskService.findAll());
		mav.addObject("departmentList", departmentService.findAll());
		mav.addObject("project_id", id);
		return mav;
	}
	/**
	 * 为项目添加一个成员并重定向
	 * @param object 用户添加的成员（Allocation）对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addAllocation")
	public ModelAndView add(Allocation object,HttpServletRequest request){
		
		Service.save(object);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+CONTROLLER_TO_GETALLALLOCATIONBYPRO+"?id="+object.getProject_id());
		return mav;
	}
	
	/**
	 * 编辑一个成员
	 * @param object 用户修改后的成员（Allocation）对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updateAllocation")
	public ModelAndView update(Allocation object,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		if(Service.update(object)){
			mav.setViewName("redirect:"+CONTROLLER_TO_GETALLALLOCATIONBYPRO+"?id="+object.getProject_id());
		}else{
			mav.setViewName(JSP_TO_ERROR);
		}
		return mav;
	}
    /**
	* 根据项目id,用户id获得一个成员对象的信息
	* @param project_id 项目的id
 	* @param user_id 用户的id
 	* @param request Request对象，保存allocation
 	* @return ModelAndView
 	*/
	@RequestMapping("/getAllocation")
	public ModelAndView get(int project_id,int user_id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_EDITALLOCATION);
		mav.addObject("taskList", userTaskService.findAll());
		mav.addObject("allocation", Service.findById(project_id,user_id));
		mav.addObject("department", departmentService.findById(userService.findById(user_id).getDepartment_id()));
		return mav;
	}
	/**
	 * 从项目中删除一个成员
	 * @param Uid 删除的用户的id
	 * @param Pid 项目的id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delAllocation")
	public void delele(int Uid,int Pid,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		try {
			if(Service.remove(Uid,Pid)){
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

