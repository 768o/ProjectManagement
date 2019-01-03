package com.xhSmart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xhSmart.model.Allocation;
import com.xhSmart.model.Demand;
import com.xhSmart.model.Feedback;
import com.xhSmart.model.Product;
import com.xhSmart.model.Project;
import com.xhSmart.model.User;
import com.xhSmart.service.AllocationService;
import com.xhSmart.service.DemandService;
import com.xhSmart.service.FeedbackService;
import com.xhSmart.service.ProductService;
import com.xhSmart.service.ProjectService;
import com.xhSmart.service.UserService;
import com.xhSmart.service.UserTaskService;
import com.xhSmat.util.Page;
/**
 * 
 * 扶着项目功能的处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/project")
@Transactional
public class ProjectController {
	
	@Autowired
	private ProjectService Service;
	@Autowired
	private DemandService demandService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private AllocationService allocationService;
	@Autowired
	private UserTaskService userTaskService;
	
	/**
	 * 根据user是否是管理员，项目名称，页数，显示数据
	 * 
	 * @param request Request对象
	 * @return allProject.jsp
	 */
	
	@RequestMapping("/getAllProject")
	public String getAll(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("user");
		int user_id = user.getUser_id();//用户id
		String keyword = null;//搜索关键词
		if(request.getParameter("keyword")!=null){
			keyword = request.getParameter("keyword");
			session.setAttribute("projectkeyword", keyword);
		}else if(session.getAttribute("projectkeyword")!=null){
			keyword = (String)session.getAttribute("projectkeyword");
		}else{
			keyword = "";
		}
		
		String pageNow = request.getParameter("pageNow");//用户请求页数
		int totalCount = 0;//总记录数
		List<Project> findAll = new ArrayList<Project>();
		Page page =null;
		if(user.getIsadmin() == 0){
	    totalCount = Service.getProjectsCountByNameToUser(user_id, keyword);//总记录数用户
	    if (pageNow != null){
        	page = new Page(totalCount, Integer.parseInt(pageNow));
        	findAll = Service.findAllByNameToUser(user_id, keyword, page.getStartPos(), page.getPageSize());//分页
	    }else{
	    	page = new Page(totalCount, 1);
	    	findAll = Service.findAllByNameToUser(user_id, keyword, page.getStartPos(), page.getPageSize());//分页
	    }
		}else {
			//System.out.println("byname");
			
			totalCount = Service.getProjectsCountByNameToAdmin(keyword);////总记录数管理员
			//System.out.println(name+totalCount);
			if (pageNow != null){
	        	page = new Page(totalCount, Integer.parseInt(pageNow));
	        	System.out.println(keyword+" "+page.getStartPos()+" "+page.getPageSize());
	        	findAll = Service.findAllByNameToAdmin(keyword, page.getStartPos(), page.getPageSize());//分页
	        	
		    }else{
		    	page = new Page(totalCount, 1);
		    	System.out.println(keyword+" "+page.getStartPos()+" "+page.getPageSize());
		    	findAll = Service.findAllByNameToAdmin(keyword, page.getStartPos(), page.getPageSize());//分页
		    	System.out.println(findAll);
		    	
		    }
		}

		List<Project> findAllList = new ArrayList<Project>();
		for (Project project : findAll) {
			//System.out.println(project.getUser_id());
			if(project.getUser_id()!=0){
			project.setUser(userService.findById(project.getUser_id()));
			findAllList.add(project);
			}
		}
		System.out.println(findAllList);
		request.setAttribute("projectList", findAllList);
		request.setAttribute("page", page);
		return "/project/allProject";
	}
	/**
	 * 显示一个项目的具体信息
	 * 
	 * @param id 项目id
	 * @param request Request对象
	 * @return aProject.jsp
	 */
	@RequestMapping("/getAProject")
	public String getAProject(int id,HttpServletRequest request){
		Project project = Service.findById(id);
		project.setUser(userService.findById(project.getUser_id()));
		request.setAttribute("project", project);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(allocationService.findById(project.getProject_id(), user.getUser_id())!=null){
		Allocation allocation = allocationService.findById(project.getProject_id(), user.getUser_id());
		allocation.setTask(userTaskService.findById(allocation.getUser_task()));
	    request.setAttribute("allocation", allocation);
		}
		List<Demand> demands = demandService.findByProjectId(id);
		List<Demand> demands2 = new ArrayList<Demand>();
		for (Demand demand : demands) {
			demand.setUser(userService.findById(demand.getSubmit_user()));
			demands2.add(demand);
		}
		List<Product> productList = productService.findByProjectId(id);
		List<Product> productList2 = new ArrayList<Product>();
		for (Product product : productList) {
			product.setUser(userService.findById(product.getSubmit_user()));
			product.setDemand(demandService.findById(product.getDemand_id()));
			productList2.add(product);
		}
		
		List<Feedback> feedbackListold = feedbackService.findByProjectId(id);
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		for (Feedback feedback : feedbackListold) {
			feedback.setUser(userService.findById(feedback.getUser_id()));
			feedbackList.add(feedback);
		}
		
		request.setAttribute("feedbackList", feedbackList);
		request.setAttribute("productList",productList2);
		request.setAttribute("demandList", demands2);
		return "/project/aProject";
	}
	
	/**
	 * 跳转到添加项目界面 
	 * @param request Request对象
	 * @return addPoject.jsp
	 */
	@RequestMapping("/toAddProject")
	public String toAdd(int id, HttpServletRequest request){
		request.setAttribute("demand_id", id);
		return "/project/addProject";
	}
	/**
	 * 添加项目并重定向
	 * @param object 项目对象
	 * @param request Request对象
	 * @return 跳转至getAll()方法 @see {@link #getAll(HttpServletRequest)}
	 * @throws ParseException 
	 */
	
	@RequestMapping("/addProject")
	@Transactional
	public String add(Project object,HttpServletRequest request) throws ParseException{
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		object.setUser_id(user_id);//获取用户id
		
		object.setProject_state("进行中");
		Service.save(object);//保存
		
		Demand demand=demandService.findById(object.getDemand_id());//获取立项的需求
			
		int project_id = Service.findByName(object.getProject_name()).getProject_id();
		//stem.out.println("xiangmudeid"+project_id);
		//demand.setDemand_submitTime(MyDateFormat(demand.getDemand_submitTime()));//貌似多余
		demand.setProject_id(project_id);//设置成刚保存的id
		//System.out.println("xuqiuid"+demand.getProject_id());
		//demand.setDemand_id(demand.getDemand_id());//貌似多余
		demand.setDemand_state("已立项");
		demandService.update(demand);
		
		Allocation allocation = new Allocation();
		allocation.setProject_id(project_id);
		allocation.setUser_id(demand.getSubmit_user());
		allocation.setUser_task(1);
		allocationService.save(allocation);
		
		Feedback feedback = new Feedback();
		feedback.setUser_id(demand.getSubmit_user());
		feedback.setProject_id(project_id);
		feedback.setFeedback_name("需求");
		feedback.setFeedback_depict(userService.findById(demand.getSubmit_user()).getUser_name()+" 添加了需求 "+demand.getDemand_name());//需求
		feedbackService.save(feedback);
		
		feedback = new Feedback();
		feedback.setUser_id(user_id);
		feedback.setProject_id(project_id);
		feedback.setFeedback_name("项目");
		feedback.setFeedback_depict(user.getUser_name()+" 开始了项目 "+object.getProject_name());//立项反馈
		feedbackService.save(feedback);
		
		return "redirect:/project/getAllProject";
	}
