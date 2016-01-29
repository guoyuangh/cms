<%@page import="cn._2vin.admin.constant.ConstantAdmin"%>
<%@page import="cn._2vin.admin.bean.SysUser"%>
<%@page import="cn._2vin.common.base.controller.BaseController"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
//验证是否存在相同的唯一键
function checkLoginName(value){
	var flag=false;
	var url=appCtx+"admin/sysuser/checkLoginName.do";
	var data={};
	data.loginName=$("input[data-id=loginName]").val();
	data.id=$("input[data-id=loginId]").val();
	$.ajax({
		type:"post",
		async:false,
		url:url,
		data:data
	}).done(function(data){
		if("success" == data.result){
			flag=false;
		}else{
			flag=true;
		}
	});
	return flag;
}
</script>
<%
/*超级管理员不能修改 用户名 和 权限角色*/
Object o = request.getAttribute(BaseController.BEAN_KEY);
boolean isSuperAdmin = false;
	if(o instanceof SysUser){
		isSuperAdmin = ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(((SysUser)o).getLoginName());
	}
%>
<form:form id="addOrUpdateModalForm" method="post" modelAttribute="bean">
   <form:hidden path="id" data-id="loginId"/>
	<div class="content-modal" style="width: 350px;">
	
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="loginName">登录名：</label>
				<div class="controls">
					<%
					if(isSuperAdmin){
					%>
					<form:input  disabled="true" path="loginName" cssClass="inputText" />
					<form:hidden data-id="loginName" path="loginName" id="hiddenLoginName"/>
					<%
					}else{%>
					<form:input data-id="loginName" path="loginName" cssClass="inputText" check-type="required checkLoginName" required-message="登录名不能为空!"/>
					<%
					}
					%>
					<font class="red">*</font>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="realName">真实姓名：</label>
				<div class="controls">
					<form:input data-id="realName" path="realName" cssClass="inputText"/>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="loginPassword">密码：</label>
				<div class="controls">
					<input type="password" data-id="loginPassword" name="loginPassword" class="inputText" placeholder='${empty bean.id?"留空使用默认密码":"留空使用原始密码"}'/>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="loginPassword">权限角色：</label>
				<div class="controls">
					<%
					if(isSuperAdmin){
					%>
					<form:input disabled="true" path="sysRole.roleName" cssClass="inputText"/>
					<form:hidden path="sysRole.roleId" id="hiddenRoleId"/>
					<%
					}else{%>
 					<form:select data-id="sysRole"  path="sysRole.roleId" cssClass="selectText" check-type="required" required-message="权限角色不能为空">
						<form:option value="" >请选择</form:option>
						<c:forEach var="item" items="${sysRoleList}">
							<form:option value="${item.roleId}">${item.roleName}</form:option>
						</c:forEach>
					</form:select>					
					<%
					}
					%>
					<font class="red">*</font>
				</div>
			</div>
		</div>
	</div>
</form:form>