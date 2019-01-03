<%@ page language="java" contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Jurisdiction.jsp' starting page</title>
   <script type="text/javascript">
	function del(id){
		
		$.get("<%=basePath%>jurisdictionGroup/delJurisdictionGroup?id=" + id,function(data){
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
      <h3> 组织管理 <span>
      <a href="<%=basePath%>user/getAllUser">员工</a>
      <a href="<%=basePath%>department/getAllDepartment">部门</a>
      <a href="<%=basePath%>post/getAllPost">职称</a>
      <a href="<%=basePath%>userTask/getAllUserTask">任务</a>
      <!-- <a href="<%=basePath%>jurisdictionGroup/getAllJurisdictionGroup">组</a>
      <a href="<%=basePath%>jurisdiction/getAllJurisdiction">权限</a>-->
      </span></h3>
      <div class="pull-right"><a href="<%=basePath%>jurisdictionGroup/toAddJurisdictionGroup" class="btn btn-success">+添加新组</a></div>
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 权限组管理  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>组名</th>
                        <th>描述</th>
                        <th>权限</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:if test="${!empty jurisdictionGroupList }">
				          <c:forEach items="${jurisdictionGroupList}" var="jurisdictionGroup">
                    <tr>
                      
                      <td>${jurisdictionGroup.jurisdictionGroup_name}</td>
                      <td>${jurisdictionGroup.jurisdictionGroup_depict}</td>
                      
                      <td>${jurisdictionGroup.jurisdiction}</td>
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>jurisdictionGrou/getJurisdictionGrou?id=${jurisdictionGroup.jurisdictionGroup_id}">编辑</a></li>
                            <li role="separator" class="divider"></li>                        
                            <li><a href="javascript:del('${jurisdictionGroup.jurisdictionGroup_id}')">删除</a></li>
   
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
