package com.sunflower.back.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.support.admin.AdminUserCriteria;
import com.sunflower.back.util.AdminUserSessionUtil;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.constants.Constants;
import com.sunflower.common.vo.JsonDetail;

/**
 * 管理员Controller
 * 
 * 类名称：AdminUserController 创建时间：Jan 29, 2013
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminUserController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	/**
	 * 管理员列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminUser_list")
	public String adminUserList(ModelMap model, HttpServletRequest request)
			throws Exception {
		AdminUserCriteria criteria = new AdminUserCriteria();
		List<AdminUser> userList = this.adminService.finAdminUserAll(criteria);
		model.addAttribute("userList", userList);
		return "admin/adminUser_list";
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminUser_delete")
	public String adminUserDelete(HttpServletRequest request) throws Exception {
		String delIds = request.getParameter("delIds");
		AdminUser user = AdminUserSessionUtil.getAdminSession(request);
		if (!StringUtils.isEmpty(delIds)) {
			String[] ids = delIds.split(",");
			for (String id : ids) {
				if (!StringUtils.isEmpty(id) && (id.equalsIgnoreCase(Constants.SYSTEM_ADMIN_ID))) {
					request.getSession().setAttribute("rs", "不能删除超级管理员的账号!");
					return "redirect:adminUser_list.h";
				}
				if (user.getId().equals(id)) {
					request.getSession().setAttribute("rs", "不能删除自己的账号!");
					return "redirect:adminUser_list.h";
				}
				this.adminService.deleteAdminUser(Integer.parseInt(id));
			}
		}
		
		return "forward:adminUser_list.h";
	}

	/**
	 * 异步获取管理员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax_get_adminUser", method = RequestMethod.POST)
	public String ajaxGetAdminUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			AdminUser adminUser = this.adminService.findAdminUserById(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(adminUser);
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
	@RequestMapping(value = "/adminUser_update", method = RequestMethod.POST)
	public String adminUserUpdate(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		String status = request.getParameter("status");

		// 判断名称是否已经存在
		AdminUser adminUser = this.adminService.findAdminUserByUsername(username);
		if (adminUser != null && !adminUser.getId().toString().equals(id)) {
			request.getSession().setAttribute("rs", "用户名已存在，请重新添加！");
			return "redirect:adminUser_list.h";
		}
		
		if (adminUser == null && !StringUtils.isEmpty(id)) {
			adminUser = this.adminService.findAdminUserById(Integer.parseInt(id));
		}
			
		if (adminUser == null) {
			adminUser = new AdminUser();
		}

		adminUser.setNickname(nickname);
		adminUser.setUsername(username);
		adminUser.setPsw(AdminUserSessionUtil.encryptPsw(username, psw));
		adminUser.setStatus(Integer.parseInt(status));

		// 更新
		if (adminUser.getId() != null) {
			this.adminService.updateAdminUser(adminUser);
		}
		// 保存
		else {
			this.adminService.saveAdminUser(adminUser);
		}
		return "redirect:adminUser_list.h";
	}

}
