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
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=角色管理" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							系统功能<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 权限管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 角色管理(<font color=red>超级管理员为系统预设，无法删除。</font>) </small>
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
												<th>角色名称</th>
												<th>角色代码</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${roleList }" var="info">
												<tr>
													<td class="center">
														<label class="pos-rel"> 
															<input type="checkbox" class="ace" name="ids" value="${info.id }"/> 
															<span class="lbl"></span>
														</label>
													</td>
													<td>${info.id }</td>
													<td>
														${info.roleName }
													</td>
													<td>
														${info.roleCode }
													</td>
													<td>
														${info.roleDesc }
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<a href="adminUserToRole_list.h?roleId=${info.id }" class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</a>
															<a href="#" class="btn btn-xs btn-info" onclick="editType(${info.id});">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</a>
															<c:if test="${info.id ne 1 }">
																<a href="adminRole_delete.h?delIds=${info.id }" class="btn btn-xs btn-danger">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</a>
															</c:if>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<div>
										<a href="#" class="btn btn-danger btn-delete-batch"> <i class="ace-icon fa fa-trash-o"></i> 批量删除 </a>
										<a href="#modal-table" role="button" class="btn btn-primary" data-toggle="modal">
											<i class="ace-icon fa fa-plus"></i> 添加角色 </a>
										</a>
									</div>
								</div>
								<!-- /.span -->
								
								<div id="modal-table" class="modal fade" tabindex="-1">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header no-padding">
												<div class="table-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														<span class="white">&times;</span>
													</button>
													<span id="modal-title">添加角色</span>
												</div>
											</div>

											<form action="adminRole_update.h" method="post" class="form-horizontal" role="form">
												<div class="modal-body">
													<div class="alert alert-danger hide"></div>
													<input type="hidden" name="id" id="data-id">
													<div class="form-group">
														<label for="roleName" class="col-sm-2 control-label">
															<font color="red">*</font>角色名称
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																name="roleName" placeholder="请输入角色名称" required>
														</div>
													</div>
													<div class="form-group">
														<label for="roleCode" class="col-sm-2 control-label">
															<font color="red">*</font>角色代码
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																name="roleCode" placeholder="请输入角色代码" required>
														</div>
													</div>
													<div class="form-group">
														<label for="roleDesc" class="col-sm-2 control-label">
															<font color="red">*</font>角色描述
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																name="roleDesc" placeholder="请输入角色描述" required>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
													<button type="submit" class="btn btn-primary btn-submit">提交</button>
												</div>
											</form>
										</div><!-- /.modal-content -->
									</div><!-- /.modal-dialog -->
								</div>
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
					alert('选择后再删除!!');
					return false;
				}
				if (confirm('您确定要删除吗？')) {
					location.href = '<c:url value="/system/admin/adminRole_delete.h?delIds="/>' + ids;
					return true;
				}
				return false;
			});
		});
        
		function editType(id) {
			$.ajax({
            	type: "POST",
                url: "ajax_get_adminRole.h",
                data: {"id" : id},
                dataType:'json',
                success: function(data){
                	$('#modal-title').html("编辑角色信息");
                	$('#modal-table').modal();
                	
                	if(data && data.status){
                		$('#data-id').val(id);
                		$('.modal-body input[name="roleName"]').val(data.items.roleName);
                		$('.modal-body input[name="roleCode"]').val(data.items.roleCode);
                		$('.modal-body input[name="roleDesc"]').val(data.items.roleDesc);
                	}else{
                		$('.alert-danger').html(data.info);
                		$('.alert-danger').removeClass("hide");
                	}
                }
            });
		};
	</script>
</body>
</html>
