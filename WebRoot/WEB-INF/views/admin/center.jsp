<%@page import="cn._2vin.admin.bean.SysUser"%>
<%@page import="cn._2vin.admin.constant.ConstantAdmin"%>
<%
	SysUser sysUer = null;
	Object object = session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
	if (null != object && object instanceof SysUser) {
		sysUer = (SysUser) object;
	} else {
		sysUer = new SysUser();
	}
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<jsp:include page="/open/common.admininc.jsp" />
<SCRIPT>
var serverdatetime = parseInt('${serverDateTime}');
function showtime(){
	today=new Date(serverdatetime);
	/*加1秒*/
	serverdatetime+=1000;
	var year=1900+today.getYear();
	var month=today.getMonth()+1;
	var day=today.getDate();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	var timeValue= hours;//((hours >12) ? hours -12 :hours);
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes+"";
	//timeValue += (hours >= 12) ? "PM" : "AM";
	timeValue+=((seconds < 10) ? ":0" : ":") + seconds+"";
	var timetext=year+"-"+(month < 10 ? "0" : "")+month+"-"+(day < 10 ? "0" : "")+day+" "+timeValue;
	document.getElementById("liveclock").innerText = timetext; //div的html是now这个字符串
	setTimeout("showtime()",1000); //设置过1000毫秒就是1秒，调用showtime方法
}
</SCRIPT>
<script language="javascript">
window.onload=function(){
	showtime();
};
</script>
</HEAD>
<BODY>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
		<TR height=28>
			<TD
				background="<%=request.getContextPath()%>/resources/images/title_bg1.jpg">&nbsp;&nbsp;当前位置:管理员中心</TD>
		</TR>
		<TR>
			<TD bgColor=#b1ceef height=1></TD>
		</TR>
		<TR height=20>
			<TD
				background="<%=request.getContextPath()%>/resources/images/shadow_bg.jpg"></TD>
		</TR>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
		<TR height=100>
			<TD align="center" width=100><IMG height=100
				src="<%=request.getContextPath()%>/resources/images/admin_p.gif"
				width=90></TD>
			<TD width=60>&nbsp;</TD>
			<TD>
				<TABLE height=100 cellSpacing=0 cellPadding=0 width="100%" border=0>

					<TR>
						<TD>当前时间：<span id="liveclock"></span></TD>
					</TR>
					<TR>
						<TD style="FONT-WEIGHT: bold; FONT-SIZE: 16px"><%=(null != sysUer.getSysRole()?"["+sysUer.getSysRole().getRoleName()+"]":"")%><%=sysUer.getLoginName()%></TD>
					</TR>
					<TR>
						<TD>欢迎进入网站管理中心！</TD>
					</TR>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD colSpan=3 height=10></TD>
		</TR>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
		<TR height=20>
			<TD></TD>
		</TR>
		<TR height=22>
			<TD style="PADDING-LEFT: 20px; FONT-WEIGHT: bold; COLOR: #ffffff"
				align="center"
				background="<%=request.getContextPath()%>/resources/images//title_bg2.jpg">您的相关信息</TD>
		</TR>
		<TR bgColor=#ecf4fc height=12>
			<TD></TD>
		</TR>
		<TR height=20>
			<TD></TD>
		</TR>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=2 width="95%" align=center border=0>
		<TR>
			<TD align=right width="30%">登陆帐号：</TD>
			<TD style="COLOR: #880000" width="70%"><%=sysUer.getLoginName()%></TD>
		</TR>
		<TR>
			<TD align=right>真实姓名：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getRealName()%></TD>
		</TR>
		<TR>
			<TD align=right>注册时间：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getCrtDate()%></TD>
		</TR>
		<TR>
			<TD align=right>最近修改时间：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getArcDate()%></TD>
		</TR>
		<TR>
			<TD align=right>登陆次数：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getLoginCount()+1%></TD>
		</TR>
		<TR>
			<TD align=right>最后一次登录时间：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getLastLoginDate()%></TD>
		</TR>
		<TR>
			<TD align=right>最后一次登录IP地址：</TD>
			<TD style="COLOR: #880000"><%=sysUer.getLastLoginIp()%></TD>
		</TR>
		<TR>
			<TD align=right>身份过期：</TD>
			<TD style="COLOR: #880000"><%=session.getMaxInactiveInterval()/60l%>分钟</TD>
		</TR>
	</TABLE>
</BODY>
</HTML>
