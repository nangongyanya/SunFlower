<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>系统日志管理</title>
<c:import url="/pages/inc/inc_head.jsp" />
</head>

<body class="no-skin">
	<c:import url="/pages/inc/inc_navbar.jsp" />

	<div class="main-container ace-save-state" id="main-container">
		<c:import url="/pages/inc/inc_sidebar.jsp?menu=2-2-1" />

		<div class="main-content">
			<div class="main-content-inner">
				<c:import url="/pages/inc/inc_breadcrumbs.jsp?menu=系统日志" />

				<div class="page-content">
					<div class="page-header">
						<h1>
							系统功能<small><i class="ace-icon fa fa-angle-double-right"></i></small>
							系统日志 <small> <i class="ace-icon fa fa-angle-double-right"></i> 系统日志 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-search" name="search_form" id="search_form">
								操作类型：
								<select name="optType" onchange="document.search_form.submit();">
									<option value="">请选择</option>
									<option value="save" 
										<c:if test="${'save' eq po.criteria.optType }">selected</c:if>>新增</option>
									<option value="delete" 
										<c:if test="${'delete' eq po.criteria.optType }">selected</c:if>>删除</option>
									<option value="update" 
										<c:if test="${'update' eq po.criteria.optType }">selected</c:if>>更新</option>
									<option value="import" 
										<c:if test="${'import' eq po.criteria.optType }">selected</c:if>>导入</option>										
									<option value="export" 
										<c:if test="${'export' eq po.criteria.optType }">selected</c:if>>导出</option>
									<option value="download" 
										<c:if test="${'download' eq po.criteria.optType }">selected</c:if>>下载</option>
									<option value="login" 
										<c:if test="${'login' eq po.criteria.optType }">selected</c:if>>登陆</option>
									<option value="logout" 
										<c:if test="${'logout' eq po.criteria.optType }">selected</c:if>>登出</option>
									<option value="ERROR" 
										<c:if test="${'ERROR' eq po.criteria.optType }">selected</c:if>>异常</option>
								</select>
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
												<th class="detail-col">详细</th>
												<th>管理员</th>
												<th>操作类型</th>
												<th>操作描述</th>
												<th>操作时间</th>
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
													<td class="center">
														<div class="action-buttons">
															<a href="#" class="green bigger-140 show-details-btn" title="Show Details">
																<i class="ace-icon fa fa-angle-double-down"></i>
																<span class="sr-only">Details</span>
															</a>
														</div>
													</td>
													<td>
														${info.adminUser.nickname }
													</td>
													<td>
														${info.optType }
													</td>
													<td>
														${info.optDesc }
													</td>
													<td>
														<fmt:formatDate value="${info.optDate}" pattern="yyyy-MM-dd HH:mm" />
													</td>
												</tr>
												<tr class="detail-row open" style="display: none;">
													<td colspan="8">
														<div class="table-detail">
															<div class="row">
																<div class="col-xs-12 col-sm-2">
																	<div class="text-center">
																		<img height="150" class="thumbnail inline no-margin-bottom" alt="Domain Owner's Avatar" src="/ace/1.4.0/assets/avatars/profile-pic.jpg" />
																		<br />
																		<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
																			<div class="inline position-relative">
																				<a class="user-title-label" href="#">
																					<i class="ace-icon fa fa-circle light-green"></i>
																					&nbsp;
																					<span class="white">${info.adminUser.nickname }</span>
																				</a>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col-xs-12 col-sm-10">
																	<div class="space visible-xs"></div>
																	<div class="profile-user-info profile-user-info-striped">
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 管理员主键值 </div>
																			<div class="profile-info-value">
																				<span>${info.adminUser.id }</span>
																			</div>
																		</div>
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 管理员用户名 </div>
																			<div class="profile-info-value">
																				<span>${info.adminUser.username }</span>
																			</div>
																		</div>
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 管理员昵称 </div>
																			<div class="profile-info-value">
																				<span>${info.adminUser.nickname }</span>
																			</div>
																		</div>
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 操作类 </div>
																			<div class="profile-info-value">
																				<span>${info.classMethod }</span>
																			</div>
																		</div>
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 操作ip </div>
																			<div class="profile-info-value">
																				<span>${info.optIp }</span>
																			</div>
																		</div>
																		<div class="profile-info-row">
																			<div class="profile-info-name"> 备注 </div>
																			<div class="profile-info-value">
																				<span>${info.remake }</span>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
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
														<pg:param name="optType" value="${po.criteria.optType}" />
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
		$(function(){
			$('.show-details-btn').on('click', function(e) {
				e.preventDefault();
				$(this).closest('tr').next().toggle();
				//$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
			});
		});
	</script>
</body>
</html>
