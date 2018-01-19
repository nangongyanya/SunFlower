<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>基础数据类型管理</title>
<meta name="description" content="基础数据类型管理" />
<c:import url="/pages/inc/inc_head.jsp" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=1-1-2" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=基础数据" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							设置<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 基础数据管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 基础数据 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-search" name="search_form" id="search_form">
								父类别：
								<select name="type" onchange="document.search_form.submit();">
									<option value="">请选择</option>
									<c:forEach items="${typeList }" var="info">
										<option value="${info.id }" <c:if test="${info.id eq po.criteria.type }">selected</c:if>>${info.name }</option>
									</c:forEach>
								</select>
								&nbsp;数据名称：
								<input name="name" value="${po.criteria.name }" type="text" class="search-query" placeholder="请输入数据名称关键字..." />
							</form>
							<div class="hr hr-18 hr-double dotted"></div>
								
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
												<th>数据名称</th>
												<th>状态</th>
												<th>所属类别</th>
												<th>添加时间</th>
												<th>修改时间</th>
												<th>操作</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${po.results }" var="info">
												<tr>
													<td class="center">
														<label class="pos-rel"> 
															<input type="checkbox" class="ace" name="ids" value="${info.id }"/> 
															<span class="lbl"></span>
														</label>
													</td>
													<td>${info.id }</td>
													<td>
														<a href="/system/mcCommonData.h?type=${info.id }">${info.name }</a>
													</td>
													<td>
														<c:if test="${1 eq info.status }">有效</c:if>
														<c:if test="${0 eq info.status }">无效</c:if>
													</td>
													<td>
														${info.mcCommonDataType.name }
													</td>
													<td>
														<fmt:formatDate value="${info.dateAdded}" pattern="yyyy-MM-dd HH:mm" />
													</td>
													<td>
														<fmt:formatDate value="${info.lastModified}" pattern="yyyy-MM-dd HH:mm" />
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<a href="#" class="btn btn-xs btn-info" onclick="editType(${info.id});">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</a>
															<a href="mcCommonData_delete.h?delIds=${info.id }" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</a>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">
										<div class="col-xs-6">
											<div class="dataTables_info">
												在<font color=red> ${po.total} </font>个条目中显示
												<c:choose>
													<c:when test="${po.total gt po.criteria.firstResult + po.criteria.maximumResultSize}">
														${po.criteria.firstResult + 1 } 到 ${po.criteria.firstResult + po.criteria.maximumResultSize }
													</c:when>
													<c:otherwise>
														${po.criteria.firstResult + 1 } 到 ${po.total }
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="col-xs-6">
											<div class="dataTables_paginate paging_simple_numbers">
												<ul class="pagination">
													<pg:pager url="${url}" export="offset,currentPageNumber=pageNumber"
														index="center" items="${po.total}" maxPageItems="${po.criteria.maximumResultSize}">
														<pg:param name="pager.items" value="${po.criteria.maximumResultSize}" />
														<pg:param name="name" value="${po.criteria.name}" />
														<pg:param name="type" value="${po.criteria.type}" />
														<pg:first>
															<li class="paginate_button">
																<a href="${pageUrl}">首页</a>
															</li>
														</pg:first>
														<pg:prev>
															<li class="paginate_button">
																<a href="${pageUrl}">上一页</a>
															</li>
														</pg:prev>
														<pg:pages>
															<c:choose>
																<c:when test="${currentPageNumber eq pageNumber}">
																	<li class="paginate_button active">
																		<a href="#">${pageNumber}</a>
																	</li>
																</c:when>
																<c:otherwise>
																	<li class="paginate_button">
																		<a href="${pageUrl}">${pageNumber}</a>
																	</li>
																</c:otherwise>
															</c:choose>
														</pg:pages>
														<pg:next>
															<li class="paginate_button" tabindex="0">
																<a href="${pageUrl}">下一页</a>
															</li>
														</pg:next>
														<pg:last>
															<li class="paginate_button" tabindex="0">
																<a href="${pageUrl}">尾页</a>
															</li>
														</pg:last>
													</pg:pager>
												</ul>
											</div>
										</div>
									</div>
									
									<div>
										<a href="#" class="btn btn-danger btn-delete-batch"> <i class="ace-icon fa fa-trash-o"></i> 批量删除 </a> 
										<a href="#modal-table" role="button" class="btn btn-primary" data-toggle="modal">
											<i class="ace-icon fa fa-plus"></i> 添加类型 </a>
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
													<span id="modal-title">添加数据类型</span>
												</div>
											</div>

											<form action="mcCommonDataType_update.h" method="post" class="form-horizontal" role="form">
												<div class="modal-body">
													<div class="alert alert-danger hide"></div>
													<input type="hidden" name="id" id="data-id">
													<div class="form-group">
														<label for="name" class="col-sm-2 control-label">
															<font color="red">*</font>类别名称
														</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="typeName"
																name="name" placeholder="请输入类别名称" required>
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
					location.href = '<c:url value="/system/mcCommonData_delete.h?delIds="/>' + ids;
					return true;
				}
				return false;
			});
		});
		
		function editType(id) {
			$.ajax({
            	type: "POST",
                url: "ajax_get_mcCommonDataType.h",
                data: {"id" : id},
                dataType:'json',
                success: function(data){
                	$('#modal-title').html("编辑数据类型");
                	$('#modal-table').modal();
                	
                	if(data && data.status){
                		$('#data-id').val(id);
                		$('input[name="name"]').val(data.items.name);
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
