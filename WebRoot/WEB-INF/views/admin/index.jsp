<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Frameset//EN">
<HTML>
<HEAD>
<TITLE>后台管理中心 V1.0</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Pragma content=no-cache>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Expires content=-1000>
</HEAD>
<FRAMESET border="0" frameSpacing="0" rows="56, *" frameBorder=0>
<FRAME name="header" src="<%=request.getContextPath() %>/admin/header.do" frameBorder=0 noResize scrolling=no>
<FRAMESET cols="170, *">
<FRAME name="menu" src="<%=request.getContextPath() %>/admin/menu.do" frameBorder=0 noResize>
<FRAME name="main" src="<%=request.getContextPath() %>/admin/center.do" frameBorder=0 noResize scrolling=yes>
</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</HTML>
