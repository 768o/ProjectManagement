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
   		var text = $("#finddemand_name").val()
   		stateFilter(text);
   	});
   </script>
  </head>
  
  <body>
  <div class="page-heading">
      <h3> 项目管理 <span>
      <a href="<%=basePath%>project/getAllProject">项目</a>
      <!--  <a href="<%=basePath%>demand/getAllDemand">需求</a>-->
      <a href="<%=basePath%>product/getAllProduct">进度</a>
      </span></h3>
       <div class="pull-left">
      <a href="javascript:stateFilter('进行中')" class="btn btn-info btn-sm">项目进行中</a>
      <a href="javascript:stateFilter('挂起中')" class="btn btn-info btn-sm">项目挂起中</a>
      <a href="javascript:stateFilter('已结束')" class="btn btn-info btn-sm">项目已结束</a>
      </div>
      <div class="pull-right"><a href="<%=basePath%>demand/getAllDemand" class="btn btn-success">+添加新产品</a></div>
     </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 产品管理  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
              <input type="hidden" id="finddemand_name" value="${ demand_name}">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>产品名称</th>
                        <th>产品描述</th> 
                        <th>发布人</th>
                        <th>发布日期</th>
                        <!--<th>产品类型</th>-->  
                        <th>产品状态</th>
                        <th>产品下载</th>
                        <th>需求</th>
                        <th>项目</th>                       
                        <th>操作</th>      
                      </tr>
                    </thead>
                    <tbody>      
                    <c:if test="${!empty productList}">
				          <c:forEach items="${productList}" var="product">
                    <tr>                                      
                      <td>${product.product_name}</td>
                      <td>${product.product_depict}</td>
                      <td>${product.user.user_name}</td>
                      <td><fmt:formatDate value="${product.product_submitTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>                
                       <!--<td>${product.product_type}</td>-->  
                        <td>${product.product_state}</td>
                      <td><a href="<%=basePath%>product/downProduct?id=${product.product_id}">下载</a></td>
                        <td>${product.demand.demand_name}</td>
                        <td>${product.project.project_name}(${product.project.project_state})</td>
                      
                      <td><div class="btn-group">
                          <c:choose> 
                          <c:when test="${product.project.project_state == '进行中' || product.project.project_state == null}">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          </c:when>
                          <c:otherwise>
                          <button type="button" class="btn btn-default disabled dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          </c:otherwise>
                           </c:choose> 
                          <ul class="dropdown-menu">
                            <li><a href="">暂定</a></li>
                            <!--  <li role="separator" class="divider"></li> -->
                          </ul>
                        </div>
                      </td>
                     
                    </tr>
                    </c:forEach>
                    </c:if>    
                    </tbody>                 
                  </table>
                </form>              
				 </section>
            </div>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
