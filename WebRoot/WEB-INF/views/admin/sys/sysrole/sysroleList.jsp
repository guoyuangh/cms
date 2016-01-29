<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/open/common.admininc.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tab/sysrole.js"></script>

</head>
<body>
<form:form id="listForm" action="#" method="post" modelAttribute="bean">
<jsp:include page="/open/common.nav.jsp"></jsp:include>
<table class="searchTable" width="100%" border="0" cellpadding="0">
	<tr>
		<td width="10%"></td>
		
		<td width="8%" align="right">权限角色编码：</td>
		<td width="12%"><form:input path="roleCode" cssClass="searchText"/></td>
		
		<td width="8%" align="right">权限角色名称：</td>
		<td width="12%"><form:input path="roleName" cssClass="searchText"/></td>
		
		<td width="8%" align="right">创建时间：</td>
		<td width="12%"> 
			<form:input id="firstTime" path="firstTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssClass="searchTime"/>
		</td>
		
		<td width="8%" align="right">至：</td>
		<td width="12%">		
			<form:input id="lastTime" path="lastTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  cssClass="searchTime"/>
		</td>
		
		<td width="10%"></td>
	</tr>
	<tr>
		<td colspan="4" width="40%"></td>
		
		<td width="12%" align="right"><input type="submit" value="" class="tab_search not_border ml10 mr10"></td>
		<td width="8%"><input id="resetBtn" type="button" value="" class="tab_reset not_border"></td>
		
		<td colspan="4" width="40%"></td>
	</tr>
</table>
<table  width="100%" height="5">
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
                   <a id="addBtn" href="#">
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/22.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >新增</span>
                   	</a>
                   </td>
                   	<td width="5"></td>
                   <td>
                   <a id="updateBtn" href="#">
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/33.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >修改</span>
                   </a>
                   </td>
                    <td width="5"></td>
                   <td>
                   <a id="deleteBtn" href="#">
                   	<span class="operatorBtn"><img align="bottom" src="<%=request.getContextPath()%>/resources/images/tab/11.gif" width="14" height="14" /></span>
                   	<span class="operatorBtn" >删除</span>
                   	</a>
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
              <input type="checkbox" id="selectAllOrNoneBtn" onclick="listSelectAllOrNone(this,'roleId');"/>
            </th>
            <th width="10%">权限角色编码</th>
            <th width="10%" >权限角色名称</th>
            <th width="40%">备注</th>
            <th width="15%">修改日期</th>
            <th width="15%">创建日期</th>
            <th width="10%">操作</th>
          </tr>
          	<c:forEach items="${list}" var="bean">
          <tr>
            <td><div align="center">
              <input type="checkbox" onclick="setSelectAllOrNoneBtn(this,'selectAllOrNoneBtn');" name="roleId" value="${bean.roleId}" />
            </div></td>
            <td><a href="<%=request.getContextPath() %>/admin/sysuser/sysuserList.do?sysRole.roleCode=${bean.roleCode}">${bean.roleCode}</a></td>
            <td>${bean.roleName}</td>
            <td>${bean.remark}</td>
            <td>${bean.arcDate}</td>
            <td>${bean.crtDate}</td>
            <td><a href="#" onclick="showOneUpdateModal('${bean.roleId}')">修改</a>&nbsp;|&nbsp;<a href="#"  onclick="deleteModalDo('${bean.roleId}')">删除</a></td>
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
<jsp:include page="/open/common.one.row.modal.jsp"/>
</body>
</html>