<%@ page language="java" contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main_user.jsp' starting page</title>
   <script type="text/javascript">
	function del(Uid,Pid){
		//alert(Uid+","+Pid);
		$.get("<%=basePath%>allocation/delAllocation?Uid=" + Uid+"&Pid=" + Pid,function(data){
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
  <div class="page-heading">
       <h3> 项目管理 <span>
      <a href="<%=basePath%>project/getAllProject">项目</a>
      <a href="<%=basePath%>demand/getAllDemand">需求</a>
      <a href="<%=basePath%>product/getAllProduct">产品</a>
      </span></h3>
      <div class="pull-right"><a href="<%=basePath%>allocation/toAddAllocation?id=${project_id}" class="btn btn-success">+添加新分工</a></div>
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 分工管理  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>项目</th>
                        <th>员工</th>
                        <th>任务</th>    
                        <th>加入时间</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:if test="${!empty allocationList }">
				          <c:forEach items="${allocationList}" var="allocation">
                    <tr>
                      
                      <td>${allocation.project.project_name}</td>
                      <td>${allocation.user.user_name}</td>
                      <td>${allocation.task.task_name}</td>
                      <td><fmt:formatDate value="${allocation.user_joinTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      
                      
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>allocation/getAllocation?project_id=${allocation.project_id}&user_id=${allocation.user_id}">编辑</a></li>
                            
                            <li role="separator" class="divider"></li>
                            
                            <li><a href="javascript:del('${allocation.user_id}','${allocation.project_id}')">删除</a></li>
   
                          </ul>
                        </div></td>
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
