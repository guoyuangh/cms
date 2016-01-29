<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
//验证是否存在相同的唯一键
function checkUniqueKey(value){
	var flag=false;
	var url=appCtx+"admin/sysrole/checkRoleCode.do";
	var data={};
	data.roleCode=$("input[data-id=roleCode]").val();
	data.roleId=$("input[data-id=roleId]").val();
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
<form:form id="addOrUpdateModalForm" method="post" modelAttribute="bean">
   <form:hidden path="roleId" data-id="roleId"/>
	<div class="content-modal" style="width: 350px;">
	
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="roleCode">权限角色编码：</label>
				<div class="controls">
					<form:input data-id="roleCode" path="roleCode" cssClass="inputText" check-type="required checkUniqueKey" required-message="权限角色编码不能为空!"/>
					<font class="red">*</font>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="roleName">权限角色名称：</label>
				<div class="controls">
					<form:input data-id="roleName" path="roleName" check-type="required" required-message="权限角色名称不能为空!" cssClass="inputText"/>
					<font class="red">*</font>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="remark">备注：</label>
				<div class="controls">
					<form:textarea path="remark" rows="5"  cssClass="textareaText"/>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="roleName">模块授权：</label>
				<div class="controls">
				<form:hidden  path="treeSysModuleModalIds"/>
				<!--<font class="red" style="float: right; padding-right: 15px;">*</font>  -->
				<ul id="treeSysModuleModalForm" class="ztree" style="width: 160px;"></ul>
				</div>
			</div>
		</div>
	</div>
</form:form>