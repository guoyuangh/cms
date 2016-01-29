<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>web首页</title>
</head>
<body bgcolor="F5F5F5">
<hr/>
<h3>项目名称：爱文通用CMS后台管理系统</h3><br/>

联系QQ:1921768786<br/>
cms by:<b>刘学文/liuxuewen</b>
<hr/>
<h3>说明：</h3><br/>
本项目是一个通用cms后台管理系统。包含通用的<b>管理员用户的登录和管理/系统模块/权限角色控制/操作日志</b>，可以在该基础上进行二次开发，可以快速开发项目，缩短时间。<br/>
项目框架：SpringMVC+MyBatis进行开发，前端采用bootstrap中的[bootstrap-modal.js]模态框弹窗框架和[bootstrap-validation.js]表单验证框架。还采用了其他js前端框架：My97DatePicker日期框架和zTree树模型框架。<br/>
快速运行项目：把目录db下的cms.sql导入到数据库，再修改db.properties中的数据库用户名和密码。（导入已经有数据，登录名：admin 密码：admin &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/login.do">登录</a>）
<hr/>
<br/><br/><h3>包名：</h3><br/>
<img alt="包名" src="<%=request.getContextPath()%>/resources/img/package.jpg"/>
<hr/>
<br/><br/><h3>资源目录：</h3><br/>
<img alt="资源目录" src="<%=request.getContextPath()%>/resources/img/res.jpg"/>
<hr/>
<br/><br/><h3>界面：</h3><br/>
<br/><br/>登录页<br/>
<img alt="登录页" src="<%=request.getContextPath()%>/resources/img/login.jpg"/>
<br/><br/>管理员首页<br/>
<img alt="管理员首页" src="<%=request.getContextPath()%>/resources/img/adminIndex.jpg"/>
<br/><br/>系统管理/管理员用户管理<br/>
<img alt="系统管理/管理员管理" src="<%=request.getContextPath()%>/resources/img/sysuser.jpg"/>
<br/><br/>管理员用户管理/添加管理员用户<br/>
<img alt="管理员用户管理/添加管理员用户" src="<%=request.getContextPath()%>/resources/img/add_sysuser.jpg"/>
<br/><br/>管理员用户管理/修改管理员用户<br/>
<img alt="管理员用户管理/修改管理员用户" src="<%=request.getContextPath()%>/resources/img/update_sysuser.jpg"/>
<br/><br/>管理员用户管理/删除管理员用户<br/>
<img alt="管理员用户管理/删除管理员用户" src="<%=request.getContextPath()%>/resources/img/delete_sysuser.jpg"/>
</body>
</html>