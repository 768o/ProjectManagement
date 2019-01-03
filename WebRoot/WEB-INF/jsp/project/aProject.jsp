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
    <script type="text/javascript" src="/xhSmart/js/wxLin/linFilter.js"></script>
    <script type="text/javascript" src="/xhSmart/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/xhSmart/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/xhSmart/js/jquery.form.js"></script>

    <title>My JSP 'main_user.jsp' starting page</title>
  <script>
  $(function(){
	 $("#left2").click(function(){
		 $("#t2").slideToggle(500);
	 }); 
  });
  $(function(){
		 $("#left3").click(function(){
			 $("#t3").slideToggle(500);
		 }); 
	  });
  $(function(){
		 $("#left4").click(function(){
			 $("#t4").slideToggle(500);
		 }); 
	  });
  
  $(document).ready(function(){
		$("#postForm").validate();
	});
  function AddFeedback(feedback_name){
	  $("#feedback_name").val(feedback_name);
  }
  $(function(){
	  $("#product_button_add").click(function(){  
		  $("#product_demand").empty();
		 $(".demand_demand").each(function(){
			 $("#product_demand").append("<option value=\""+this.id+"\">"+$(this).val()+"</option>");
		    //alert(this.id+$(this).val())
	  });
		 
	  });
  });
  $(function(){
		$("#productButton").click(function(){
			$("#productForm").submit();
			//location.href="http://www.baidu.com";
		    //window.location.reload();
		});
	});
  $(function(){
		$("#demandButton").click(function(){
		    $("#demandForm").submit();
		});
	});
  
 	$(function(){
 		$("#feedbackButton").click(function(){
 			$.ajax({
 				type : "POST",
 			    url : "<%=basePath%>feedback/addFeedback",
 			    dataType : "json",
 			    data : {
 			    	feedback_depict:$("#feedback_depict").val(),
 			  		feedback_name:$("#feedback_name").val(),
 			  		project_id:parseInt($("#project_id").val()),
 			    },
 			    success : function(data){
 					//var obj = eval("("+data+")"); //要把json数据转化为对象
 					$("#feedbackTr").empty();
 					for (var index in data){
 						$("#feedbackTr").append("<tr><td>"+data[index].feedback_time+"</td>"
 													+"<td>"+data[index].feedback_name+"</td>"
 													+"<td>"+data[index].feedback_depict+"</td>"
 													+"<td>"+data[index].user_id+"</td></tr>");
 			
 					}
 			    },
 			   error: function(XMLHttpRequest){  
 					     alert( "Error: " + XMLHttpRequest.responseText);  
 					   }  
 			});
 		});
  	});
 </script>
 <style type="text/css">
 td{
 	min-width:150px;
 }
 </style>

</head>
  <body>
