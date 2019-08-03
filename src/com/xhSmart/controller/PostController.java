package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.Post;
import com.xhSmart.service.PostService;
/**
 * 负责职务功能的处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService Service;
	
	/**
	 * 获取所有职务列表
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllPost")
	public ModelAndView getAll(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/post/allPost");
		mav.addObject("postList", Service.findAll());
		return mav;
	}
	
	/**
	 * 跳转到添加职务界面
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddPost")
	public ModelAndView toAdd(HttpServletRequest request){
		
		ModelAndView mav =new ModelAndView();
		mav.setViewName("/post/addPost");
		return mav;
	}
	/**
	 * 添加职务并重定向
	 * @param object 添加的职务对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addPost")
	public ModelAndView add(Post object,HttpServletRequest request){
		
		Service.save(object);
		ModelAndView mav =new ModelAndView();
		mav.setViewName("redirect:/post/getAllPost");
		return mav;
	}
	
	/**
	 *编辑职务
	 * @param object 职务对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updatePost")
	public ModelAndView update(Post object,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		if(Service.update(object)){
			mav.setViewName("redirect:/post/getAllPost");
		}else{
			mav.setViewName("/error");
		}
	    return mav;
	}
	/**
	 * 根据id查询单个职务
	 * @param id 职务id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getPost")
	public ModelAndView get(int id,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/post/editPost");
		mav.addObject("post", Service.findById(id));
		return mav;
	}
	/**
	 * 删除职务
	 * @param id 职务id
	 * @param request Request对象
	 * @param response response对象
	 */
	@RequestMapping("/delPost")
	public void delele(int id,HttpServletRequest request,HttpServletResponse response){
		System.out.println("删除的id"+ id);
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

