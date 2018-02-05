package com.sunflower.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.domain.admin.AdminUserToRole;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.constants.Constants;
import com.sunflower.common.vo.JsonDetail;
import com.sunflower.security.AdminUserInvocationSecurityMetadataSource;

/**
 * 管理员角色Controller
 * 
 * 类名称：AdminRoleController 创建时间：Jan 29, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminRoleController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	/**
	 * 角色列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminRole_list")
	public String adminRoleList(ModelMap model, HttpServletRequest request)
			throws Exception {
		List<AdminRole> roleList = this.adminService.findAdminRoleAll();
        model.addAttribute("roleList", roleList);
		return "admin/adminRole_list";
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminRole_delete")
	public String adminRoleDelete(HttpServletRequest request) throws Exception {
		String delIds = request.getParameter("delIds");
		if (!StringUtils.isEmpty(delIds)) {
			String[] ids = delIds.split(",");
			for (String id : ids) {
				if (id.equalsIgnoreCase(Constants.SYSTEM_ROLE.toString())) {
        			continue;
        		}
				
				List<AdminUser> list = this.adminService.getListAdminUserByRoleId(Integer.parseInt(id));
        		if (list != null && !list.isEmpty()) {
        			AdminRole adminRole = this.adminService.findAdminRoleById(Integer.parseInt(id));
        			request.getSession().setAttribute("rs", adminRole.getRoleName() + "下存在管理员，请取消关联后再删除!");
        			return "redirect:adminRole_list.h";
        		}
        		
        		this.adminService.deleteAdminRole(Integer.parseInt(id));
			}
		}
		
		// 更新spring security 的权限缓存
        AdminUserInvocationSecurityMetadataSource auisms = new AdminUserInvocationSecurityMetadataSource();
        auisms.setAdminService(adminService);
        auisms.loadResourceDefine();
		return "redirect:adminRole_list.h";
	}

	/**
	 * 异步获取角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax_get_adminRole", method = RequestMethod.POST)
	public String ajaxGetAdminRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			AdminRole adminRole = this.adminService.findAdminRoleById(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(adminRole);
		} catch (Exception e) {
			log.error("异步获取管理员出错 --> ", e);
			e.printStackTrace();
			json.setStatus(false);
			json.setInfo("数据出错");
		}
		return this.json2Response(json, response);
	}
	
	/**
	 * 保存管理员
	 * 
	 * @param admin
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminRole_update", method = RequestMethod.POST)
	public String adminRoleUpdate(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		String roleCode = request.getParameter("roleCode");
		String roleDesc = request.getParameter("roleDesc");

		// 判断代码是否已经存在
		AdminRole adminRole = this.adminService.findAdminRoleByCode(roleCode);
		if (adminRole != null && !adminRole.getId().toString().equals(id)) {
			request.getSession().setAttribute("rs", "角色代码重复，请重新添加！");
			return "redirect:adminRole_list.h";
		}
		
		if (adminRole == null && !StringUtils.isEmpty(id)) {
			adminRole = this.adminService.findAdminRoleById(Integer.parseInt(id));
		}
			
		if (adminRole == null) {
			adminRole = new AdminRole();
		}

		adminRole.setRoleName(roleName);
		adminRole.setRoleCode(roleCode);
		adminRole.setRoleDesc(roleDesc);

		// 更新
		if (adminRole.getId() != null) {
			this.adminService.updateAdminRole(adminRole);
		}
		// 保存
		else {
			this.adminService.saveAdminRole(adminRole);
		}
		return "redirect:adminRole_list.h";
	}
	
	/**
	 * 角色下的管理员列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminUserToRole_list")
	public String adminUserToRoleList(ModelMap model,
			HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isBlank(roleId)) {
			return "redirect:adminRole_list.h";
		}
		AdminRole role = this.adminService.findAdminRoleById(Integer.parseInt(roleId));
		model.put("role", role);
		List<AdminUser> adminUserList = this.adminService.getListAdminUserByRoleId(Integer.parseInt(roleId));
		model.addAttribute("adminUserList", adminUserList);
		return "admin/adminUserToRole_list";
	}
	
	/**
	 * 删除角色和管理员关系
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminUserToRole_delete")
	public String adminUserToRoleDelete(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isBlank(roleId)) {
			return "redirect:adminRole_list.h";
		}
		String delIds = request.getParameter("delIds");
		if (!StringUtils.isEmpty(delIds)) {
			String[] ids = StringUtils.split(delIds, ",");
			for (String id : ids) {
				adminService.deleteAdminUserToRole(Integer.parseInt(roleId), Integer.parseInt(id));
			}
		}
		return "redirect:adminUserToRole_list.h?roleId=" + roleId;
	}
	
	/**
	 * 角色添加用户
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminUserToRole_form", method = RequestMethod.GET)
	public String adminUserToRoleFormGet(ModelMap model,
			HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isBlank(roleId)) {
			return "redirect:adminRole_list.h";
		}
		AdminRole role = this.adminService.findAdminRoleById(Integer.parseInt(roleId));
		model.put("role", role);
		List<AdminUser> adminUserList = adminService.finAdminUserAll();
		List<AdminUser> results = new ArrayList<AdminUser>();
		for (AdminUser adminUser : adminUserList) {
			if (!this.adminService.getExistByAdminUserIdAndRoleId(adminUser.getId(), Integer.parseInt(roleId))) {
				results.add(adminUser);
			}
		}
		model.put("adminUserList", results);
		return "admin/adminUserToRole_form";
	}
	
	/**
	 * 角色添加用户
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminUserToRole_form", method = RequestMethod.POST)
	public String adminUserToRoleFormPost(ModelMap model,
			HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isBlank(roleId)) {
			return "redirect:adminRole_list.h";
		}
		String[] strIds = request.getParameterValues("duallistbox_demo1[]");
		if (strIds != null && strIds.length > 0) {
			for (String id : strIds) {
				AdminUserToRole adminUserToRole = new AdminUserToRole();
				adminUserToRole.setAdminId(Integer.parseInt(id));
				adminUserToRole.setRoleId(Integer.parseInt(roleId));
				adminService.saveAdminUserToRole(adminUserToRole);
			}
		}
		return "redirect:adminUserToRole_list.h?roleId=" + roleId;
	}
	
	/**
	 * 给角色分配功能菜单权限的页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/adminRoleUrl_form", method = RequestMethod.GET)
    public String adminRoleUrlFormGet(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = request.getParameter("roleId");
        if (StringUtils.isEmpty(roleId)) {
	        request.getSession().setAttribute("rs", "请选择角色！");
			return "redirect:adminRole_list.h";
        }
        AdminRole role = this.adminService.findAdminRoleById(Integer.parseInt(roleId));
        model.put("role", role);

        return "admin/adminRoleUrl_form";
    }
    
    /**
	 * 给角色分配功能菜单权限的页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/adminRoleUrl_form", method = RequestMethod.POST)
    public String adminRoleUrlFormPost(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String roleId = request.getParameter("roleId");
		String menuIds = request.getParameter("menuIds");
		// 先全部删除 然后再添加
		this.adminService.saveByRoleIdAndMenuIds(roleId, menuIds);
		// SystemLog.log(this, SystemLog.UPDATE, "编辑所属菜单权限:rid=".concat(rid).concat("  menuIds=").concat(menuIds), request);
		// 此处更新spring security 的权限缓存
		AdminUserInvocationSecurityMetadataSource auisms = new AdminUserInvocationSecurityMetadataSource();
		auisms.setAdminService(adminService);
		auisms.loadResourceDefine();
		return "redirect:adminRole_list.h";

    }
	
}
