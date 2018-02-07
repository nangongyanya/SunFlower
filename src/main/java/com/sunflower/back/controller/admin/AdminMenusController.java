package com.sunflower.back.controller.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.util.SystemLogUtil;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.vo.JsonDetail;
import com.sunflower.security.AdminUserInvocationSecurityMetadataSource;

/**
 * 功能菜单Controller
 * 
 * 类名称：AdminMenusController 创建时间：Jan 29, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminMenusController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	/**
	 * 功能菜单主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adminMenu_index")
    public String adminMenusIndex() {
        return "admin/adminMenus_index";
    }

	/**
	 * 功能菜单左边页面
	 * 
	 * @return
	 */
    @RequestMapping(value = "/adminMenus_left")
    public String adminMenusLeft() {
        return "admin/adminMenus_left";
    }

    /**
     * 功能菜单列表
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/adminMenu_list")
    public String adminMenuList(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fid = request.getParameter("fid");
        Collection<AdminMenus> adminMenusList = null;
        if (StringUtils.isEmpty(fid)) {
            adminMenusList = this.adminService.findAdminMenusFirst();
        } else {
            AdminMenus menu = this.adminService.findAdminMenus(Integer.parseInt(fid));
            if (menu != null) {
                adminMenusList = menu.getAdminMenuss();
            }
        }
        model.put("adminMenusList", adminMenusList);
        model.put("fid", fid);
    	return "admin/adminMenu_list";
    }

	/**
	 * 删除栏目
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminMenu_delete")
	public String adminMenuDelete(HttpServletRequest request) throws Exception {
		String delIds = request.getParameter("delIds");
        String fid = "";
        if (!org.apache.commons.lang.StringUtils.isEmpty(delIds)) {
            String[] ids = delIds.split(",");
            for (String id : ids) {
                AdminMenus adminMenu = this.adminService.findAdminMenus(Integer.parseInt(id));
                if (adminMenu != null && adminMenu.getAdminMenus() != null) {
                    fid = adminMenu.getAdminMenus().getId().toString();
                }
                if (adminMenu != null) {
                    if (adminMenu.getAdminMenuss() != null && !adminMenu.getAdminMenuss().isEmpty()) {
                        request.getSession().setAttribute("rs", adminMenu.getName() + " 目前拥有子栏目,不能删除,请删除所有子栏目后再删除!!");
                        return "redirect:adminMenu_list.h?fid=" + fid;
                    }
                }
            }
            this.adminService.removeAdminMenus(ids);
            SystemLogUtil.log(adminService, this, SystemLogUtil.DELETE, "删除菜单权限:".concat(delIds), request);
            
            // 此处更新spring security 的权限缓存
            AdminUserInvocationSecurityMetadataSource auisms = new AdminUserInvocationSecurityMetadataSource();
            auisms.setAdminService(adminService);
            auisms.loadResourceDefine();
        }
        if (StringUtils.isBlank(fid)) {
        	return "redirect:adminMenu_list.h";
        } else {
        	return "redirect:adminMenu_list.h?fid=" + fid;
        }
	}

	/**
	 * 异步获取栏目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax_get_adminMenu", method = RequestMethod.POST)
	public String ajaxGetAdminMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			AdminMenus adminMenu = this.adminService.findAdminMenus(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(adminMenu);
		} catch (Exception e) {
			log.error("异步获取管理员出错 --> ", e);
			e.printStackTrace();
			json.setStatus(false);
			json.setInfo("数据出错");
		}
		return this.json2Response(json, response);
	}
	
	/**
	 * 保存/更新栏目
	 * 
	 * @param admin
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminMenu_update", method = RequestMethod.POST)
	public String adminMenuUpdate(ModelMap model, HttpServletRequest request) {
		String fid = request.getParameter("fid");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");
		String url = request.getParameter("url");
		String description = request.getParameter("description");

		AdminMenus adminMenu = new AdminMenus();
		adminMenu.setName(name);
		adminMenu.setSort(Integer.parseInt(sort));
		adminMenu.setUrl(url);
		adminMenu.setDescription(description);
		if (!StringUtils.isEmpty(fid)) {
			adminMenu.setParentId(Integer.parseInt(fid));
        }
       
		// 保存
		if (StringUtils.isEmpty(id)) {
            this.adminService.saveAdminMenus(adminMenu);
            SystemLogUtil.log(adminService, this, SystemLogUtil.SAVE, "添加菜单权限:".concat(adminMenu.getName()), request);
        } 
		// 更新
		else {
			adminMenu.setId(Integer.parseInt(id));
			this.adminService.updateAdminMenus(adminMenu);
			SystemLogUtil.log(adminService, this, SystemLogUtil.UPDATE, "修改菜单权限:".concat(adminMenu.getName()), request);
		}

		// 此处更新spring security 的权限缓存
        AdminUserInvocationSecurityMetadataSource auisms = new AdminUserInvocationSecurityMetadataSource();
        auisms.setAdminService(adminService);
        auisms.loadResourceDefine();
        request.getSession().removeAttribute("adminMenu");
        if (StringUtils.isBlank(fid)) {
        	return "redirect:adminMenu_list.h";
        } else {
        	return "redirect:adminMenu_list.h?fid=" + fid;
        }
	}
	
}
