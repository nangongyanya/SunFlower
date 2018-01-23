<%@ page import="com.sunflower.back.util.AdminUserSessionUtil"%>
<%@ page import="com.sunflower.back.domain.admin.AdminUser"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	AdminUser adminUser = AdminUserSessionUtil.getAdminSession(session);
	if (adminUser == null) {
		response.sendRedirect("/gu_ess.jsp");
	} else {
		request.setAttribute("loginName", AdminUserSessionUtil.getAdminSession(session).getUsername());
		request.setAttribute("loginPsw", AdminUserSessionUtil.getAdminSession(session).getPsw());
	}
%>
<body onload="loginForm.submit();">
	<form name="loginForm" action="${pageContext.servletContext.contextPath}/system/j_spring_security_check_new_url" method="post">
		<input type="hidden" name="j_username" value="${loginName}" /><input
			type="hidden" name="j_password" value="${loginPsw}" />
	</form>
</body>
