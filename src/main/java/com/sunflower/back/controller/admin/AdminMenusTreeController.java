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
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.common.util.ResponseUtil;

/**
 * 功能菜单树Controller
 * 
 * 类名称：AdminMenusTreeController 创建时间：Jan 29, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminMenusTreeController {
	@Autowired
	private AdminService adminService;

	/**
	 * 功能菜单树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminMenu_tree")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		// 有点击事件
		String xml = "";
		if (!StringUtils.isEmpty(action)) {
			xml = action();
		}
		// 没有击事件
		xml = noAction();
		ResponseUtil.xml2Response(xml, response);
		return null;
	}

	public void actionElement(AdminMenus adminMenus, Element root) {
		if (adminMenus == null) {
			return;
		}
		if (!adminMenus.getAdminMenuss().isEmpty()) {
			root.addAttribute("text", adminMenus.getName());
			root.addAttribute("action", "javascript:gotoAction('".concat(adminMenus.getId().toString()).concat("')"));
			for (AdminMenus lAdminMenus : adminMenus.getAdminMenuss()) {
				if (lAdminMenus == null) {
					continue;
				}
				Element el = root.addElement("tree");
				actionElement(lAdminMenus, el);
			}
		} else {
			root.addAttribute("text", adminMenus.getName());
			root.addAttribute("action", "javascript:gotoAction('".concat(adminMenus.getId().toString()).concat("')"));
		}
	}

	public String action() throws Exception {
		List<AdminMenus> fAdminMenus = this.adminService.findAdminMenusFirst();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("tree");// 创建根节点
		for (AdminMenus AdminMenus : fAdminMenus) {
			Element el = root.addElement("tree");
			actionElement(AdminMenus, el);
		}
		String xml = document.asXML();

		return xml;
	}

	public void noActionElement(AdminMenus adminMenus, Element root) {
		if (adminMenus == null) {
			return;
		}
		if (adminMenus.getAdminMenuss() != null
				&& !adminMenus.getAdminMenuss().isEmpty()) {
			root.addAttribute("text", adminMenus.getName());
			root.addAttribute("action", "javascript:gotoAction('".concat(adminMenus.getId().toString()).concat("')"));
			for (AdminMenus lAdminMenus : adminMenus.getAdminMenuss()) {
				if (lAdminMenus == null) {
					continue;
				}
				Element el = root.addElement("tree");
				noActionElement(lAdminMenus, el);
			}
		} else {
			root.addAttribute("text", adminMenus.getName());
			root.addAttribute("action", "javascript:gotoAction('".concat(adminMenus.getId().toString()).concat("')"));
		}
	}

	public String noAction() throws Exception {
		List<AdminMenus> fAdminMenus = this.adminService.findAdminMenusFirst();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("tree");// 创建根节点
		for (AdminMenus AdminMenus : fAdminMenus) {
			Element el = root.addElement("tree");
			noActionElement(AdminMenus, el);
		}
		String xml = document.asXML();

		return xml;
	}

}
