<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'editUser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
<script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#postForm").validate();
});
</script>
</head>

<body>
	<div class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<section class="panel"> <header class="panel-heading">
				</header>
				<div class="panel-body">
					<form action="<%=basePath%>user/updateUser" class="form-horizontal adminex-form cmxform" id="postForm"  method="post">
						<header>
						<b> 修改员工 </b></header>
						<input type="hidden" name="user_id" value="${user.user_id}" />
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>姓名</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="user_name" value="${user.user_name}"
									class="form-control requied" placeholder="请填写姓名">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>账号</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="user_loginName"
									value="${user.user_loginName}" class="form-control requied"
									placeholder="请填写用户名">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>密码</label>
							<div class="col-sm-10">
								<input minlength="6" maxlength="20" type="text" name="user_password"
									value="${user.user_password}" class="form-control requied"
									placeholder="请填写密码">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>部门</label>
							<div class="col-sm-10">
								 <select name="department_id" class="form-control">
									<c:if test="${!empty departmentList }">
										<c:forEach items="${departmentList}" var="department">
										    <c:choose>
										    	<c:when test="${user.department.department_id==department.department_id}">
										    		<option value="${department.department_id}" selected = "selected">${department.department_name}</option>
												</c:when>
												<c:otherwise>
													<option value="${department.department_id}">${department.department_name}</option>		
												</c:otherwise>
											</c:choose>	
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>职位</label>
							<div class="col-sm-10">
								<select name="post_id" class="form-control">
									<c:if test="${!empty postList }">
										<c:forEach items="${postList}" var="post">
										    <c:choose>
										    	<c:when test="${user.post.post_id==post.post_id}">
										    		<option value="${post.post_id}" selected = "selected">${post.post_name}</option>
												</c:when>
												<c:otherwise>
													<option value="${post.post_id}">${post.post_name}</option>		
												</c:otherwise>
											</c:choose>	
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<c:if test="${sessionScope.user.isadmin eq 1}">
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>管理员</label>
							<div class="col-sm-10">
								 <input type="radio" name="isadmin" value="0"  checked/>否
								<input type="radio" name="isadmin" value="1" />是
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<label class="col-lg-2 col-sm-2 control-label"></label>
							<div class="col-lg-10">
								<input type="submit" class="btn btn-primary" value="提交">
							</div>
						</div>
					</form>
				</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>
