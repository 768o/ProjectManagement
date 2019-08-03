<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>任务进度管理系统</title>
 <base href="<%=basePath%>">
<decorator:head />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="author" content="lock">
<meta name="keywords" content="xhSmart,管理系统">
<meta name="description" content="xhSmart管理系统">
<link rel="shortcut icon" href="/xhSmart/img/favicon.ico" type="image/png">
<link href="/xhSmart/css/style.css" rel="stylesheet" type="text/css">
<link href="/xhSmart/css/style-responsive.css" rel="stylesheet" type="text/css">
<link href="/xhSmart/css/table-responsive.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>
<link rel="stylesheet" href="/xhSmart/js/bootstrap-fileinput-master/css/fileinput.min.css" type="text/css">
<script type="text/javascript" src="/xhSmart/js/wxLin/linFilter.js"></script>
<script type="text/javascript" src="/xhSmart/js/bootstrap-fileinput-master/js/fileinput.min.js"></script>
<script type="text/javascript">
function find(){
	var text = $("#keywords").val();
	stateFilter(text);
}
</script>
</head>
<body class="sticky-header">
<section> 
<div class="left-side sticky-left-side">
  
  <div class="logo"> <a href="/xhSmart/index.jsp"><img src="/xhSmart/img/logo-left.png" alt="xh管理系统"></a> </div>
  <div class="logo-icon text-center"> 
  <a href="/"><img src="/xhSmart/img/logo_icon.png" style="width:40px;" alt="xh管理系统"></a> 
  </div>
  
  <div class="left-side-inner">
    
    <div class="visible-xs hidden-sm hidden-md hidden-lg">
      <div class="media logged-user"> 
      <img alt="libai" src="/xhSmart/uploadfile/2017-3/28/5b41faa955a4c1acdb6d7e6c116bce2f-cropper.jpg" class="media-object">
        <div class="media-body">
          <h4><a href="/user/show/1461312703628858832">libai</a></h4>
          <span>xh系统</span> </div>
      </div>
      <h5 class="left-nav-title">控制台</h5>
      <ul class="nav nav-pills nav-stacked custom-nav">
        <li><a href="/user/profile"><i class="fa fa-user"></i> <span>个人设置</span></a></li>
        <li><a href="/logout"><i class="fa fa-sign-out"></i> <span>退出</span></a></li>
     </ul>
    </div> 
    <ul class="nav nav-pills nav-stacked custom-nav js-left-nav"> 
	<li><br></li>   		
	<li><a href="/xhSmart/project/getAllProject"><i class="fa fa-book"></i> <span>项目管理</span></a></li>   	
	<li><a href="/xhSmart/user/getAllUser"><i class="fa fa-user"></i> <span>组织管理</span></a></li>   
    </ul>
  </div>
</div>
<div class="main-content" >  
    <div class="header-section">     
      <a class="toggle-btn"><i class="fa fa-bars"></i></a>
      <form action="" id="search" method="post">
     <div class="searchform">
        <input type="text" class="form-control" name="name" id="keywords" value="${keyword}" placeholder="请输入搜索条件" />
        <button class="btn btn-primary" id="searchButton">搜索</button>
     </div>
     </form>
	<div class="menu-right">
        <button class="btn btn-default btn-xs disabled"><i class="glyphicon glyphicon-user"></i>${sessionScope.user.user_name} </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/getUser?id=${sessionScope.user.user_id}"><i class="fa fa-cog"></i> 更改资料</a> </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/logout?id=${sessionScope.user.user_id}"><i class="fa fa-sign-out"></i> 退出</a> </button>
	</div>

 </div>
  
    <decorator:body />
    <footer> 2017 &copy; xhSmart &nbsp;&nbsp;<a href="">帮助</a> &middot; <a href="">用户反馈</a></footer>
  </div>
</section>
<script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
<script type="text/javascript" src="/xhSmart/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/xhSmart/js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="/xhSmart/js/scripts.js"></script>
<script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>
<script type="text/javascript" src="/xhSmart/js/opms.js"></script>
</body>
</html>

