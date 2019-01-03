<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Memory.jsp' starting page</title>
    <meta charset="utf-8">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="/xhSmart/js/echarts.min.js"></script>
  
  </head>
  
  <body>
   <div id="main" style="width: 600px;height:400px;"></div>
  
     
     <script type="text/javascript">
        var myChart = echarts.init(document.getElementById("main"));
        
 	 </script>
  	<script type="text/javascript" src="/xhSmart/js/wxLin/Memory.js"></script>
  </body>
  
</html>
