package com.sunflower.back.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.common.util.ResponseUtil;

/**
 * 功能菜单树Controller
 * 
 * 类名称：AdminRoleUrlFormByTreeController 创建时间：Feb 5, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminRoleUrlFormByTreeController {

    @Autowired
    private AdminService adminService;
    private String roleId; // 角色id

    /**
     * 获取功能菜单树
     *   
     * @param request
     * @param response
     * @return
     * @throws Exception  
     * @since  1.0.0
     */
    @RequestMapping(value = "/adminRoleUrl_xml")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        this.roleId = request.getParameter("roleId");
        if (StringUtils.isEmpty(this.roleId)) {
            ResponseUtil.string2Response("请选择角色！", response);
            return null;
        }
        ResponseUtil.xml2Response(action(), response);
        return null;
    }

    /**
     * 获取功能菜单树
     *   
     * @param request
     * @param response
     * @return
     * @throws Exception  
     * @since  1.0.0
     */
    public void actionElement(AdminMenus menu, Element root, List<AdminRoleUrl> roleUrllist) {
        if (menu == null) {
            return;
        }
        root.addAttribute("disabled", "false"); //disabled="false" 表示是否可操作
        root.addAttribute("text", menu.getName());
        root.addAttribute("type", "check");
        root.addAttribute("value", menu.getId().toString());
        String checked = "false";
        for (AdminRoleUrl arn : roleUrllist) {
            if (arn.getMenuId().equalsIgnoreCase(menu.getId().toString())) {
                checked = "true";
                roleUrllist.remove(arn);
                break;
            }
        }
        root.addAttribute("checked", checked);
        if (!menu.getAdminMenuss().isEmpty()) {
            for (AdminMenus lmenu : menu.getAdminMenuss()) {
                if (lmenu == null) {
                    continue;
                }
                Element el = root.addElement("tree");
                actionElement(lmenu, el, roleUrllist);
            }
        }
    }

    /**
     * 获取功能菜单树
     *   
     * @param request
     * @param response
     * @return
     * @throws Exception  
     * @since  1.0.0
     */
    public String action() throws Exception {
        List<AdminRoleUrl> roleUrllist = this.adminService.findAdminRoleUrlByRoleId(roleId);
        List<AdminMenus> menusList = this.adminService.findAdminMenusFirst();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("tree");// 创建根节点
        for (AdminMenus menu : menusList) {
            Element el = root.addElement("tree");
            actionElement(menu, el, roleUrllist);
        }
        String xml = document.asXML();

        return xml;
    }

}
