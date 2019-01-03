<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	</style>
	<script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
<script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#postForm").validate();
});
</script>
  </head>
  
  <body>
  <div style="padding:20% 35% 20% 35%">
    				<form action="<%=basePath%>user/login" class="form-horizontal adminex-form cmxform" id="postForm"  method="post">
    				    <c:if test="${tips != null}">
    				    <div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span></span></label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="" disabled>
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>账号</label>
							<div class="col-sm-10">
								<input minlength="4" maxlength="30" type="text" name="user_loginName"
									class="form-control required"  placeholder="请填写账号">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>密码</label>
							<div class="col-sm-10">
								<input minlength="6" maxlength="20" name="user_password" type="password"
									placeholder="请填写密码" class="form-control required">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 col-sm-2 control-label"></label>
							<div class="col-lg-10" >
								<input type="submit" class="btn btn-primary" value="提交">
							</div>
						</div>
					</form>
					</div>
  </body>
</html>
