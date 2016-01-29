<%@page import="cn._2vin.admin.constant.ConstantAdmin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<TITLE>管理中心登陆 V1.0</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/open/common.inc.jsp"/>
<link href="<%=request.getContextPath()%>/resources/css/admin.css"
	type="text/css" rel="stylesheet"/>
</head>
<body onload="document.loginForm.username.focus();">
	<form name="loginForm" action="<%=request.getContextPath()%>/login.do" method="post">
	<input type="hidden" name="action" value="login" />
		<table height="100%" cellSpacing="0" cellPadding="0" width="100%"
			bgColor="#002779" border="0">
			<tr>
				<td align="center">
					<table cellSpacing="0" cellPadding="0" width="468" border="0">
						<tr>
							<td><img height="23"
								src="<%=request.getContextPath()%>/resources/images/login_1.jpg"
								width="468"></td>
						</tr>
						<tr>
							<td><img height="147"
								src="<%=request.getContextPath()%>/resources/images/login_2.jpg"
								width="468"></td>
						</tr>
					</table>
					<table cellSpacing="0" cellPadding="0" width="468"
						bgColor="#ffffff" border="0">
						<tr>
							<td width="16"><img height="122"
								src="<%=request.getContextPath()%>/resources/images/login_3.jpg"
								width="16"></td>
							<td align="center">
								<table cellSpacing="0" cellPadding="0" width="230" border="0">
									<tr height="20">
										<td colspan="2"></td>
										<td><font class="red">
											<%
												String error = null;
												error = (String)request.getAttribute(ConstantAdmin.LOGIN_ERROR);
												if(null == error){
													error = request.getParameter(ConstantAdmin.LOGIN_ERROR);
												}
												if(null != error){
													if(ConstantAdmin.LOGIN_ERROR_NOT_LOGIN_ERROR.equals(error)){
														out.print("请先登录！");
													}else if(ConstantAdmin.LOGIN_ERROR_VALIDATE_CODE_NULL.equals(error)){
														out.print("验证码不能为空！");
													}else if(ConstantAdmin.LOGIN_ERROR_LOGINNAME_OR_PASSWORD_NULL.equals(error)){
														out.print("用户名或者密码不能为空！");
													}else if(ConstantAdmin.LOGIN_ERROR_VALIDATE_CODE_ERROR.equals(error)){
														out.print("验证码错误！");
													}else if(ConstantAdmin.LOGIN_ERROR_LOGINNAME_OR_PASSWORD_ERROR.equals(error)){
														out.print("用户名或者密码错误！");
													}
												}
											%>
										</font></td>
									</tr>
									<tr height="24">
										<td></td>
										<td>用户名：</td>
										<td><input
											style="float:left; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid; width:160px; height: 20px;"
											type="text" maxLength="30" size="24" value="" name="loginName"></td>
									</tr>
									<tr height="24">
										<td>&nbsp;</td>
										<td>密&nbsp;&nbsp;&nbsp;码：</td>
										<td><input
											style="float:left;  BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid; width:160px; height: 20px;"
											type="password" maxLength="30" size="24" value="" name="loginPassword"></td>
									</tr>
									<tr height="24">
										<td>&nbsp;</td>
										<td>验证码：</td>
										<td>
										<input name="adminValidateCode" id="adminValidateCode" type="text" style=" float:left; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid;width: 50px; height: 20px;" maxlength="4"/>
										<img src="<%=request.getContextPath()%>/adminValidateImage.jpg" align="bottom" id="adminValidateImg" style=" float:left; cursor: pointer; width：50px; height:20px;margin-left: 10px;" title='点击更换验证码'/>
										</td>
									</tr>
									<tr height="5">
										<td colSpan="3"></td>
									</tr>
									<tr height="20">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>
										<input type="submit" class="bt_login not_border mr20" value=""><input type="reset" class="bt_reset not_border" value="">
										</td>
									</tr>
								</table>
							</td>
							<td width="16"><img height="122"
								src="<%=request.getContextPath()%>/resources/images/login_4.jpg"
								width="16"></td>
						</tr>
					</table>
					<table cellSpacing="0" cellPadding="0" width="468" border="0">
						<tr>
							<td><img height="16"
								src="<%=request.getContextPath()%>/resources/images/login_5.jpg"
								width="468"></td>
						</tr>
					</table>
					<table cellSpacing="0" cellPadding="0" width="468" border="0">
						<tr>
							<td align="right"><a href="#" target="_blank"><img
									height="26"
									src="<%=request.getContextPath()%>/resources/images/login_6.gif"
									width="165" border="0"></a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
<script>
	//验证码请求
	var validateImg = "<%=request.getContextPath()%>/adminValidateImage.jpg";
	$(document).ready(function(){
		$("#adminValidateImg").click(function(){
			$("#adminValidateImg").attr("src", validateImg + "?" + Math.random());
		});
	});
</script>
</body>
</HTML>
