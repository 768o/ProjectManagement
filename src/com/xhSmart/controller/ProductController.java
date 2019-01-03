package com.xhSmart.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.xhSmart.model.Product;
import com.xhSmart.model.User;

import com.xhSmart.service.ProductService;
import com.xhSmat.util.Page;
import com.xhSmat.util.SaveFileL;
/**
 * 
 * 负责产品功能的处理
 * @author lin
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService Service;
	
	/**
	 * 获取所有产品列表
	 * 
	 * @param request Request对象
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	
	@RequestMapping("/getAllProduct")
	public ModelAndView getAll(HttpServletRequest request) throws UnsupportedEncodingException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int user_id=user.getUser_id();//用户id
		
		String keyword = null;//搜索关键词
		
		if(request.getParameter("keyword")!=null){
			//System.out.println(request.getParameter("keyword"));
			//keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"),"UTF-8");
			keyword = request.getParameter("keyword");
			//System.out.println(keyword);
			session.setAttribute("productkeyword", keyword);
			session.setMaxInactiveInterval(2 * 60);
		}else if(session.getAttribute("productkeyword")!=null){
			keyword = (String)session.getAttribute("productkeyword");
		}else{
			keyword = "";
		}
		
	    String pageNow = request.getParameter("pageNow");//用户请求页数
	    int totalCount = 0;//总记录数
	    List<Product> findAll = new ArrayList<Product>();
	    Page page =null;
	    if(user.getIsadmin() == 0){//0代表非管理员
    	    totalCount = Service.getProductsCountByUser(user_id, keyword);//总记录数用户
    	    if (pageNow != null){
            	page = new Page(totalCount, Integer.parseInt(pageNow));
            	findAll = Service.findAllByUser(user_id, keyword,page.getStartPos(),page.getPageSize());//分页
    	    }else{
    	    	page = new Page(totalCount, 1);
    	    	findAll = Service.findAllByUser(user_id, keyword,page.getStartPos(),page.getPageSize());//分页
    	    }
    		}else {
    			totalCount = Service.getProductsCountByAdmin(keyword);//总记录数用户
    			if (pageNow != null){
    	        	page = new Page(totalCount, Integer.parseInt(pageNow));
    	        	findAll = Service.findAllByAdmin(keyword,page.getStartPos(),page.getPageSize());//分页
    		    }else{
    		    	page = new Page(totalCount, 1);
    		    	findAll = Service.findAllByAdmin(keyword,page.getStartPos(),page.getPageSize());//分页
    		    }
    		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/product/allProductByUser");
		mav.addObject("productList", findAll);
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 根据产品id下载产品
	 * @param id 产品id
	 * @param request Request对象
	 * @param response Response对象
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/downProduct")
	public void downDemand(int id, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/msword");
		File f = new File(Service.findById(id).getProduct_path());
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
	 * 添加产品并重定向
	 * 
	 * @param object 产品对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/addProduct")
	public ModelAndView add(@RequestParam("file") CommonsMultipartFile file,
			Product object, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");
		
		object.setProduct_path(SaveFileL.save(path, file));
		User user = (User) session.getAttribute("user");
		object.setSubmit_user(user.getUser_id());
		Service.save(object,user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/project/getAProject?id="+object.getProject_id());
		return mav;
	}

	/**
	 * 编辑产品
	 * @param object 产品对象
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/updateProduct")
	public ModelAndView update(@RequestParam("file") CommonsMultipartFile file,Product object, HttpServletRequest request) {
		String path = request.getSession().getServletContext()
				.getRealPath("WEB-INF/uploadfile/doc");
		object.setProduct_path(SaveFileL.save(path, file));
		object.setProduct_state(Service.findById(object.getProduct_id()).getProduct_state());
		ModelAndView mav = new ModelAndView();
		if (Service.update(object)) {
			mav.setViewName("redirect:/product/getAllProduct");
		} else {
			mav.setViewName("/error");
		}
		return mav;
	}
	

	/**
	 * 根据id查询单个产品
	 * 
	 * @param id 产品id
	 * @param request Request对象
	 * @return ModelAndView
	 */
	@RequestMapping("/getProduct")
	public ModelAndView get(int id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/product/editProduct");
		mav.addObject("product", Service.findById(id));
		return mav;
	}

	/**
	 * 删除产品
	 * @param id 产品id
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/delProduct")
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
	/**
	 * 改变产品的状态
	 * @param id 产品id
	 * @param state 产品状态 0-开发中，1-测试中，2-已结束
	 * @param request Request对象
	 * @param response Response对象
	 */
	@RequestMapping("/changeProductState")
	public void changeProductState(int id, int state, HttpServletRequest request,HttpServletResponse response){
		//System.out.println(id+state);
		Product product = Service.findById(id);
		String newstate = null;
		if (state == 0){
			newstate = "开发中";
		}else if (state == 1){
			newstate = "测试中";
		}else if (state == 2){
			newstate="已结束";
			//endTime = new Timestamp(System.currentTimeMillis());
			
		}else{}
	    product.setProduct_state(newstate);
		String result = "{\"result\":\"error\"}";
		try {
			if(Service.update(product)){
				result = "{\"result\":\"success\"}";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error");
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
