<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/open/common.admininc.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tab/log.js"></script>

</head>
<body>
<form:form id="listForm" action="#" method="post" modelAttribute="bean">
<jsp:include page="/open/common.nav.jsp"></jsp:include>
<table class="searchTable" width="100%" border="0" cellpadding="0">
	<tr>
		<td width="5%"></td>
		
		<td width="10%" align="right">关键字：</td>
		<td width="10%"><form:input path="action" cssClass="searchText"/></td>
		
		<td width="10%" align="right">操作时间：</td>
		<td width="15%"> 
			<form:input id="firstTime" path="firstTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssClass="searchTime"/>
		</td>
		
		<td width="10%" align="right">至：</td>
		<td width="15%">		
			<form:input id="lastTime" path="lastTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  cssClass="searchTime"/>
		</td>
		
		<td width="15%"><input type="submit" value="" class="tab_search not_border ml10 mr10"><input id="logResetBtn" type="button" value="" class="tab_reset not_border"></td>
		
		<td width="5%"></td>
	</tr>
</table>
<table height="5">
	<tr><td></td></tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="<%=request.getContextPath()%>/resources/images/tab/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=request.getContextPath()%>/resources/images/tab/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%"><table border="0" align="left" cellpadding="0" cellspacing="0">
                 <tr>
                   <td>
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/22.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >新增</span>
                   </td>
                   	<td width="5"></td>
                   <td>
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/33.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >修改</span>
                   </td>
                    <td width="5"></td>
                   <td>
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/11.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >删除</span>
                   </td>
                  </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="<%=request.getContextPath()%>/resources/images/tab/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=request.getContextPath()%>/resources/images/tab/tab_12.gif">&nbsp;</td>
        <td><table class="listTable" width="100%" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <th width="3%">
              <input type="checkbox" id="selectAllOrNoneBtn" onclick="listSelectAllOrNone(this,'id');"/>
            </th>
            <th width="10%">序号</th>
            <th width="20%">标题</th>
            <th width="45%">内容</th>
            <th width="20%">操作时间</th>
          </tr>
          	<c:forEach items="${list}" var="bean">
          <tr>
            <td>
              <input type="checkbox" onclick="setSelectAllOrNoneBtn(this,'selectAllOrNoneBtn');" name="id" value="${bean.id}" />
            </td>
            <td >${bean.id}</td>
            <td >${bean.title}</td>
            <td >
            	<input type="hidden" id="actionId_${bean.id}" value="${bean.action}">
            	<a href="#" onclick="showAllAction('actionId_${bean.id}');">${bean.actionSmall}</a>
            </td>
            <td >${bean.crtDate}</td>
          </tr>
          </c:forEach>
        </table></td>
        <td width="8" background="<%=request.getContextPath()%>/resources/images/tab/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="<%=request.getContextPath()%>/resources/images/tab/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="<%=request.getContextPath()%>/resources/images/tab/tab_18.gif" width="12" height="35" /></td>
        <td>
        	<jsp:include page="/open/page.inc.jsp" />
        </td>
        <td width="16"><img src="<%=request.getContextPath()%>/resources/images/tab/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form:form>
</body>
</html>