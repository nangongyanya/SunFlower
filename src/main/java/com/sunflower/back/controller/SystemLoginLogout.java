package com.sunflower.back.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.util.AdminUserSessionUtil;
import com.sunflower.common.util.IPUtil;

/**
 * 后台登陆控制器
 * 
 * 类名称：SystemLoginLogout 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system")
public class SystemLoginLogout {
	
	@Autowired
	AdminService adminService;

	/**
	 * 管理员登录
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/login")
	public String login(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");

		HttpSession session = request.getSession();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(psw)) {
			//SystemLog.log(this, SystemLog.LOGIN, "登陆参数不全", request);
			session.setAttribute("rs", "emptyParam");
			return "redirect:/gu_ess.jsp";
		}

		AdminUser admin = this.adminService.findAdminUserByUsername(username);
		// 用户为空
		if (admin == null) {
			//SystemLog.log(this, SystemLog.LOGIN, "用户不存在==>".concat(username), request);
			session.setAttribute("rs", "emptyUser");
			return "redirect:/gu_ess.jsp";
		}
		// 用户密码不正确
		psw = AdminUserSessionUtil.encryptPsw(admin.getUsername(), psw);
		if (!psw.equals(admin.getPsw())) {
			//SystemLog.log(this, SystemLog.LOGIN, "用户密码错误==>".concat(username).concat(" p=").concat(pwd), request);
			session.setAttribute("rs", "passwordError");
			return "redirect:/gu_ess.jsp";
		}
		// 状态：0 正常，1 锁定
		if (1 == admin.getStatus()) {
			//SystemLog.log(this, SystemLog.LOGIN, "用户被锁定==>".concat(username).concat(" p=").concat(psw), request);
			session.setAttribute("rs", "userLocked");
			return "redirect:/gu_ess.jsp";
		}
		// 无登录权限
		List<AdminRole> roleList = adminService.getListAdminRole(admin.getId());
		if (roleList == null || roleList.isEmpty()) {
			session.setAttribute("rs", "norightError");
			return "redirect:/gu_ess.jsp";
		}
		
		admin.setLastLoginIp(IPUtil.getIpAddr(request));
		admin.setLastLoginDate(new Date());
		admin.setLoginCount(admin.getLoginCount() != null ? admin.getLoginCount() + 1 : 1);
		this.adminService.updateAdminUser(admin);

		// 设置admin session
		AdminUserSessionUtil.setAdminSession(request.getSession(), admin);
		// 设置admin cookie
		response.addCookie(AdminUserSessionUtil.getServerCookieStr(request, admin.getId().toString()));
		//SystemLog.log(this, SystemLog.LOGIN, "登陆成功", request);
		return "redirect:/pages/back/jump_springSecurityLogin.jsp";
	}
	
	/**
     * 管理员注销
     *   
     * @param request
     * @param response
     * @return
     * @throws Exception  
     * @since  1.0.0
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminUser adminUser = AdminUserSessionUtil.getAdminSession(request);
        if (adminUser != null) {
        	//SystemLog.log(this, SystemLog.LOGOUT, "退出==>", request);
        }
        Enumeration<String> _enum = request.getSession().getAttributeNames();
        while (_enum.hasMoreElements()) {
            String key = (String) _enum.nextElement();
            request.getSession().removeAttribute(key);
        }
        request.getSession().invalidate();

        response.addCookie(AdminUserSessionUtil.removeCookie());
        return "redirect:/system/j_spring_security_logout_new_url";
    }
	
}
