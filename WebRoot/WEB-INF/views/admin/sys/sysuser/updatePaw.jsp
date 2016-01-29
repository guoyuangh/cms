<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="cn._2vin.admin.constant.ConstantAdmin"%>
<%@page import="cn._2vin.admin.bean.SysUser"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<head>
<jsp:include page="/open/common.admininc.jsp" />
</head>
<%
	SysUser sysUer = null;
	Object object = session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
	if (null != object && object instanceof SysUser) {
		sysUer = (SysUser) object;
	} else {
		sysUer = new SysUser();
	}
%>
<body>
<form id="pwUpdateForm" method="post" class="form-horizontal">
<jsp:include page="/open/common.nav.jsp"></jsp:include>
<div class="content">
	 <div class="content-header">
    	<div class="alert alert-info" id="modifyPwdInfo">
    	 	<!-- <button type="button" class="close" data-dismiss="alert">&times;</button> -->
		  	修改用户&nbsp;&nbsp;<strong> <%=sysUer.getLoginName() %> </strong>&nbsp;&nbsp;的密码
		</div>
		<div id="errorTip" class="errorTip red"></div>
	  </div>
	
		<div class="content-center" style="width: 380px;">
			<div class="row">
				<div class="control-group">
					<label class="control-label" for="oldPassword">原密码：</label>
					<div class="controls">
						<input type="password" id="oldPassword" name="oldPassword" class="inputText" check-type="required" required-message="原密码不能为空!" placeholder="原密码"/>
						<font class="red">*</font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group">
					<label class="control-label" for="newPassword">新密码：</label>
					<div class="controls">
						<input type="password" id="newPassword" name="newPassword" class="inputText" maxlength="16" check-type="required"
                         maxLength-message="密码长度不能超过32个字符！" required-message="新密码不能为空!"  placeholder="新密码" />
						<font class="red">*</font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group">
					<label class="control-label" for="checkPassword">确认密码：</label>
					<div class="controls">
						<input type="password"  id="checkPassword" name="checkPassword" class="inputText"
                                    maxLength-message="密码长度不能超过32个字符！" maxlength="32"  placeholder="确认密码" check-type="required checkPassword"  required-message="确认密码不能为空!"/>
						<font class="red">*</font>
					</div>
				</div>
			</div>
		</div>
		<div class="content-footer">
		    <input type="button" class="btn" onclick="checkInput();" value="保存"/>
		    <input type="reset" class="btn" value="重置"/>
	 	</div>
</div>
</form>
	<script type="text/javascript">
	
	function checkInput(){
		var validateResult = $("#pwUpdateForm").validationAjax();
		if(validateResult){
			doConfirm("确认需要修改用户<strong> <%=sysUer.getLoginName() %>  </strong>的密码吗？",function(result){
				if(result){
					savePw();
				}
			});
		}
	}
	function checkPassword(value){
		var flag = false;
		var userPassword = $("#newPassword").val();
		var userConfirm = $("#checkPassword").val();
		if(userPassword!=userConfirm){
			flag = true;				
		}
		return flag;
	}
	/**
	 * 修改当前用户密码
	 * @param moduleId
	 */
	function savePw(){
		var url ="<%=request.getContextPath()%>/admin/sysuser/savePaw.do";
		var params = $("#pwUpdateForm").serialize();
		$("#loading").modal("show");
		$("#errorTip").html("");
		$.post(url,params,function(data){
			debugger
			$("#loading").modal("hide");
			$("#errorTip").html(data.message);
			var flag = data.result;
			if(flag=="fail"){
				$("#oldPassword").focus();
			}
			else if(flag=="success"){
				$("#pwUpdateForm")[0].reset();
			}
		});
	}
	</script>	
</body>
</html>
