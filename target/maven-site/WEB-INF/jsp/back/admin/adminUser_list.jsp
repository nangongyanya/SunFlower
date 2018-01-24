<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>管理员管理</title>
<c:import url="/pages/inc/inc_head.jsp" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=2-1-1" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=管理员管理" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							系统功能<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 权限管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 管理员管理(<font color=red>超级管理员为系统预设，无法删除。</font>) </small>
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
												<th>最后登录时间</th>
												<th>最后登录IP</th>
												<th>登录次数</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${userList }" var="info">
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
														<fmt:formatDate value="${info.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td>
														${info.lastLoginIp }
													</td>
													<td>
														${info.loginCount }
													</td>
													<td>
														<c:if test="${1 eq info.status }"><font color=red>锁定</font></c:if>
														<c:if test="${0 eq info.status }"><font color=green>正常</font></c:if>
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<a href="#" class="btn btn-xs btn-info" onclick="editType(${info.id});">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</a>
															<c:if test="${info.id ne 1 }">
																<a href="adminUser_delete.h?delIds=${info.id }" class="btn btn-xs btn-danger">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</a>
															</c:if>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<div>
										<a href="#modal-table" role="button" class="btn btn-primary" data-toggle="modal">
											<i class="ace-icon fa fa-plus"></i> 添加管理员 </a>
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
													<span id="modal-title">添加管理员</span>
												</div>
											</div>

											<form action="adminUser_update.h" method="post" class="form-horizontal" role="form">
												<div class="modal-body">
													<div class="alert alert-danger hide"></div>
													<input type="hidden" name="id" id="data-id">
													<div class="form-group">
														<label for="nickname" class="col-sm-2 control-label">
															<font color="red">*</font>昵称
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																name="nickname" placeholder="请输入昵称" required>
														</div>
													</div>
													<div class="form-group">
														<label for="username" class="col-sm-2 control-label">
															<font color="red">*</font>用户名
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control"
																name="username" placeholder="请输入用户名" required>
														</div>
													</div>
													<div class="form-group">
														<label for="psw" class="col-sm-2 control-label">
															<font color="red">*</font>密码
														</label>
														<div class="col-sm-10">
															<input type="password" class="form-control"
																name="psw" placeholder="请输入用户名" required>
														</div>
													</div>
													<div class="form-group">
														<label for="status" class="col-sm-2 control-label">
															<font color="red">*</font>状态
														</label>
														<div class="col-sm-10">
															<label class="radio-inline"> 
																<input type="radio" name="status" value="1"> 锁定 
															</label>
															<label class="radio-inline"> 
																<input type="radio" name="status" value="0" checked> 正常 
															</label>
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
        
		function editType(id) {
			$.ajax({
            	type: "POST",
                url: "ajax_get_adminUser.h",
                data: {"id" : id},
                dataType:'json',
                success: function(data){
                	$('#modal-title').html("编辑管理员信息");
                	$('#modal-table').modal();
                	
                	if(data && data.status){
                		$('#data-id').val(id);
                		$('.modal-body input[name="nickname"]').val(data.items.nickname);
                		$('.modal-body input[name="username"]').val(data.items.username);
                		$('.modal-body input[name="psw"]').val(data.items.psw);
                		if (1 == data.items.status) {
				    		$('.modal-body input[name="status"]').eq(1).removeAttr("checked");
				    		$('.modal-body input[name="status"]').eq(0).attr("checked","checked");
				    	}
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
