<%@ page language="java" contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
  <script type="text/javascript" src="/xhSmart/js/wxLin/linFilter.js"></script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main_user.jsp' starting page</title>
   <script type="text/javascript">
   $(function(){
	   $(".showorhide").click(function(){
		  $('table tbody tr').toggleClass("selected").siblings(".child_"+this.value).toggle();
	  });
   });
   </script>
  </head>
  
  <body>
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
        <button class="btn btn-default btn-xs disabled"><i class="glyphicon glyphicon-user"></i>${user.user_name} </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/getUser?id=${user.user_id}"><i class="fa fa-cog"></i> 更改资料</a> </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/logout?id=${user.user_id}"><i class="fa fa-sign-out"></i> 退出</a> </button>
	</div>

 </div>
  <div class="page-heading">
      <h3> 项目管理 <span>
      <a href="<%=basePath%>project/getAllProject">项目</a>
      <a href="<%=basePath%>demand/getAllDemand">需求</a>
      <a href="<%=basePath%>product/getAllProduct">产品</a>
      </span></h3>
      <div class="pull-left">
      <a href="javascript:stateFilter('')" class="btn btn-info btn-sm">全部</a> 
       <a href="javascript:stateFilter('等待立项')" class="btn btn-info btn-sm">等待立项</a> 
      <a href="javascript:stateFilter('已立项')" class="btn btn-info btn-sm">已立项</a>
      </div>
      <div class="pull-right"><a href="<%=basePath%>demand/toAddDemand" class="btn btn-success">+添加新需求</a></div>
     </div> 
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 需求管理  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
               
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>展开/收起</th>
                        <th>需求名称</th>
                        <th>需求描述</th> 
                        <th>发布人</th>
                        <th>发布日期</th>
                        <th>需求类型</th>    
                        <th>需求文档</th>
                        <th>产品</th>
                        <th>需求状态</th>
                        <th>项目</th>
                        <th>操作</th>      
                      </tr>
                    </thead>
                    <tbody>      
                    <c:if test="${!empty demandList}">
				    <c:forEach items="${demandList}" var="demand">
				    <c:choose>
			        <c:when test="${demand.demand_state eq '已立项' || demand.demand_state eq '等待立项'}">
                    <tr>   
                    <td><button class="btn btn-default showorhide" value="${demand.demand_fatherid}">展开/收起</button></td>
                    </c:when>   
                    <c:otherwise> 
                    <tr class="child_${demand.demand_fatherid}" id="child_${demand.demand_fatherid}"> 
                    <td> </td>
                    </c:otherwise> 
                    </c:choose>                      
                      <td>${demand.demand_name}</td>
                      <td>${demand.demand_depict}</td>
                      <td>${demand.user.user_name}</td>
                      <td><fmt:formatDate value="${demand.demand_submitTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>                
                      <td>${demand.demand_type}</td>     
                      <td>
                      <c:choose>
                      	<c:when test="${demand.demand_path==null}">
                     		缺少
                      	</c:when>
                      	<c:otherwise>
                      	 	<a href="<%=basePath%>demand/downDemand?id=${demand.demand_id}">下载</a>
                      	</c:otherwise>
                      </c:choose>
                      </td>
                      <td><a href="<%=basePath%>product/getAllProduct?demand_name=${demand.demand_name}">查看</a></td>
                      <c:choose>
                      <c:when test="${demand.demand_type eq '初始'}">
                       <td>${demand.demand_state}</td>
                       <td>${demand.project.project_name}(${demand.project.project_state})</td>   
                      <c:choose>
                      <c:when test="${demand.project.project_state == '进行中' || demand.project.project_state == null}">
                          <td><div class="btn-group">
                          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>demand/toChangeDemand?id=${demand.demand_fatherid}">变更</a></li>
                            <li role="separator" class="divider"></li>
                            <c:choose>
                            <c:when test="${demand.demand_state eq '等待立项'}">
                            <li><a href="<%=basePath%>project/toAddProject?id=${demand.demand_id}">立项</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="<%=basePath%>product/toAddProduct?id=${demand.demand_id}&project_id=${demand.project.project_id}">提交产品</a></li>
                            </c:otherwise>
                            </c:choose>  
                          </ul>
                        </div></td>
                      </c:when>
                      <c:otherwise>
                          <td><div class="btn-group">
                          <button type="button" class="btn btn-primary disabled dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                                               <ul class="dropdown-menu">
                            <li>项目${demand.project.project_state}</li>
                            <li role="separator" class="divider"></li>
                            <li>禁止操作</li>
                        </c:otherwise>
                        </c:choose>
                        </c:when>
                        <c:otherwise>
                        
                        	<td colspan="2"> </td>
                        	<td><div class="btn-group">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>                   
                          <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>product/toAddProduct?id=${demand.demand_id}">提交产品</a></li>                         
                          </ul>
                        </div></td>
                        
                        
                        </c:otherwise>  
                        </c:choose>
                    </tr>
                   
                  </c:forEach>
                    </c:if>    
                    </tbody>                 
                  </table>
                    
				 </section>
            </div>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
