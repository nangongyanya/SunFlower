<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>角色管理</title>
<c:import url="/pages/inc/inc_head.jsp" />
<link rel="stylesheet" href="/static/js/xtree/xtree.css" />
<script src="/static/js/xtree/map_zh_cn.js"></script>
<script src="/static/js/xtree/xtree_zh_cn.js"></script>
<script src="/static/js/xtree/xloadtree_zh_cn.js"></script>
<script src="/static/js/xtree/checkboxTreeItem_zh_cn.js"></script>
<script src="/static/js/xtree/xmlextras_zh_cn.js"></script>
<script src="/static/js/xtree/checkboxXLoadTree_zh_cn.js"></script>
<script src="/static/js/xtree/radioTreeItem_zh_cn.js"></script>
<script src="/static/js/xtree/radioXLoadTree_zh_cn.js"></script>
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
							 权限管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> ${role.roleName }菜单权限管理 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<form method="post" id='menu-form' name="menuform">
										<table id="simple-table" class="table  table-bordered table-hover">
											<input type="hidden" id="menuIds" name="menuIds"/>
											<tbody>
												<tr>
                									<td width="100%" class="pn-flabel pn-flabel-h" style="text-align:left">
														<script type="text/javascript">
									                        webFXTreeConfig.setImagePath("/static/js/xtree/images/xp/");
									                        var tree = new WebFXTree("菜单权限", "");
									                        var L21 = new WebFXLoadCheckBoxTreeItem("所有菜单", "", "/system/admin/adminRoleUrl_xml.h?roleId=${role.id }", tree);
									                        document.write(tree);
									                    </script>
													</td>
												</tr>
									            <tr>
									                <td colspan="1" class="pn-fbutton">
									                    <input type="button" onclick="checkAll();" value="提交"/>
									                </td>
									            </tr>
											</tbody>
										</table>
									</form>
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
		function checkAll() {
            var values = getCheckValues();
            document.getElementById('menuIds').value = values;
            menuform.submit();
        }
	</script>
</body>
</html>
