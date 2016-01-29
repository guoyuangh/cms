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
</HEAD>

<BODY>
	<TABLE cellSpacing=0 cellPadding=0 width="100%"
		background="<%=request.getContextPath()%>/resources/images/header_bg.jpg"
		border=0>
		<TR height=56>
			<TD width=260><a
				href="<%=request.getContextPath()%>/admin/index.do" target=_top><IMG
					height=56
					src="<%=request.getContextPath()%>/resources/images/header_left.jpg"
					width=260></a></TD>
			<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
				align="center">当前用户：<%=(null != sysUer.getSysRole()?"["+sysUer.getSysRole().getRoleName()+"]":"")%><%=sysUer.getLoginName()%> &nbsp;&nbsp; <A
				style="COLOR: #fff" href="<%=request.getContextPath()%>/admin/sysuser/initPaw.do" target="main">修改密码</A> &nbsp;&nbsp; <A
				style="COLOR: #fff"
				onclick="if (confirm('确定要退出吗？')) return true; else return false;"
				href="<%=request.getContextPath()%>/logout.do" target=_top>退出系统</A>
			</TD>
			<TD align=right width=268><IMG height=56
				src="<%=request.getContextPath()%>/resources/images/header_right.png"
				width=268></TD>
		</TR>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TR bgColor="#1c5db6" height="4">
			<TD></TD>
		</TR>
	</TABLE>
</BODY>
</HTML>
