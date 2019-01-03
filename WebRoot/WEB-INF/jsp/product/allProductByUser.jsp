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
   	
    function queryRequirListByPage(i) {
        $.ajax({
            url:"<%=basePath%>product/getAllProduct?pageNow="+i,
            type:'GET',
            success:function(datas){ 
                $("body").html(datas);                                       
            },
            error:function(){
                alert("失败");
            },
            });
    }

    //根据下拉查询数据列表
    function selectPage(obj){
        var pageNow=obj.options[obj.selectedIndex].value;
        $.ajax({
            url:"<%=basePath%>product/getAllProduct?pageNow="+pageNow,
            type:"GET",
            success:function(datas){ 
            	 $("body").html(datas);                                        
            },
            error:function(){
                alert("失败");
            },
            });
    }
    function changeState(id,state){
    	$.get("<%=basePath%>product/changeProductState?id=" + id +"&state="+state, function(data){
			if("success" == data.result){
				alert("状态修改成功");
				window.location.reload();
			}else{
				alert("状态修改失败");
			}
		});
    }
    $(function(){
       	
       	$("#searchButton").click(function(){   
       		var keyword = $("#keywords").val();
       		//alert(name);
       		var path = "<%=basePath%>product/getAllProduct?keyword=" + keyword;
       		$("#search").attr("action", path).submit();
       		//$("#search").submit();
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
        <input type="text" class="form-control" name="name" id="keywords" value="${productkeyword}" placeholder="请输入搜索条件" />
        <button class="btn btn-primary" id="searchButton">搜索</button>
     </div>
     </form>
	<div class="menu-right">
        <button class="btn btn-default btn-xs disabled"><i class="glyphicon glyphicon-user"></i>${sessionScope.user.user_name} </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/getUser?id=${sessionScope.user.user_id}"><i class="fa fa-cog"></i> 更改资料</a> </button>
        <button class="btn btn-default btn-xs"><a href="<%=basePath%>user/logout?id=${sessionScope.user.user_id}"><i class="fa fa-sign-out"></i> 退出</a> </button>
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
      <a href="javascript:stateFilter('开发中')" class="btn btn-info btn-sm">开发中</a>
      <a href="javascript:stateFilter('测试中')" class="btn btn-info btn-sm">测试中</a>
      <a href="javascript:stateFilter('已完成')" class="btn btn-info btn-sm">已结束</a>
      </div>
     </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 我的产品  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
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
                        <td><a href="<%=basePath%>project/getAProject?id=${product.project.project_id}">${product.project.project_name}</a></td>
                      
                      <td><div class="btn-group">
                         <button type="button" class="btn btn-default"><a href="<%=basePath%>product/getProduct?id=${product.product_id}">编辑</a></button>
                         <button type="button" class="btn btn-default"><a href="javascript:changeState('${product.product_id }',0)">开发阶段</a></button>
                         <button type="button" class="btn btn-default"><a href="javascript:changeState('${product.product_id }',1)">测试阶段</a></button>
                         <button type="button" class="btn btn-default"><a href="javascript:changeState('${product.product_id }',2)">结束</a></button>
                        </div>
                      </td>
                     
                    </tr>
                    </c:forEach>
                    </c:if>    
                    </tbody>                 
                  </table>
                </form>              
				 </section>
				  <div id="rePagerDiv" class="rePagerDiv box">
                        <nav>
                          <ul class="pager">
                              <!-- 判断当前页是否位1，如果不为1则显示上一页， -->   
                              <c:choose>
                              <c:when test="${1 == page.pageNow}">     
                              </c:when>
                              <c:otherwise>
                                <li>        
                                  <a href="javascript:void(0)" aria-label="Previous" onclick="queryRequirListByPage(${page.pageNow-1})">
                                    <span aria-hidden="true">&laquo;</span>
                                  </a>
                                </li>     
                             </c:otherwise>
                             </c:choose>
                            <!-- 首页 -->                              
                            <li><a href="javascript:void(0)" onclick="queryRequirListByPage(1)">首页</a></li>
                            <li>
                                <span><span class="main-color">${page.pageNow}</span>/&nbsp;${page.totalPageCount}页</span>                              
                            </li>
                            <!-- 尾页 -->                              
                            <li><a href="javascript:void(0)" onclick="queryRequirListByPage(${page.totalPageCount})">尾页</a></li>

                            <!-- 判断当前页和总页数，小于则显示下一页， -->    
                            <c:if test="${page.pageNow < page.totalPageCount}">
                                <li>
                                  <a href="javascript:void(0)" aria-label="Next" onclick="queryRequirListByPage(${page.pageNow+1})">
                                    <span aria-hidden="true">&raquo;</span>
                                  </a>
                                </li>
                            </c:if>
                            <li>
                               <span class="skipPageSpan">跳转到第&nbsp;
                                <select onchange="selectPage(this)">
                                <c:forEach begin="1" end="${page.totalPageCount}"  step="1" var="pageNow">  
                                <c:choose>                                       
                                        <c:when test="${pageNow == page.pageNow}">
                                            <option selected="selected" value="${pageNow}" >${pageNow}</option>     
                                          </c:when>
                                          <c:otherwise>
                                                <option value="${pageNow}">${pageNow}</option>          
                                          </c:otherwise>    
                                </c:choose>            
                                </c:forEach>                                                                  
                                </select>
                               &nbsp;页
                               </span>
                             </li>
                          </ul>
                        </nav>
                    </div>            
           
            </div>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
