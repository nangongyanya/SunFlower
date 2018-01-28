<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>添加角色成员</title>
<c:import url="/pages/inc/inc_head.jsp" />
<!-- page specific plugin styles -->
<link rel="stylesheet" href="/ace/1.4.0/components/bootstrap-duallistbox/dist/bootstrap-duallistbox.css" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=2-1-2" />

		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=角色管理" />

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="page-header">
						<h1>
							系统功能<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 权限管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> ${role.roleName }成员添加 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form" method="post">
								<input type="hidden" name="roleId" value="${role.id }">
								<div class="form-group">
									<div class="col-sm-12">
										<!-- #section:plugins/input.duallist -->
										<select multiple="multiple" size="10" name="duallistbox_demo1[]" id="duallist">
											<c:forEach items="${adminUserList }" var="info">
												<option value="${info.id }">${info.username }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div>
									<center><button type="submit" class="btn btn-success text-center">提交</button></center>
								</div>
							</form>

							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<c:import url="/pages/inc/inc_footer.jsp" />
	</div>
	<!-- /.main-container -->

	<c:import url="/pages/inc/inc_script.jsp" />

	<!-- page specific plugin scripts -->
	<script src="/ace/1.4.0/components/_mod/bootstrap-duallistbox/jquery.bootstrap-duallistbox.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
			jQuery(function($){
			    var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'});
				var container1 = demo1.bootstrapDualListbox('getContainer');
				container1.find('.btn').addClass('btn-white btn-info btn-bold');
			});
		</script>
</body>
</html>
