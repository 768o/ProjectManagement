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
    <base href="<%=basePath%>">
     <script type="text/javascript" src="/xhSmart/js/wxLin/linFilter.js"></script>
     <script type="text/javascript" src="/xhSmart/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
     
    <title>My JSP 'main_user.jsp' starting page</title>
   <script type="text/javascript">
	function del(id){
		
		$.get("<%=basePath%>demand/delDemand?id=" + id,function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败，外键约束");
			}
		});
	}
	   //根据页数查询数据列表
    function queryRequirListByPage(i) {
        $.ajax({
            url:"<%=basePath%>demand/getAllDemand?pageNow="+i,
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
            url:"<%=basePath%>demand/getAllDemand?pageNow="+pageNow,
            type:"GET",
            success:function(datas){ 
            	 $("body").html(datas);                                        
            },
            error:function(){
                alert("失败");
            },
            });
    }
   function findAllByPro(id){
	   $("#demandByProBody").empty();
	   if(id != -1){
		   var timestamp = Date.parse(new Date());
		   $.get("<%=basePath%>demand/getAllDemandByPro?id="+id,function(data){
			   for(var index in data){
				   $("#demandByProBody").append("<tr>");
				   $("#demandByProBody").append("<td>"+data[index].demand_name+"</td>");
				   $("#demandByProBody").append("<td>"+data[index].demand_depict+"</td>");
				   $("#demandByProBody").append("<td>"+data[index].demand_submitTime+"</td>");
				   $("#demandByProBody").append("<td>"+data[index].demand_type+"</td>");
				   $("#demandByProBody").append("<td>"+"下载"+"</td>");
				   $("#demandByProBody").append("</tr>");
			   }
			  
		   },"JSON");
	   }
   }
   $(function(){
   	
   	$("#searchButton").click(function(){
  
   		var keyword = $("#keywords").val();
   		//alert(name);
   		var path = "<%=basePath%>demand/getAllDemand?keyword=" + keyword;
   		$("#search").attr("action", path).submit();
   		//$("#search").submit();
   	});
   });
 
</script>
  </head>
  
  <body>
  <!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">需求</h4>
            </div>
            <div class="modal-body">
              <table class="table table-bordered table-striped table-condensed">
             	 <thead>
                      <tr>
                        <th>需求名称</th>
                        <th>需求描述</th>
                        <th>发布日期</th>
                        <th>需求类型</th>     
                        <th>需求文档</th>
                      </tr>
                    </thead>
                    <tbody id="demandByProBody"> 
                    </tbody>     
              </table>
         
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
  
  <div class="main-content" >  
    <div class="header-section">     
      <a class="toggle-btn"><i class="fa fa-bars"></i></a>
      <form action="" id="search" method="post">
     <div class="searchform">
        <input type="text" class="form-control" name="name" id="keywords" value="${demandkeyword}" placeholder="请输入搜索条件" />
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
      <h3> 组织管理 <span>
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
            <header class="panel-heading"> 我的需求  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed" id="table_demand">
                    <thead>
                      <tr>
                        <th>需求名称</th>
                        <th>需求描述</th> 
                        <th>发布人</th>
                        <th>发布日期</th>
                        <!-- <th>需求类型</th>     -->
                        <th>需求文档</th>
                        <th>需求状态</th>
                        <th>项目</th>
                        <th>操作</th>      
                      </tr>
                    </thead>
                    <tbody>      
                    <c:if test="${!empty demandList}">
				    <c:forEach items="${demandList}" var="demand">  
				   
				    <tr>
                      <td><a data-toggle="modal" data-target="#myModal" onclick="javascript:findAllByPro(${demand.project.project_id})">${demand.demand_name}</td>
                      <td>${demand.demand_depict}</td>
                      <td>${demand.user.user_name}</td>
                      <td><fmt:formatDate value="${demand.demand_submitTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>                
                      <!--<td>${demand.demand_type}</td>     --> 
                      <td><a href="<%=basePath%>demand/downDemand?id=${demand.demand_id}">下载</a></td>
                      <td>${demand.demand_state}</td>
                      <td><a href="<%=basePath%>project/getAProject?id=${demand.project.project_id}">${demand.project.project_name}</a></td>   
                       <td><div class="btn-group">
                         
                          <c:if test="${demand.demand_state eq '已立项'}">
                          <button type="button" class="btn btn-default dropdown-toggle disabled" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                          </c:if>
                          <c:if test="${demand.demand_state eq '等待立项'}">
                          <button type="button" class="btn btn-default dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                          </c:if>
                                              操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                           <li><a href="<%=basePath%>project/toAddProject?id=${demand.demand_id}">立项</a></li>
                            
                            <li role="separator" class="divider"></li>
                            <li><a href="<%=basePath%>demand/getDemand?id=${demand.demand_id}">编辑</a></li>
                            
                            <li role="separator" class="divider"></li>
                            
                            <li><a href="javascript:del('${demand.demand_id }')">删除</a></li>
   
                          </ul>
                        </div></td>
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
