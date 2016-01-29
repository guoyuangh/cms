<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<s:url value="/"/>resources/css/error.css" rel="stylesheet"/>
	<title>找不到页面</title>
</head>

<body>
	<div class="alert alert-error">
	  	<div class="content">
			<span>很抱歉，没有您所请求的页面，您确定输入的URL正确吗？ </span><br/>
			<a href="<s:url value="/"/>">返回首页</a>
		</div>
	</div>
</body>
</html>