<!-- 按钮触发模态框 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加反馈</h4>
            </div>
            <div class="modal-body">
            <form class="form-horizontal adminex-form cmxform" id="postForm">
             	<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>描述</label>
							<div class="col-sm-10">
								    <textarea rows="5" minlength="6" maxlength="300" name="feedback_depict"
									class="form-control required" placeholder="请输入反馈信息" id="feedback_depict"> </textarea>
									<input type="hidden" id="project_id" value="${project.project_id}">
                                    <input type="hidden" id="feedback_name" value="">
							</div>
						</div>
           </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="feedbackButton" data-dismiss="modal">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加产品</h4>
            </div>
            <div class="modal-body">
            <form action="<%=basePath%>product/addProduct" class="form-horizontal adminex-form cmxform" id="productForm"  method="post" enctype="multipart/form-data">
					<!-- <input type="hidden" name="demand_id" value="${demand_id}"/> -->
				<input type="hidden" name="project_id" value="${project.project_id}">
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>名称</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="product_name"
									class="form-control required" placeholder="请填写名称" id="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<textarea maxlength="140" name="product_depict"
									placeholder="请填写描述" style="height:90px;" class="form-control" id="depict"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>需求</label>
							<div class="col-sm-10">
							<select class="form-control" id="product_demand" name="demand_id">
							</select>
							</div>
					    </div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>文件</label>
							<div class="col-sm-10">
								<input type="file" class="file required" id="file" name="file" >
							</div>
						   </div>
					
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="productButton" data-dismiss="modal">提交</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">变更需求</h4>
            </div>
            <div class="modal-body">
            <form action="<%=basePath%>demand/addDemand" class="form-horizontal adminex-form cmxform" id="demandForm"  method="post" enctype="multipart/form-data">
					<!-- <input type="hidden" name="demand_id" value="${demand_id}"/> -->
				
				        <input type="hidden" name="project_id" value="${project.project_id}"/>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>名称</label>
							<div class="col-sm-10">
								<input minlength="2" maxlength="20" type="text" name="demand_name"
									class="form-control required" placeholder="请填写名称" id="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<textarea maxlength="140" name="demand_depict"
									placeholder="请填写描述" style="height:90px;" class="form-control" id="depict"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><span>*</span>文件</label>
							<div class="col-sm-10">
								<input type="file" class="file required" id="file" name="file" >
							</div>
						   </div>
					
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="demandButton" data-dismiss="modal">提交</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
  
   <div class="page-heading">
   
   <h3> 项目管理 <span>
      <a href="<%=basePath%>project/getAllProject">项目</a>
      <a href="<%=basePath%>demand/getAllDemand">需求</a>
      <a href="<%=basePath%>product/getAllProduct">产品</a>
      </span></h3>
     
   
      </div>
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 项目详细  <span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
               
               
                 <div class="pull-left"><button class="btn btn-info">项目</button></div>
                  <table class="table table-condensed table-hover table-bordered" id="t1">
                    <thead>
                      <tr>
                        <th>项目名称</th>
                        <th>项目描述</th> 
                        <th>立项人</th>
                        <th>立项时间</th>
                        <th>立项原因</th>
                        <th>状态</th>  
                        
                        <th>结束时间</th>   
                      </tr>
                    </thead>
                    <tbody>      
                    <tr>                                      
                      <td>${project.project_name}</a></td>          
                      <td>${project.project_depict}</td>
                      <td>${project.user.user_name}</td>
                      <td><fmt:formatDate value="${project.project_createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>                
                      <td>${project.project_createReason}</td>   
                      <td>${project.project_state}</td>   
                      <td><fmt:formatDate value="${project.project_endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>            
                    </tbody>                 
                  </table>
                 
                   <div class="pull-left"><button class="btn btn-info" id="left2">进度与反馈</button></div>
                    <div style="overflow-x: auto; overflow-y: auto; max-height: 500px;" id="t2" class="table">
                    <table class="table table-condensed table-hover table-bordered">
                    <thead>
                      <tr>
                        <th>时间</th>
                         <th>反馈人</th>   
                        <th>反馈项</th>
                        <th>描述</th>
                      </tr>
                    </thead>
                    <tbody id="feedbackTr">      
                    <c:if test="${!empty feedbackList}">
				    <c:forEach items="${feedbackList}" var="feedback">
                    <tr>                                      
                      <td><fmt:formatDate value="${feedback.feedback_time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>   
                      <td>${feedback.user.user_name}</td>
                      <td>${feedback.feedback_name}</td>
                      <td>${feedback.feedback_depict}</td>  
                    </tr>
                    </c:forEach>
                    </c:if>      
                    </tbody>                 
                  </table> 
                  </div>
               
                  <div class="pull-left"><button class="btn btn-info" id="left3">需求</button></div>
                  <div class="pull-right">
                  <c:if test="${project.project_state ne '已结束'}">
                   <c:if test="${allocation.task.task_name eq '需求'}">
                  <button class="btn btn-success" id="product_button" data-toggle="modal" data-target="#myModal3" class="btn btn-success">+变更需求</button>
                  </c:if>
                  </c:if>
                  </div>
                  <div style="overflow-x: auto; overflow-y: auto; max-height: 500px;" id="t3" class="table">
                  <table class="table table-condensed table-hover table-bordered">
                    <thead>
                      <tr>
                        <th>需求名称</th>
                        <th>需求描述</th> 
                        <th>发布人</th>
                        <th>发布日期</th>
                        <th>需求类型</th>  
                        <th>文件</th> 
                        <th>操作</th> 
                      </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty demandList}">
                    <input type="hidden" value="${demandList}" id="demandList">
				    <c:forEach items="${demandList}" var="demand">               
                    <tr >
                      <input type="hidden" class="demand_demand" id="${demand.demand_id}" value="${demand.demand_name}"> 
                
                      <td>${demand.demand_name}</td>
                        
                      <td>${demand.demand_depict}</td>
                      <td>${demand.user.user_name}</td>
                      <td><fmt:formatDate value="${demand.demand_submitTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>                
                      <td>${demand.demand_type}</td>     
                      <td><a href="<%=basePath%>demand/downDemand?id=${demand.demand_id}">下载</a></td> 
                      <td>
                      <c:if test="${project.project_state != '已结束'}">
                       
                      <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="AddFeedback('${demand.demand_name}')">反馈</button>
                     
                      </c:if>
                      </td>
                    </tr>   
                    
                  </c:forEach>
                    </c:if>    
                    </tbody>                 
                  </table>
                  </div>
                 <div class="pull-left"><button class="btn btn-info" id="left4">产品</button></div>
                 <div class="pull-right">
                  <c:if test="${project.project_state != '已结束'}">
                  <c:if test="${allocation.task.task_name eq '产品'}">
                 <button class="btn btn-success" id="product_button_add" data-toggle="modal" data-target="#myModal4">+添加产品</button>
                 </c:if>
                 </c:if>
                 </div>
                 <div style="overflow-x: auto; overflow-y: auto; max-height: 500px;" id="t4" class="table">
                  <table class="table table-condensed table-hover table-bordered">
                    <thead>
                      <tr>
                        <th>产品名称</th>
                        <th>产品描述</th> 
                        <th>发布人</th>
                        <th>发布日期</th>
                        <th>产品状态</th> 
                        <th>需求名称</th>
                        <th>文件</th>  
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
                      <td>${product.demand.demand_name}</td>
                      <td><a href="<%=basePath%>product/downProduct?id=${product.product_id}">下载</a></td>
                     <td> 
                      <c:if test="${project.project_state != '已结束'}">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="AddFeedback('${product.product_name}')">反馈</button>
                     </c:if>
                    </td>
                    </tr>
                    </c:forEach>
                    </c:if>    
                    </tbody>                 
                  </table>    
                  </div> 
				 </section>
            </div></div>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
