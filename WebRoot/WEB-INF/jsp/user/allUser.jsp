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
    
    <title>My JSP 'main_user.jsp' starting page</title>
   <script type="text/javascript">
	function del(id){
		
		$.get("<%=basePath%>user/delUser?id=" + id,function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败，外键约束");
			}
		});
	}
	function changeState(id){
		
		$.get("<%=basePath%>user/changeState?id=" + id,function(data){
			if("success" == data.result){
				alert("状态已修改");
				window.location.reload();
			}else{
				alert("修改失败");
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
      <div class="pull-right"><a href="<%=basePath%>user/toAddUser" class="btn btn-success">+添加新员工</a></div>
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 员工管理  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>账号</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>职务</th>
                        <th>管理员</th>
                        <th>状态</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:if test="${!empty userList }">
				          <c:forEach items="${userList}" var="user">
                    <tr>
                    
                      <td>${user.user_loginName}</td>
                      <td>${user.user_name}</td>
                      <td>${user.department.department_name}</td>
                      <td>${user.post.post_name}</td>
                      <td>
                      <c:choose>
                            <c:when test="${user.isadmin == 0}">
                           		否
                            </c:when>
                           	<c:otherwise>
                            	是
                           	</c:otherwise>
                        </c:choose>
                     </td>
                      <td>
                      	<c:choose>
                            <c:when test="${user.user_isdelete == 0}">
                           		正常
                            </c:when>
                           	<c:otherwise>
                            	屏蔽
                           	</c:otherwise>
                        </c:choose>
                      </td>
                      
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>user/getUser?id=${user.user_id}">编辑</a></li>
                            <li role="separator" class="divider"></li>  
                            <li>
                            <c:choose>
                            	<c:when test="${user.user_isdelete == 0}">
                           			 <a href="javascript:changeState('${user.user_id }')">屏蔽</a>
                            	</c:when>
                           		 <c:otherwise>
                            	 	<a href="javascript:changeState('${user.user_id }')">正常</a>
                           		 </c:otherwise>
                            </c:choose>
                            </li>
                            <li role="separator" class="divider"></li>                          
                            <li><a href="javascript:del('${user.user_id }')">删除</a></li>
   
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
