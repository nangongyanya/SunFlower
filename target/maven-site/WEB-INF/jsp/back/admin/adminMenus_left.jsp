<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>功能菜单管理</title>
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="/ace/1.4.0/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="/ace/1.4.0/components/font-awesome/css/font-awesome.css" />
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
	
	<body>
		<center>
			<a href="javascript:;" onclick="location.reload();">
				<i class="ace-icon fa fa-refresh"></i>刷新目录
			</a>
		<hr style="margin-top: 0; margin-bottom: 8px; border: 0; border-top: 1px solid #eeeeee;"/>
		</center>
		
		<script type="text/javascript">
	        function goto(url) {
	            parent.rightFrame.location.href = url;
	        }
	
	        function gotoAction(fid) {
	            goto("/system/admin/adminMenu_list.h?fid=" + fid);
	        }
	
	        function gotoInit() {
	            goto("/system/admin/adminMenu_list.h");
	        }
	        
	        webFXTreeConfig.setImagePath("/static/js/xtree/images/xp/");
		    var tree = new WebFXLoadTree("功能菜单", "/system/admin/adminMenu_tree.h", "javascript:gotoInit()");
		    tree.setBehavior("classic");
		    document.write(tree);
	
	    </script>
	</body>
</html>