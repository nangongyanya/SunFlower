<%--
  Created by IntelliJ IDEA.
  User: bob_0703
  Date: 2009-9-29
  Time: 0:44:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/pages/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<c:url value="/skin/skin01/css/front.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/skin/skin01/css/admin.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/skin/skin01/css/theme.css"/>" rel="stylesheet" type="text/css"/>
    <jsp:include page="/pages/inc/tree.js.jsp"/>
</head>
<body>
<div class="body-box">
    <div class="rhead">
        <div class="rpos">当前位置： 系统功能 - 角色管理 - <b>${role.roleName}</b>菜单权限设置</div>
        <div class="ropt" style="padding:3px 0px 3px 20px;">
            <a href="/system/admin/adminRole_index.h">返回</a>
        </div>
        <div class="clear"></div>
    </div>
    <form method="post" id='menu-form' name="menuform">
        <table id="functiontab" width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
            <input type="hidden" id="menuIds" name="menuIds"/>
            <tr>
                <td width="100%" class="pn-flabel pn-flabel-h" style="text-align:left">
                    <script type="text/javascript">
                        webFXTreeConfig.setImagePath("<c:url value="/js/xtree/images/xp/"/>");
                        var tree = new WebFXTree("菜单权限", "");
                        var L21 = new WebFXLoadCheckBoxTreeItem("所有菜单", "", "<c:url value="/system/admin/adminRoleUrl_xml.h?rid=${param.rid}"/>", tree);
                        document.write(tree);
                    </script>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="pn-fbutton">
                    <input type="button" onclick="checkAll();" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
    <script>
        function checkAll() {
            var values = getCheckValues();
            document.getElementById('menuIds').value = values;
            menuform.submit();
        }
    </script>
</div>
</body>
</html>

