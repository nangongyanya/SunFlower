<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>常见问答管理</title>
<c:import url="/pages/inc/inc_head.jsp" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=1-2-1" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=常见问答" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							基础设置<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							 常见问答管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 常见问答 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-search" name="search_form" id="search_form">
								所属类别：
								<select name="faqType" onchange="document.search_form.submit();">
									<option value="">请选择</option>
									<c:forEach items="${typePo.results }" var="info">
										<option value="${info.id }" <c:if test="${info.id eq po.criteria.faqType }">selected</c:if>>${info.name }</option>
									</c:forEach>
								</select>
								&nbsp;问答标题：
								<input name="faqQuestion" value="${po.criteria.faqQuestion }" type="text" class="search-query" placeholder="请输入问答标题关键字..." />
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
												<th>问题</th>
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
														${info.faqQuestion }
													</td>
													<td>
														${info.mcCommonData.name }
													</td>
													<td>
														<fmt:formatDate value="${info.dateAdded}" pattern="yyyy-MM-dd HH:mm" />
													</td>
													<td>
														<fmt:formatDate value="${info.lastModified}" pattern="yyyy-MM-dd HH:mm" />
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<a href="mcCommonFaq_preview.h?id=${info.id }" class="btn btn-xs btn-warning" target="_blank">
																<i class="ace-icon fa fa-eye bigger-120"></i>
															</a>
															<a href="#" class="btn btn-xs btn-info" onclick="editFaq(${info.id});">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</a>
															<a href="mcCommonFaq_delete.h?delIds=${info.id }" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</a>
														</div>
													</td>
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
												条数据
											</div>
										</div>
										<div class="col-xs-6">
											<div class="dataTables_paginate paging_simple_numbers">
												<ul class="pagination">
													<pg:pager url="${url}" export="offset,currentPageNumber=pageNumber"
														index="center" items="${po.total}" maxPageItems="${po.criteria.maximumResultSize}">
														<pg:param name="pager.items" value="${po.criteria.maximumResultSize}" />
														<pg:param name="faqQuestion" value="${po.criteria.faqQuestion}" />
														<pg:param name="faqType" value="${po.criteria.faqType}" />
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
										<c:if test="${not empty po.criteria.faqType }">
											<a href="#modal-table" role="button" class="btn btn-primary" data-toggle="modal">
												<i class="ace-icon fa fa-plus"></i> 添加数据 </a>
											</a>
										</c:if>
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
													<span id="modal-title">添加常见问答</span>
												</div>
											</div>

											<form id="faqForm" action="mcCommonFaq_update.h" method="post" class="form-horizontal" role="form">
												<div class="modal-body">
													<div class="alert alert-danger hide"></div>
													<input type="hidden" name="id" id="data-id">
													<input type="hidden" name="faqAnswer" id="faqAnswer">
													<div class="form-group">
														<label for="faqQuestion" class="col-sm-2 control-label">
															<font color="red">*</font>问题
														</label>
														<div class="col-sm-10">
															<textarea class="form-control" name="faqQuestion" placeholder="请输入问题" required></textarea>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">
															<font color="red">*</font>回答
														</label>
														<div class="col-sm-10">
															<div class="widget-box widget-color-green">
																<div class="widget-header widget-header-small"></div>
					
																<div class="widget-body">
																	<div class="widget-main no-padding">
																		<div class="wysiwyg-editor" id="editor2"></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label for="status" class="col-sm-2 control-label">
															<font color="red">*</font>所属类别
														</label>
														<div class="col-sm-10">
															<select name="faqType" required>
																<option value="">请选择</option>
																<c:forEach items="${typePo.results }" var="info">
																	<option value="${info.id }" <c:if test="${info.id eq po.criteria.faqType }">selected</c:if>>${info.name }</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary btn-submit" onclick="submitForm();">提交</button>
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
			/** wysiwyg获取内容使用$('#editor2').html() ,要清除内容则使用$('#editor2').cleanHtml()； */
			$('#editor2').css({'height':'200px'}).ace_wysiwyg({
				toolbar_place: function(toolbar) {
					return $(this).closest('.widget-box')
					       .find('.widget-header').prepend(toolbar)
						   .find('.wysiwyg-toolbar').addClass('inline');
				},
				toolbar:
				[
					'bold',
					{name:'italic' , title:'Change Title!', icon: 'ace-icon fa fa-leaf'},
					'strikethrough',
					null,
					'insertunorderedlist',
					'insertorderedlist',
					null,
					'justifyleft',
					'justifycenter',
					'justifyright'
				],
				speech_button: false
			});
			
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
					location.href = '<c:url value="/system/cms/mcCommonFaq_delete.h?delIds="/>' + ids;
					return true;
				}
				return false;
			});
		});
		
		function editFaq(id) {
			$.ajax({
            	type: "POST",
                url: "ajax_get_McCommonFaq.h",
                data: {"id" : id},
                dataType:'json',
                success: function(data){
                	$('#modal-title').html("编辑常见问答");
                	$('#modal-table').modal();
                	
                	if(data && data.status){
                		$('#data-id').val(id);
                		$('.modal-body textarea[name="faqQuestion"]').val(data.items.faqQuestion);
                		$('#editor2').html(data.items.faqAnswer);
                		$('.modal-body input[name="faqType"]').val(data.items.faqType);
                	}else{
                		$('.alert-danger').html(data.info);
                		$('.alert-danger').removeClass("hide");
                	}
                }
            });
		};
		
		function submitForm() {
			$("#faqAnswer").val($('#editor2').html());
			$("#faqForm").submit();
		};
		
	</script>
</body>
</html>
