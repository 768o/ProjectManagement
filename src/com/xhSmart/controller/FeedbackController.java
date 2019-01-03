package com.xhSmart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xhSmart.model.Feedback;
import com.xhSmart.model.User;
import com.xhSmart.service.FeedbackService;
/**
 * 负责进度与反馈功能的处理
 * @author lin
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService Service;
	/**
	 * 跳转到添加反馈界面
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddFeedback")
	public ModelAndView toAdd(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/feedback/addFeedback");
		return mav;
	}
	/**
	 * 添加反馈并重定向
	 * @param object Feedback对象 
	 * @param request Request对象
	 * @throws IOException 
	 */
	@RequestMapping("/addFeedback")
	@ResponseBody
	public void add(Feedback object,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		object.setUser_id(user.getUser_id());
		Service.save(object);
		String json = JSON.toJSONString(Service.findByProjectId(object.getProject_id()));
		response.getWriter().println(json);
	}
	
}

