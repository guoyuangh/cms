<%@page import="cn._2vin.common.bean.Pager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Object pager = request.getAttribute("pager");
	if(null != pager && pager instanceof Pager){
		
	%>
	<!-- 分页的页数信息 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="STYLE4">&nbsp;&nbsp;共有 ${requestScope.pager.totalSize} 条记录，当前第 ${requestScope.pager.currentPage}/${requestScope.pager.totalPage} 页</td>
    <td>
    <table border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
	<c:if test="${requestScope.pager.currentPage > 1}">
		<td width="40">
			<a href="#" onclick="selectPage(1)"><img src="<%=request.getContextPath()%>/resources/images/tab/first.gif" width="37" height="15" /></a>
		</td>
	</c:if>
	<c:if test="${requestScope.pager.currentPage - 1 >= 1}">
		<td width="45">
			<a href="#" onclick="selectPage(${requestScope.pager.currentPage - 1})"><img src="<%=request.getContextPath()%>/resources/images/tab/back.gif" width="43" height="15" /></a>
		</td>
	</c:if> 
	<c:if test="${requestScope.pager.currentPage + 1 <= requestScope.pager.totalPage}">
		<td width="45">
			<a href="#" onclick="selectPage(${requestScope.pager.currentPage + 1})"><img src="<%=request.getContextPath()%>/resources/images/tab/next.gif" width="43" height="15" /></a>
		</td>
	</c:if>
	<c:if test="${requestScope.pager.currentPage < requestScope.pager.totalPage}">
		<td width="40">
			<a href="#" onclick="selectPage(${requestScope.pager.totalPage})"><img src="<%=request.getContextPath()%>/resources/images/tab/last.gif" width="37" height="15" /></a>
		</td>
	</c:if>
		<%
		Integer tp = ((Pager)pager).getTotalPage();
		if(null != tp && tp > 0){
		%>
		<td width="100">
			快速翻页 <select onchange="gotoPage(this);">
		<%
			for (int i = 1; i <= tp; i++){
				if (i == ((Pager)pager).getCurrentPage().intValue())
				{
					out.print("<option selected='selected'>" + i
							+ "</option>");
				} else
				{
					out.print("<option>" + i + "</option>");
				}
			}
		}
		%>
</select>
</td>
<%
}
%>
    </table></td>
  </tr>
</table>
<input type="hidden" id="listCurrentPage" name="pager.currentPage" value="${requestScope.pager.currentPage}"/>
