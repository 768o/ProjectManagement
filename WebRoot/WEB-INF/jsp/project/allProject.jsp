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
    <script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
         <script type="text/javascript" src="/xhSmart/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/xhSmart/js/wxLin/linFilter.js"></script>
    <title>My JSP 'main_user.jsp' starting page</title>
    <script type="text/javascript">
    function changeProjectState(id,state){
		if(state==2){
			if (confirm("确定吗")==true){ 
				    changeState(id,state);
				    return true; 
				  }else{ 
				    return false; 
				  } 
		}else{
			changeState(id,state);
		}
		
	}
    function changeState(id,state){
    	$.get("<%=basePath%>project/changeProjectState?id=" + id +"&state="+state, function(data){
			if("success" == data.result){
				alert("状态修改成功");
				window.location.reload();
			}else{
				alert("状态修改失败");
			}
		});
    }
    //根据页数查询数据列表
    function queryRequirListByPage(i) {
    	
        $.ajax({
            url:"<%=basePath%>project/getAllProject?pageNow="+i,
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
            url:"<%=basePath%>project/getAllProject?pageNow="+pageNow,
            type:"GET",
            success:function(datas){ 
            	 $("body").html(datas);                                        
            },
            error:function(){
                alert("失败");
            },
            });
    }
    $(function(){
    	
    	$("#searchButton").click(function(){
   
    		var keyword = $("#keywords").val();
    		//alert(name);
    		var path = "<%=basePath%>project/getAllProject?keyword=" + keyword;
    		$("#search").attr("action", path).submit();
    		//$("#search").submit();
    	});
    });
   
    </script>  
    <script type="text/javascript">
	function del(id){
		$.get("<%=basePath%>project/delProject?id=" + id , function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败，外键约束");
			}
		});
	}
</script>
  </head>
  
  <body>
   <div class="main-content" >  
    <div class="header-section">     
      <a class="toggle-btn"><i class="fa fa-bars"></i></a>
      <form action="" id="search" method="post">
     <div class="searchform">
        <input type="text" class="form-control" name="name" id="keywords" value="${projectkeyword}" placeholder="请输入搜索条件" />
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
      <a href="<%=basePath%>project/getAllProject">我的项目</a>
      <!--  <a href="<%=basePath%>demand/getAllDemand">需求</a>-->
      <a href="<%=basePath%>allocation/getAllocation">我的任务</a>
      </span></h3>
      <div class="pull-left">
       <a href="javascript:stateFilter('')" class="btn btn-info btn-sm">全部</a> 
      <a href="javascript:stateFilter('进行中')" class="btn btn-info btn-sm">进行</a> 
      <a href="javascript:stateFilter('延期')" class="btn btn-info btn-sm">延期</a>
      <a href="javascript:stateFilter('已结束')" class="btn btn-info btn-sm">结束</a>
      </div>
      <div class="pull-right"><a href="<%=basePath%>project/toAddProject" class="btn btn-success">+添加新项目</a></div>
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 我的项目  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>项目名称</th>
                        <th>项目描述</th> 
                        <th>立项人</th>
                        <th>立项时间</th>
                        <th>结束时间</th>
                        <th>状态</th>    
                        <th>操作</th>      
                      </tr>
                    </thead>
                    <tbody>      
                    <c:if test="${!empty projectList}">
				          <c:forEach items="${projectList}" var="project">
                    <tr>                                      
                      <td><a href="<%=basePath%>project/getAProject?id=${project.project_id}">${project.project_name}</a></td>          
                      <td>${project.project_depict}</td>
                      <td>${project.user.user_name}</td>
                      <td><fmt:formatDate value="${project.project_createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      <td>${project.project_endTime}</td>             
                      <td>${project.project_state}</td>   
                    
                    
                      <td><div class="btn-group">
                         <button type="button" class="btn btn-default"><a href="javascript:changeProjectState('${project.project_id }',2)">完成</a></button>
                         <button type="button" class="btn btn-default"><a href="<%=basePath%>allocation/getAllAllocationByPro?id=${project.project_id}">任务</a></button>
                         <button type="button" class="btn btn-default"><a href="javascript:del('${project.project_id}')">删除</a></button>
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
