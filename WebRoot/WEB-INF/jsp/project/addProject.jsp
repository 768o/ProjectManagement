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
<script type="text/javascript" src="/xhSmart/js/jedate.js"></script>
<link rel="stylesheet" type="text/css" href="/xhSmart/js/jedate.css">

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
				<section class="panel"> <header class="panel-heading">添加项目</header>

				<div class="panel-body">
					<form action="<%=basePath%>project/addProject" class="form-horizontal adminex-form cmxform" id="postForm"  method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>名称</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="project_name"
									class="form-control required" placeholder="请填写名称" id="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>描述</label>
							<div class="col-sm-10">
								<textarea minlength="6" maxlength="140" name="project_depict"
									placeholder="请填写描述" style="height:90px;" class="form-control required" id="depict"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>立项原因</label>
							<div class="col-sm-10">
								<textarea minlength="6" maxlength="140" name="project_createReason"
									placeholder="请填写原因" style="height:90px;" class="form-control required"></textarea>
							</div>
						</div>
						<div class="form-group jeitem">
			                <label class="col-sm-2 col-sm-2 control-label jelabel">预约完成时间</label>
			                <div class="col-sm-10 jeinpbox">
			                	<input class="form-control required" type="text" class="jeinput" name="project_endTimeStr"
			                		id="test04" placeholder="YYYY-MM-DD hh:mm:ss">
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
	<script type="text/javascript" src="/xhSmart/js/demo.js"></script>
</body>
</html>