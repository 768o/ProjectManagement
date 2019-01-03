package com.xhSmart.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xhSmart.model.Demand;
import com.xhSmart.model.User;
import com.xhSmart.service.DemandService;
import com.xhSmat.util.Page;
import com.xhSmat.util.SaveFileL;
/**
 * 负责需求功能的相关处理
 * @author lin
 */
@Controller
@RequestMapping("/demand")
public class DemandController {

	@Autowired
	private DemandService Service;
	
	private static final String JSP_TO_ALLDEMANDBYUSER = "/demand/allDemandByUser";
	private static final String JSP_TO_ADDDEMAND = "/demand/addDemand";
	private static final String JSP_TO_EDITDEMAND = "/demand/editDemand";
	private static final String JSP_TO_ERROR = "error";
	
	private static final String CONTROLLER_TO_GETALLDEMAND = "/demand/getAllDemand";
	
	/**
	 * 根据项目id查询该项目下的需求
	 * @param id 项目的id
	 * @param request Request对象
	 * @param response Response对象
	 * @throws IOException 
	 */
	@RequestMapping("/getAllDemandByPro")
	public void getAllByPro(int id,HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<Demand> demandList = Service.findByProjectId(id);
		String json = JSON.toJSONString(demandList);
		response.getWriter().println(json);
	}
	/**
	 * 根据不同的用户显示不同的需求列表
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getAllDemand")
	public ModelAndView getAllByUser(HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int user_id=user.getUser_id();//获得用户id
        String keyword = null;//获得关键词
		if(request.getParameter("keyword")!=null){
			keyword = request.getParameter("keyword");
			session.setAttribute("demandkeyword", keyword);
		}else if(session.getAttribute("demandkeyword")!=null){
			keyword = (String)session.getAttribute("demandkeyword");
		}else{
			keyword = "";
		}
        String pageNow = request.getParameter("pageNow");//获得用户请求页数
        int totalCount = 0;//总记录数
        List<Demand> findAll =new ArrayList<Demand>();//
        Page page =null;
        if(user.getIsadmin() == 0){//0代表非管理员
    	    totalCount = Service.getDemandsCountByUser(user_id,keyword);//总记录数用户
    	    if (pageNow != null){
            	page = new Page(totalCount, Integer.parseInt(pageNow));
            	findAll = Service.findAllByUser(user_id,keyword,page.getStartPos(),page.getPageSize());//分页
    	    }else{
    	    	page = new Page(totalCount, 1);
    	    	findAll = Service.findAllByUser(user_id,keyword,page.getStartPos(),page.getPageSize());//分页
    	    }
    		}else {
    			totalCount = Service.getDemandsCountByAdmin(keyword);////总记录数管理员
    			if (pageNow != null){
    	        	page = new Page(totalCount, Integer.parseInt(pageNow));
    	        	findAll = Service.findAllByAdmin(keyword,page.getStartPos(),page.getPageSize());//分页
    		    }else{
    		    	page = new Page(totalCount, 1);
    		    	findAll = Service.findAllByAdmin(keyword,page.getStartPos(),page.getPageSize());//分页
    		    }
    		}
        ModelAndView mav = new ModelAndView();
        mav.setViewName(JSP_TO_ALLDEMANDBYUSER);
        mav.addObject("demandList", findAll);
        mav.addObject("page", page);
		return mav;
	}

	/**
	 * 跳转到添加需求界面
	 * @param request Request对象
	 * @return addDemand.jsp
	 */
	@RequestMapping("/toAddDemand")
	public ModelAndView toAdd(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_ADDDEMAND);
		return mav;
	}
	/**
	 * 根据需求id下载需求
	 * @param id 需求id
	 * @param request Request对象
	 * @param response Response对象
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/downDemand")
	public void downDemand(int id, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/msword");
		File f = new File(Service.findById(id).getDemand_path());
	    InputStream in = new FileInputStream(f);	
		OutputStream out = response.getOutputStream();	
		writeBytes(in, out);
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
	/**
	 * 添加需求并重定向
	 * @param file 文档
	 * @param object 需求对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addDemand")
	public ModelAndView add(@RequestParam("file") CommonsMultipartFile file,
			Demand object, HttpServletRequest request) {
		
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");//设置路径
		object.setDemand_path(SaveFileL.save(path, file));
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Service.save(object,user);
		ModelAndView mav = new ModelAndView();
		if((object.getProject_id()!=0)){
			mav.setViewName("redirect:/project/getAProject?id="+object.getProject_id());
		}else{
			mav.setViewName(CONTROLLER_TO_GETALLDEMAND);
		}	
		return mav;
	}

	/**
	 * 编辑需求
	 * @param object 需求对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updateDemand")
	public ModelAndView update(@RequestParam("file") CommonsMultipartFile file,Demand object, HttpServletRequest request) {
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");
		object.setDemand_path(SaveFileL.save(path, file));
		object.setProject_id(-1);
		object.setDemand_state(Service.findById(object.getDemand_id()).getDemand_state());
		ModelAndView mav = new ModelAndView();
		if (Service.update(object)) {
			mav.setViewName("redirect:"+CONTROLLER_TO_GETALLDEMAND);
		} else {
			mav.setViewName(JSP_TO_ERROR);
		}
		return mav;
	}

	/**
	 * 根据id查询单个需求
	 * 
	 * @param id 需求id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getDemand")
	public ModelAndView get(int id, HttpServletRequest request) {
        
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSP_TO_EDITDEMAND);
		mav.addObject("demand", Service.findById(id));
		return mav;
	}

	/**
	 * 根据id删除需求
	 * 
	 * @param id 需求id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delDemand")
	public void delele(int id, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			if (Service.remove(id)) {
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
