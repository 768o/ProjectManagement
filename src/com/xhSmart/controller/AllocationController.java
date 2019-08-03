package com.xhSmart.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xhSmart.model.Allocation;
import com.xhSmart.model.Feedback;
import com.xhSmart.model.User;

import com.xhSmart.service.AllocationService;
import com.xhSmart.service.DepartmentService;
import com.xhSmart.service.FeedbackService;
import com.xhSmart.service.UserService;
import com.xhSmart.service.UserTaskService;
import com.xhSmat.util.SaveFileL;
/**
 * 负责任务分工功能的相关处理
 * @author lin
 */
@Controller
@RequestMapping("/allocation")
@Transactional
public class AllocationController {
	@Autowired
	private AllocationService Service;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private FeedbackService feedbackService;
	
	private static final String JSP_TO_ALLALLOCATION = "/allocation/allAllocation";
	private static final String JSP_TO_ADDALLOCATION = "/allocation/addAllocation";
	private static final String JSP_TO_EDITALLOCATION = "/allocation/editAllocation";
	private static final String JSP_TO_ERROR = "error";
	
	private static final String CONTROLLER_TO_GETALLALLOCATIONBYPRO = "/allocation/getAllAllocationByPro";
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllAllocationByUser")
	public ModelAndView getByUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_ALLALLOCATION);
		mav.addObject("allocationList", Service.findByUserId(user_id));
		return mav;
	}

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
		List<Allocation> list = Service.findAllByPro(id);
		List<Allocation> list2 = new ArrayList<Allocation>();
		String a[] = new String[list.size()];
		Boolean h = false;
		int k  = 0;
		for(int i = 0; i<list.size() ; i++){
			for(int j = 0 ; j < k; j ++){
				System.out.println(list.get(i).getName()+","+a[j]);
				try{
					if(a[j].equals(list.get(i).getName())){
						h = true;
						break;
					}
				}catch(Exception e){
					
				}
			}
			if(!h){
				a[k] = list.get(i).getName();
				list2.add(list.get(i));
				k++;
			}
			h = false;
		}
		mav.addObject("allocationList", list2);
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
	@Transactional
	public ModelAndView add(@RequestParam("file") CommonsMultipartFile file,Allocation object,HttpServletRequest request){
		try {
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");
		
		object.setPath(SaveFileL.save(path, file));
		}catch(Exception e) {}
		object.setUser_task(1);
		object.setProgress(0);
		object.setEndTime(Timestamp.valueOf(object.getEndTimeStr()));
		Service.save(object);
		
		User user = userService.findById(object.getUser_id());
		Feedback feedback = new Feedback();
		feedback.setUser_id(object.getUser_id());
		feedback.setProject_id(object.getProject_id());
		feedback.setFeedback_name("布置任务 " + object.getName() + " 给 " + user.getUser_name());
		feedback.setFeedback_depict("截至时间:" + object.getEndTimeStr());//立项反馈
		feedbackService.save(feedback);
		
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
	public ModelAndView update(@RequestParam("file") CommonsMultipartFile file,Allocation object,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		try {
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");
		
		object.setPath(SaveFileL.save(path, file));
		}catch(Exception e) {}
		object.setUser_task(1);
		object.setUser_joinTime(new Timestamp(System.currentTimeMillis()));
		Service.save(object);
		mav.setViewName("redirect:"+"/allocation/getAllocation");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Feedback feedback = new Feedback();
		feedback.setUser_id(object.getUser_id());
		feedback.setProject_id(object.getProject_id());
		feedback.setFeedback_name(user.getUser_name() + " 更新任务进度");
		feedback.setFeedback_depict(object.getName() + ":" + object.getProgress() + "%");//立项反馈
		feedbackService.save(feedback);
		
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
	public ModelAndView get(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_ALLALLOCATION);
		List<Allocation> list = Service.findByUserId(user_id);
		List<Allocation> list2 = new ArrayList<Allocation>();
		String a[] = new String[list.size()];
		Boolean h = false;
		int k  = 0;
		for(int i = list.size() - 1; i>=0 ; i--){
			for(int j = 0 ; j < k; j ++){
				System.out.println(list.get(i).getName()+","+a[j]);
				try{
					if(a[j].equals(list.get(i).getName())){
						h = true;
						break;
					}
				}catch(Exception e){
					
				}
			}
			if(!h){
				a[k] = list.get(i).getName();
				list2.add(list.get(i));
				k++;
			}
			h = false;
		}
		mav.addObject("allocationList", list2);
		return mav;
	}
	
	@RequestMapping("/getAAllocation")
	public ModelAndView getA(int project_id,int user_id,String name,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_EDITALLOCATION);
		mav.addObject("taskList", userTaskService.findAll());
		mav.addObject("allocation", Service.findById(project_id,user_id,name));
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
	public void delele(int Uid,int Pid,String name,HttpServletRequest request,HttpServletResponse response){
		
		Allocation object = Service.findById(Pid,Uid,name);
		String result = "{\"result\":\"error\"}";
		try {
			if(Service.remove(Uid,Pid,name)){
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
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Feedback feedback = new Feedback();
		feedback.setUser_id(object.getUser_id());
		feedback.setProject_id(object.getProject_id());
		feedback.setFeedback_name(user.getUser_name() + " 删除任务 " + object.getName());
		feedback.setFeedback_depict("");//立项反馈
		feedbackService.save(feedback);

	}
	
	@RequestMapping("/down")
	public void down(int project_id,int user_id,String name, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			response.setContentType("application/msword");
			File f = new File(Service.findById(project_id, user_id, name).getPath());
		    InputStream in = new FileInputStream(f);	
			OutputStream out = response.getOutputStream();	
			writeBytes(in, out);
		}catch(Exception e){}
	}
	private void writeBytes(InputStream in,OutputStream out) throws IOException{
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
}

