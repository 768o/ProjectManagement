<%@ page language="java" contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main_user.jsp' starting page</title>
    <meta name="decorator" content="default"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
       <div class="page-heading">
      <h3> 组织管理 <span>
      <a href="/user/manage">员工</a>
      <a href="/department/manage">部门</a>
      <a href="/position/manage">职称</a>
      <a href="/group/manage">组</a>
      <a href="/permission/manage">权限</a></span></h3>
      <ul class="breadcrumb pull-left">
        <li> <a href="/user/show/1461312703628858832">xhSmart</a> </li>
        <li> <a href="/user/manage">员工管理</a> </li>
        <li class="active"> 员工 </li>
      </ul>
      <div class="pull-right"><a href="/user/add" class="btn btn-success">+添加新员工</a></div>
    </div>
    
    
    <div class="wrapper">
      <div class="row">
        <div class="col-sm-12">
          <section class="panel">
            <header class="panel-heading"> 员工管理 / 总数：14<span class="tools pull-right"><a href="javascript:;" class="fa fa-chevron-down"></a>
              
              </span> </header>
            <div class="panel-body">
              <section id="unseen">
                <form id="user-form-list">
                  <table class="table table-bordered table-striped table-condensed">
                    <thead>
                      <tr>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>职务</th>
                        <th>状态</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    
                    <tr>
                      <td>lock</td>
                      <td><a href="/user/show/65140463652311040">lock</a></td>
                      <td>男</td>
                      <td>2017-07-12</td>
                      <td>屏蔽</td>
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="/user/edit/65140463652311040">编辑</a></li>
                            
                            <li role="separator" class="divider"></li>
                            
                            <li><a href="javascript:;" class="js-user-single" data-id="65140463652311040" data-status="1">正常</a></li>
   
                          </ul>
                        </div></td>
                    </tr>          
                    <tr>
                      <td>lisi</td>
                      <td><a href="/user/show/1468140265954907628">李四</a></td>
                      <td>男</td>
                     
                      <td></td>
                      <td>正常</td>
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="/user/edit/1468140265954907628">编辑</a></li>
                            
                            <li role="separator" class="divider"></li>
                            
                            <li><a href="javascript:;" class="js-user-single" data-id="1468140265954907628" data-status="2">屏蔽</a></li>
                      
                          </ul>
                        </div></td>
                    </tr>
                    
                    <tr>
                      <td>fancy</td>
                      <td><a href="/user/show/1468915433602979028">朱笑天</a></td>
                      <td>男</td>
                     
                      <td></td>
                      <td>正常</td>
                      <td><div class="btn-group">
                          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 操作<span class="caret"></span> </button>
                          <ul class="dropdown-menu">
                            <li><a href="/user/edit/1468915433602979028">编辑</a></li>
                            
                            <li role="separator" class="divider"></li>
                            
                            <li><a href="javascript:;" class="js-user-single" data-id="1468915433602979028" data-status="2">屏蔽</a></li>
					
                          </ul>
                        </div></td>
                    </tr>
                    
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
