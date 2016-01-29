<%@page import="cn._2vin.admin.bean.SysModule"%>
<%@page import="java.util.List"%>
<%@page import="cn._2vin.common.util.DateHelper"%>
<%@page import="com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection"%>
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
<SCRIPT language=javascript>
	function expand(el) {
		childObj = document.getElementById("child" + el);
		if(null != childObj ){
			if (childObj.style.display == 'none') {
				childObj.style.display = 'block';
				document.getElementById("backgroundParent" + el).className="parent_show";
			} else {
				childObj.style.display = 'none';
				document.getElementById("backgroundParent" + el).className="parent_default";
			}
		}else{
			document.getElementById("backgroundParent" + el).className="parent_show";
		}
		return;
	}
	
	function setMenuChildClass(thiss){
		var thisId = $(thiss).attr("id"); 
		$(".menuChildTd a").each(function(index){
			var cid = $(this).attr("id");
			if(thisId == cid){
				$(this).attr("class","menuChild setMenuChildClass");
			}else{
				$(this).attr("class","menuChild");
			}
		});
	}
</SCRIPT>
<style type="text/css">
.parent_default{ background-image: url("<%=request.getContextPath()%>/resources/images/menu_bt.jpg");}
.parent_show{background-image: url("<%=request.getContextPath()%>/resources/images/menu_bt_show.jpg");}
.menuParent{float: left; width: 120px;height: 22px;PADDING-LEFT: 30px ;}
.menuChild{line-height: 18px;}
.setMenuChildClass{color: blue !important;}
</style>
</HEAD>
<BODY>
	<TABLE height="100%" cellSpacing=0 cellPadding=0 width=170
		background="<%=request.getContextPath()%>/resources/images/menu_bg.jpg"
		border=0>
		<TR>
			<TD vAlign=top align="center">
				<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
					<TR>
						<TD height=10></TD>
					</TR>
				</TABLE>
				<%
				List<SysModule> list = sysUer.getSysModules();
				if(null != list){
					for(SysModule parent : list){
						List<SysModule> children = parent.getChildren();
						boolean hasChildren = (null != children && children.size() > 0);
						String pUrl = (ConstantAdmin.DEFAULT_MODULE_URL.equals(parent.getModuleUrl())?parent.getModuleUrl():(request.getContextPath()+ ((0 == parent.getModuleUrl().indexOf("/"))?"":"/") +parent.getModuleUrl()+"?navTitle="+parent.getModuleName()));
				%>
				<TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
					<TR height=22>
						<TD id="backgroundParent<%=parent.getModuleId()%>" class="<%=hasChildren?"parent_default":"parent_show" %>"><A
							class="menuParent" onclick="expand(<%=parent.getModuleId() %>)" href="<%=pUrl%>" target="<%=parent.getTargetString()%>"><%=parent.getModuleName() %></A></TD>
					</TR>
					<TR height=4>
						<TD></TD>
					</TR>
				</TABLE>
				<%
						if(hasChildren){
				%>
				<TABLE id="child<%=parent.getModuleId()%>" style="DISPLAY: none" cellSpacing=0 cellPadding=0
					width=150 border=0>
					<%
							for(SysModule child : children){
								String cUrl = (ConstantAdmin.DEFAULT_MODULE_URL.equals(child.getModuleUrl())?child.getModuleUrl():(request.getContextPath()+ ((0 == child.getModuleUrl().indexOf("/"))?"":"/") +child.getModuleUrl()+"?navTitle="+child.getModuleName()));
					%>
					<TR height=20>
						<TD align="center" width=30><IMG height=9
							src="<%=request.getContextPath()%>/resources/images/menu_icon.gif"
							width=9></TD>
						<TD class="menuChildTd"><A id="menuChild<%=child.getModuleId()%>" class="menuChild" onclick="setMenuChildClass(this)" href="<%=cUrl %>" target="<%=child.getTargetString()%>"><%=child.getModuleName() %></A></TD>
					</TR>
				<%
							}	
				%>
				</TABLE>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
					<TR>
						<TD height=5></TD>
					</TR>
				</TABLE>
				<% 	
						}
					}
				} 
				%>
			</TD>
			<TD width="1" bgColor="#d1e6f7"></TD>
		</TR>
	</TABLE>
</BODY>
</HTML>
