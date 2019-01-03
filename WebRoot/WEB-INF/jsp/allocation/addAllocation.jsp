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
$(function(){
	$("#department").change(function(){
		//var department = $("#department").find("option:selected").text();
		var department = $("#department").val();
		
		$.ajax({
			type: "GET" ,
			url: "<%=basePath%>user/getUserByDepartment?id="+department ,
			dateType: "json" ,
			async : false,
			success: function(data){
				//alert(data);
				var obj = eval("("+data+")"); 
				$("#duser").empty();
				for(var index in obj){
					$("#duser").append("<option value=\""+obj[index].user_id+"\">"+obj[index].user_name+"</option>");
				}
				
			},
			error: function(XMLHttpRequest){  
				     alert( "Error: " + XMLHttpRequest.responseText);  
				   }  
		});
	});
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
					<form action="<%=basePath%>allocation/addAllocation" class="form-horizontal adminex-form cmxform" id="postForm"  method="get">
						<header>
						<b> 设置分工 </b></header>
						<input type="hidden" name="project_id" value="${project_id}"> 
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>部门</label>
							<div class="col-sm-10">
								 <select  class="form-control" id="department">
								  <option>请选择部门</option>		
									<c:if test="${!empty departmentList }">
										<c:forEach items="${departmentList}" var="department">						   
													<option value="${department.department_id}">${department.department_name}</option>		
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>姓名</label>
							<div class="col-sm-10">
								 <select name="user_id" class="form-control" id="duser">	
								 		<option value="">请先选择部门</option>		
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>任务</label>
							<div class="col-sm-10">
								 <select name="user_task" class="form-control">
									<c:if test="${!empty taskList }">
										<c:forEach items="${taskList}" var="task">						   
													<option value="${task.task_id}">${task.task_name}</option>		
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<!--  <div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>权限</label>
							<div class="col-sm-10">
								<select name="user_jurisdictionGroup" class="form-control">
									<c:if test="${!empty jurisdictionGroupList }">
										<c:forEach items="${jurisdictionGroupList}" var="jurisdictionGroup">									   
													<option value="${jurisdictionGroup.jurisdictionGroup_id}">${jurisdictionGroup.jurisdictionGroup_name}</option>		
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>-->
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
