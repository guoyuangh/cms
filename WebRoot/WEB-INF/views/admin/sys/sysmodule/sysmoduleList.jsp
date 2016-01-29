<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/open/common.admininc.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tab/sysmodule.js"></script>
<style type="text/css">
.tdF5F5F5{background-color: #F5F5F5 !important;}
.tablehide{display: none;}
</style>
<script type="text/javascript">
	function showOrHideChildren(childrenModuleCss){
		$("."+childrenModuleCss).each(function(){
			var css = $(this).attr("class");
			if(-1 != css.indexOf("tablehide")){
				$(this).attr("class","tableshow "+childrenModuleCss);
				$(this).show();
			}else{
				$(this).hide();
				$(this).attr("class","tablehide "+childrenModuleCss);
			}
		});
	}
</script>
</head>
<body>
<form:form id="listForm" action="#" method="post" modelAttribute="bean">
<jsp:include page="/open/common.nav.jsp"></jsp:include>
<table class="searchTable" width="100%" border="0" cellpadding="0">
	<tr>
		<td width="11%"></td>
		
		<td width="10%" align="right">模块名称：</td>
		<td width="12%"><form:input path="moduleName" cssClass="searchText"/></td>
		
		<td width="10%" align="right">TARGET：</td>
		<td width="12%"><form:input path="targetString" cssClass="searchText"/></td>
		
		<td width="10%" align="right">权限编码：</td>
		<td width="12%"><form:input path="rightCode" cssClass="searchText"/></td>
		
		<td width="11%"></td>
	</tr>
	<tr>
		<td width="11%"></td>
		
		<td width="10%" align="right">创建时间：</td>
		<td width="12%"> 
			<form:input id="firstTime" path="firstTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" cssClass="searchTime"/>
		</td>
		
		<td width="10%" align="right">至：</td>
		<td width="12%">		
			<form:input id="lastTime" path="lastTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  cssClass="searchTime"/>
		</td>
		
		<td width="10%" align="right"><input type="submit" value="" class="tab_search not_border ml10 mr10"></td>
		<td width="12%"><input id="resetBtn" type="button" value="" class="tab_reset not_border"></td>
		
		<td width="11%"></td>
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
              <input type="checkbox" id="selectAllOrNoneBtn" onclick="listSelectAllOrNone(this,'moduleId');"/>
            </th>
            <th width="8%">模块名称</th>
            <th width="18%">模块URL</th>
            <th width="13%">URL的TARGET</th>
            <th width="10%">权限编码</th>
            <th width="8%">排序号</th>
            <th width="15%">修改日期</th>
            <th width="15%">创建日期</th>
            <th width="10%">操作</th>
          </tr>
          	<c:forEach items="${list}" var="bean">
          <tr>
            <td><div align="center">
              <input type="checkbox" onclick="setSelectAllOrNoneBtn(this,'selectAllOrNoneBtn');" name="moduleId" value="${bean.moduleId}" />
            </div></td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.moduleName}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.moduleUrl}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.targetString}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.rightCode}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.seqNum}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.arcDate}</td>
            <td title="点击查看或者隐藏所属模块" onclick="showOrHideChildren('childrenModule${bean.moduleId}')">${bean.crtDate}</td>
            <td><a href="#" onclick="showOneUpdateModal('${bean.moduleId}')">修改</a>&nbsp;|&nbsp;<a href="#"  onclick="deleteModalDo('${bean.moduleId}')">删除</a></td>
          </tr>
     	<c:forEach items="${bean.children}" var="child">
          <tr  class="tablehide childrenModule${bean.moduleId}">
            <td class="tdF5F5F5"></td>
            <td class="tdF5F5F5">${child.moduleName}</td>
            <td class="tdF5F5F5">${child.moduleUrl}</td>
            <td class="tdF5F5F5">${child.targetString}</td>
            <td class="tdF5F5F5">${child.rightCode}</td>
            <td class="tdF5F5F5">${child.seqNum}</td>
            <td class="tdF5F5F5">${child.arcDate}</td>
            <td class="tdF5F5F5">${child.crtDate}</td>
            <td class="tdF5F5F5"><a href="#" onclick="showOneUpdateModal('${child.moduleId}')">修改</a>&nbsp;|&nbsp;<a href="#"  onclick="deleteModalDo('${child.moduleId}')">删除</a></td>
          </tr>
         </c:forEach>
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