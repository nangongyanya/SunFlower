<%@ page import="java.util.Enumeration"%>
<%@ page import="com.sunflower.back.util.AdminUserSessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Enumeration _enum = request.getSession().getAttributeNames();
    while (_enum.hasMoreElements()) {
        String key = (String) _enum.nextElement();
        request.getSession().removeAttribute(key);
    }
    request.getSession().invalidate();
    response.addCookie(AdminUserSessionUtil.removeCookie());
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>登录超时</title>
		<meta http-equiv="refresh" content="5;url=/gu_ess.jsp">
		<c:import url="/pages/inc/inc_head.jsp" />
	</head>

	<body class="no-skin">

		<!-- /section:basics/navbar.layout -->
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content">
						<div class="page-header">
							<h1>登录超时，5秒后系统自动跳转到登录页面。</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="alert alert-info">
									<button class="close" data-dismiss="alert"><i class="ace-icon fa fa-times"></i></button>
									 如果页面没有响应，请点<a href="/gu_ess.jsp"><font color=red>这里刷新</font></a>!
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

		</div><!-- /.main-container -->

	</body>
</html>
