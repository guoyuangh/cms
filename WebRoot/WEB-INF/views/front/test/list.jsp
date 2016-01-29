<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/resources/css/page.css" rel="stylesheet"/>

<title>test首页</title>
</head>
<body>
test list<hr size="3"/>
<div id="testPagination">
<form:form id="listForm" action="#" method="post" modelAttribute="bean">
name:<form:input path="name"/>
<input type="submit" value="搜索"><hr/>
	<c:forEach items="${list}" var="bean">
		${bean.name}<br/>
	</c:forEach>
	<jsp:include page="/open/page.inc.jsp" />
</form:form>
</div>
</body>
</html>