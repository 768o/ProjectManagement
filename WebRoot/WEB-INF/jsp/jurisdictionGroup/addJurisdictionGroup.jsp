<%@ page language="java" contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Jurisdiction.jsp' starting page</title>
    <script type="text/javascript" src="/xhSmart/js/jquery.min.js"></script>
<script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>
   <script type="text/javascript">
	function del(id){
		
		$.get("<%=basePath%>jurisdiction/delJurisdiction?id=" + id,function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败，外键约束");
			}
		});
	}
	$(function(){
		$(".getAll").change(function (){
		var target = $(".get");
		if ($(this).prop('checked')) {
		$(target).prop('checked', true);
		} else {
		$(target).prop('checked', false);
		}
		});
	});
	$(function(){
		$(".addAll").change(function (){
		var target = $(".add");
		if ($(this).prop('checked')) {
		$(target).prop('checked', true);
		} else {
		$(target).prop('checked', false);
		}
		});
	});
	$(function(){
		$(".updateAll").change(function (){
		var target = $(".update");
		if ($(this).prop('checked')) {
		$(target).prop('checked', true);
		} else {
		$(target).prop('checked', false);
		}
		});
	});
	$(function(){
		$(".downAll").change(function (){
		var target = $(".down");
		if ($(this).prop('checked')) {
		$(target).prop('checked', true);
		} else {
		$(target).prop('checked', false);
		}
		});
	});
	$(function(){
		
		$(".adminAll").change(function (){
		var target = $(".admin");
		if ($(this).prop('checked')) {
		$(target).prop('checked', true);
		} else {
		$(target).prop('checked', false);
		}
		});
	});
	$(function(){
		$("#post").click(function(){
			var str="";
			$(".jurisdiction").each(function(){
				if($(this).prop('checked')){
				str=str+$(this).val()+",";
				}
			});
			//alert(str);
			$("#juris").val(str);
			//alert($("#juris").val());
			$("#form-list").attr("action","<%=basePath%>jurisdictionGroup/addJurisdictionGroup");
			$("#form-list").submit();
			
		});
	});
	
</script>
  </head>
  
  <body> 
  <div class="page-heading">
      <h3> 组织管理 <span>
      <a href="<%=basePath%>user/getAllUser">员工</a>
      <a href="<%=basePath%>department/getAllDepartment">部门</a>
      <a href="<%=basePath%>post/getAllPost">职称</a>
      <a href="<%=basePath%>userTask/getAllUserTask">任务</a>
        <a href="<%=basePath%>jurisdictionGroup/getAllJurisdictionGroup">组</a>
      <a href="<%=basePath%>jurisdiction/getAllJurisdiction">权限</a>
      </span></h3>
      <div class="pull-right"></div>
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 添加权限组  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="form-list" method="post">
                  <input type="hidden" name="jurisdiction" id="juris" >
                  <table class="table table-bordered table-striped table-condensed">      
                    <tbody>
                    <tr>
                    <td><span>*</span>名称</td>
                    <td><div class="col-sm-12">
								<input minlength="2" maxlength="50" type="text" name="jurisdictionGroup_name"
									class="form-control required" placeholder="请填写名称" id="name">
							</div></td>
                    </tr>
                    <tr>
                    <td><span>*</span>描述</td>
                    <td><div class="col-sm-12">
								<input minlength="2" maxlength="50" type="text" name="jurisdictionGroup_depict"
									class="form-control required" placeholder="请填写描述" id="depict">
							</div></td>
                    </tr>
                    <tr><td> </td><td><h4>请选择权限</h4></td></tr>
				           <tr>
				           <td><input type="checkbox" class="getAll"/>查看</td> <td>
				            <c:if test="${!empty jurisdictionList }">
				          <c:forEach items="${jurisdictionList}" var="jurisdiction">
				          <c:if test="${fn:contains(jurisdiction.jurisdiction_name, 'get')}">
                     			 <input type="checkbox" class="get jurisdiction" value="${jurisdiction.jurisdiction_id}"/>${jurisdiction.jurisdiction_depict}&nbsp;&nbsp;&nbsp;&nbsp;
                     	  </c:if>
                     	  </c:forEach>
                          </c:if>
                     	  </td>
                   			 </tr>   
                   			   <tr> <td><input type="checkbox" class="addAll"/>添加</td> <td>
				            <c:if test="${!empty jurisdictionList }">
				          <c:forEach items="${jurisdictionList}" var="jurisdiction">
				          <c:if test="${fn:contains(jurisdiction.jurisdiction_name, 'Add')}">
                     			 <input type="checkbox" class="add jurisdiction" value="${jurisdiction.jurisdiction_id}" />${jurisdiction.jurisdiction_depict}&nbsp;&nbsp;&nbsp;&nbsp;
                     	  </c:if>
                     	  </c:forEach>
                          </c:if>
                     	  </td>
                   			 </tr>   
                   			   <tr> <td><input type="checkbox" class="updateAll"/>修改</td> <td>
				            <c:if test="${!empty jurisdictionList }">
				          <c:forEach items="${jurisdictionList}" var="jurisdiction">
				          <c:if test="${fn:contains(jurisdiction.jurisdiction_name, 'update')}">
                     			 <input type="checkbox" class="update jurisdiction" value="${jurisdiction.jurisdiction_id}"/>${jurisdiction.jurisdiction_depict}&nbsp;&nbsp;&nbsp;&nbsp;
                     	  </c:if>
                     	  </c:forEach>
                          </c:if>
                     	  </td>
                   			 </tr>      
                   			   <tr> <td><input type="checkbox" class="downAll"/>下载</td> <td>
				            <c:if test="${!empty jurisdictionList }">
				          <c:forEach items="${jurisdictionList}" var="jurisdiction">
				          <c:if test="${fn:contains(jurisdiction.jurisdiction_name, 'down')}">
                     			 <input type="checkbox" class="down jurisdiction" value="${jurisdiction.jurisdiction_id}" />${jurisdiction.jurisdiction_depict}&nbsp;&nbsp;&nbsp;&nbsp;
                     	  </c:if>
                     	  </c:forEach>
                          </c:if>
                     	  </td>
                   			 </tr>   
                   			  <tr> <td><input type="checkbox" class="adminAll"/>管理</td> <td>
				            <c:if test="${!empty jurisdictionList }">
				          <c:forEach items="${jurisdictionList}" var="jurisdiction">
				          <c:if test="${fn:contains(jurisdiction.jurisdiction_name, 'admin')}">
                     			 <input type="checkbox" class="admin jurisdiction" value="${jurisdiction.jurisdiction_id}" />${jurisdiction.jurisdiction_depict}&nbsp;&nbsp;&nbsp;&nbsp;
                     	  </c:if>
                     	  </c:forEach>
                          </c:if>
                     	  </td>
                   			 </tr>   
                  		
                    <tr><td> </td><td><input type="button" class="btn btn-primary" id="post" value="提交" /></td></tr>
                    
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
