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

<title>My JSP 'addJurisdiction.jsp' starting page</title>

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
				<section class="panel"> <header class="panel-heading">添加需求</header>

				<div class="panel-body">
					<form action="<%=basePath%>demand/addDemand" class="form-horizontal adminex-form cmxform" id="postForm"  method="post" enctype="multipart/form-data">
					<c:if test="${demand_fatherid != null}">
					<input type="hidden" name="demand_fatherid" value="${demand_fatherid}"/>
					</c:if>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>名称</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="demand_name"
									class="form-control required" placeholder="请填写名称" id="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<textarea maxlength="140" name="demand_depict"
									placeholder="请填写描述" style="height:90px;" class="form-control" id="depict"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>文档</label>
							<div class="col-sm-10">
								<input type="file" class="file required" id="file" name="file" >
							</div>
						   </div>
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
