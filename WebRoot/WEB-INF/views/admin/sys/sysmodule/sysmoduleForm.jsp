<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
//验证是否存在相同的唯一键
function checkUniqueKey(value){
	var flag=false;
	var url=appCtx+"admin/sysmodule/checkModuleName.do";
	var data={};
	data.moduleName=$("input[data-id=moduleName]").val();
	data.moduleId=$("input[data-id=moduleId]").val();
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
   <form:hidden path="moduleId" data-id="moduleId"/>
	<div class="content-modal" style="width: 350px;">
	
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="parentSysModule.moduleName">父模块名称：</label>
				<div class="controls">
					<c:if test="${!(empty add)}">
						<form:select data-id="parentModuleId" path="parentModuleId" cssClass="selectText" check-type="required" required-message="权限角色不能为空">
						<form:option value="0" >顶级模块</form:option>
						<c:forEach var="item" items="${parentModuleList}">
							<form:option value="${item.moduleId}">${item.moduleName}</form:option>
						</c:forEach>
					</form:select>
					</c:if>
					<c:if test="${!(empty update)}">
						<form:input data-id="parentSysModule.moduleName" path="parentSysModule.moduleName" cssClass="inputText" disabled="true"/>
						<form:hidden path="parentModuleId"/>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="moduleName">模块名称：</label>
				<div class="controls">
					<form:input data-id="moduleName" path="moduleName" cssClass="inputText" check-type="required checkUniqueKey" required-message="模块名称不能为空!"/>
					<font class="red">*</font>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="moduleUrl">模块名称URL：</label>
				<div class="controls">
					<form:input data-id="moduleUrl" path="moduleUrl" cssClass="inputText" placeholder="留空使用默认URL'#'"/>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="targetString">URL的TATGET：</label>
				<div class="controls">
					<form:input data-id="targetString" path="targetString" cssClass="inputText"/>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="rightCode">权限编码：</label>
				<div class="controls">
					<form:input data-id="rightCode" path="rightCode"  cssClass="inputText" check-type="required" required-message="权限编码不能为空!"/>
					<font class="red">*</font>
				</div>
			</div>
		</div>
		
		<div class="row-modal">
			<div class="control-group-modal">
				<label class="control-label" for="targetString">排序号：</label>
				<div class="controls">
					<form:input data-id="targetString" path="seqNum" check-type="number" cssClass="inputText" placeholder="留空使用默认排序号'0'"/>
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
	</div>
</form:form>