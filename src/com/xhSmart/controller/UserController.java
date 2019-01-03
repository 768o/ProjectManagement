package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.User;
import com.xhSmart.service.DepartmentService;
import com.xhSmart.service.PostService;
import com.xhSmart.service.UserService;
/**
 * 
 * 负责用户功能的相关处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 注销登陆，销毁session
	 * @param id 用户id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(int id,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/login.jsp");
		return mav;
		
	}
	/**
	 * 用户登录
	 * @param user 登陆的用户
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpServletRequest request){
		HttpSession session = request.getSession();
		User userReal = userService.findByLoginName(user.getUser_loginName());
		ModelAndView mav = new ModelAndView();
		if(user.getUser_password().equals(userReal.getUser_password())){
			if(userReal.getUser_isdelete()==0){
				session.setAttribute("user", userReal);
				mav.setViewName("redirect:/project/getAllProject");
			}else{
				mav.setViewName("redirect:/login.jsp");
			}
		}else{	
			mav.setViewName("redirect:/login.jsp");
		}
		return mav;
	}
	/**
	 * 查看所有用户
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllUser")
	public ModelAndView getAllUser(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/allUser");
		mav.addObject("userList", userService.findAll());
		return mav;
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddUser")
	public ModelAndView toAddUser(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/addUser");
		mav.addObject("postList", postService.findAll());
		mav.addObject("departmentList", departmentService.findAll());
		return mav;
	}
	/**
	 * 添加用户并重定向
	 * @param user 添加的用户对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addUser")
	public ModelAndView addUser(User user,HttpServletRequest request){
		
		userService.save(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/getAllUser");
		return mav;
	}
	
	/**
	 * 编辑用户
	 * @param user 编辑后的用户对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updateUser")
	public ModelAndView updateUser(User user,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user2 = (User)session.getAttribute("user");//当前登录的用户
		ModelAndView mav = new ModelAndView();
		if(userService.update(user)){
			user = userService.findById(user.getUser_id());
			mav.addObject("user", user);
			if(user2.getIsadmin()==0){
				mav.setViewName("redirect:/project/getAllProject");
			}else{
				mav.setViewName("redirect:/user/getAllUser");
			}
		}else{
			mav.setViewName("/error");
		}
		return mav;
	}
	/**
	 * 查询部门下的员工
	 * @param id 部门id
	 * @param request Request对象
	 * @param response Response对象
	 * @throws IOException
	 */
	@RequestMapping("/getUserByDepartment")
	@ResponseBody
	public void getUserByDepartment(int id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<User> userList = userService.findByDepartment(id);
		String json = JSON.toJSONString(userList);
		response.getWriter().println(json);
	}
	/**
	 * 根据员工id查询单个员工
	 * @param id 员工id
	 * @param request Request对象
	 * @return editUser.jsp
	 * */
	@RequestMapping("/getUser")
	public ModelAndView getUser(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/editUser");
		mav.addObject("user", userService.findById(id));
		mav.addObject("postList", postService.findAll());
        mav.addObject("departmentList", departmentService.findAll());
		return mav;
	}
	/**
	 * 删除用户
	 * @param id 用户id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delUser")
	public void delUser(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		
		if(userService.remove(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 改变用户的状态（可登陆（0）与不可登陆（1））
	 * @param id 用户的id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/changeState")
	public void changeState(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		User user = userService.findById(id);
		user.setUser_isdelete(user.getUser_isdelete()+1-2*user.getUser_isdelete());
		if(userService.update(user)){
			user = userService.findById(user.getUser_id());
			request.setAttribute("user", user);
			result = "{\"result\":\"success\"}";
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