//	public Timestamp MyDateFormat(Timestamp timestamp) throws ParseException{//貌似多余
//		String submitTimeS = timestamp.toString();
//		submitTimeS.replaceAll(".0", "");
//		System.out.println(submitTimeS);
//		Timestamp submitTime = null;
//		submitTime = Timestamp.valueOf(submitTimeS);
//		return submitTime;
//	}
	
	/**
	 * 编辑项目
	 * @param object 项目对象
	 * @param request Request对象
	 * @return 跳转至getAll()方法 @see {@link #getAll(HttpServletRequest)}
	 */
	@RequestMapping("/updateProject")
	public String update(Project object,HttpServletRequest request){
		
		if(Service.update(object)){
			object = Service.findById(object.getProject_id());
			request.setAttribute("project", object);
			return "redirect:/project/getAllProject";
		}else{
			return "/error";
		}
	}
	/**
	 * 根据id查询单个项目
	 * @param id 项目id
	 * @param request Request对象
	 * @return editProject.jsp
	 */
	@RequestMapping("/getProject")
	public String get(int id,HttpServletRequest request){
		
		request.setAttribute("project", Service.findById(id));
		return "/project/editProject";
	}
	/**
	 * 删除项目
	 * @param id 项目id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delProject")
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
	/**
	 * 修改项目状态 
	 * @param id 项目id
	 * @param state 0-进行中,1-挂起中,2-已结束
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/changeProjectState")
	public void changeProjectState(int id, int state, HttpServletRequest request,HttpServletResponse response){
		Project project = Service.findById(id);
		String newstate = null;
		Timestamp endTime = null;
		if (state == 0){
			newstate = "进行中";
		}else if (state == 1){
			newstate = "挂起中";
		}else if (state == 2){
			newstate="已结束";
			endTime = new Timestamp(System.currentTimeMillis());
			
		}else{}
		//project.setProject_createTime(project.getProject_createTime());
		project.setProject_endTime(endTime);
		project.setProject_state(newstate);
		String result = "{\"result\":\"error\"}";
		try {
			if(Service.update(project)){
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

