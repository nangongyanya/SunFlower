<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>角色管理</title>
<c:import url="/pages/inc/inc_head.jsp" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=2-1-2" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=角色管理&showBack=1" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							系统功能<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 权限管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> ${role.roleName }成员管理 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="simple-table" class="table  table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label class="pos-rel"> 
														<input type="checkbox" class="ace" /> <span class="lbl"></span>
													</label>
												</th>
												<th>ID</th>
												<th>昵称</th>
												<th>用户名</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${adminUserList }" var="info">
												<tr>
													<td class="center">
														<label class="pos-rel"> 
															<input type="checkbox" class="ace" name="ids" value="${info.id }"/> 
															<span class="lbl"></span>
														</label>
													</td>
													<td>${info.id }</td>
													<td>
														${info.nickname }
													</td>
													<td>
														${info.username }
													</td>
													<td>
														<c:if test="${1 eq info.status }"><font color=red>锁定</font></c:if>
														<c:if test="${0 eq info.status }"><font color=green>正常</font></c:if>
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<a href="adminUserToRole_delete.h?delIds=${info.id }&roleId=${role.id }" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</a>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<div>
										<a href="#" class="btn btn-danger btn-delete-batch"> <i class="ace-icon fa fa-trash-o"></i> 批量取消关联 </a>
										<a href="adminUserToRole_form.h?roleId=${role.id }" role="button" class="btn btn-primary" data-toggle="modal">
											<i class="ace-icon fa fa-plus"></i> 添加成员 </a>
										</a>
									</div>
								</div>
								<!-- /.span -->
								
							</div>
							<!-- /.row -->
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
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		<c:if test="${not empty sessionScope.rs}">
            alert('${rs}');
            <c:remove var="rs" scope="session"/>
        </c:if>
        
        $(function(){
		   $('.btn-delete-batch').on('click', function(e) {
				e.preventDefault();
				var ids = '';
				var idsObj = document.getElementsByName('ids');
				for (i = 0; i < idsObj.length; i++) {
					if (idsObj[i].checked == true) {
						ids = idsObj[i].value + ',' + ids;
					}
				}
				ids = ids.substring(0, ids.length - 1);
				if (ids == '') {
					alert('选择后再取消关联!!');
					return false;
				}
				if (confirm('您确定要取消关联吗？')) {
					location.href = '<c:url value="/system/admin/adminUserToRole_delete.h?roleId=${role.id }&delIds="/>' + ids;
					return true;
				}
				return false;
			});
		});
	</script>
</body>
</html>
