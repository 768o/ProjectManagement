<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	
  </head>
  
  <body>
   	<a href="<%=basePath%>user/getAllUser">进入用户管理页</a><BR>
   	<a href="<%=basePath%>department/getAllDepartment">进入部门管理页</a><BR>
   	<a href="<%=basePath%>jurisdiction/getAllJurisdiction">进入权限管理页</a><BR>
   	<a href="<%=basePath%>post/getAllPost">进入职称管理页</a><BR>
   	<a href="<%=basePath%>demand/getAllDemand">进入需求管理页</a><BR>
    <a href="<%=basePath%>project/getAllProject">进入项目管理页</a><BR>
    <a href="<%=basePath%>product/getAllProduct">进入产品管理页</a><BR>
    <a href=" <%=basePath%>allocation/getAllAllocation">进入任务分工管理页</a><BR>
  </body>
</html>
