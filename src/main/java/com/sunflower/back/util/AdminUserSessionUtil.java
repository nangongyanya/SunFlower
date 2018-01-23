package com.sunflower.back.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.common.constants.Constants;
import com.sunflower.common.util.IPUtil;
import com.sunflower.common.util.MD5Util;

/**
 * 管理员session操作类
 * 
 * 类名称：AdminUserSessionUtil 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class AdminUserSessionUtil {
	private static final String ADMIN_SESSION_ADMINUSER = "admin_session_adminUser";

	// private static final String ADMIN_SESSION_ADMINROLE =
	// "admin_session_adminRole";

	/**
	 * 加密密码
	 * 
	 * @param key
	 * @param loginPsw
	 * @return
	 * @since 1.0.0
	 */
	public static String encryptPsw(String key, String loginPsw) {
		try {
			String encryptPsw = MD5Util.getMD5Lower(key + loginPsw);
			return encryptPsw;
		} catch (Exception e) {
			e.printStackTrace();
			return loginPsw;
		}
	}

	/**
	 * 根据key获得session
	 * 
	 * @param session
	 * @param key
	 * @return
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	private static Object getAdminSession(HttpSession session, String key) {
		Map<String, Object> map = (Map<String, Object>) session
				.getAttribute(Constants.ADMIN_SESSION_USER);
		if (map == null) {
			return null;
		}
		Object obj = map.get(key);
		if (obj == null) {
			return null;
		}
		return obj;
	}

	/**
	 * 从session中获得管理员信息
	 * 
	 * @param session
	 * @return
	 * @since 1.0.0
	 */
	public static AdminUser getAdminSession(HttpSession session) {
		return (AdminUser) getAdminSession(session, ADMIN_SESSION_ADMINUSER);
	}

	/**
	 * 从session中获得管理员信息
	 * 
	 * @param request
	 * @return
	 * @since 1.0.0
	 */
	public static AdminUser getAdminSession(HttpServletRequest request) {
		return getAdminSession(request.getSession());
	}

	/**
	 * 设置管理员的session
	 * 
	 * @param session
	 * @param admin
	 * @param adminroleSet
	 * @param siteSet
	 * @param menuSet
	 * @since 1.0.0
	 */
	public static void setAdminSession(HttpSession session, AdminUser admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ADMIN_SESSION_ADMINUSER, admin);
		session.setAttribute(Constants.ADMIN_SESSION_USER, map);
	}

	/**
	 * 保存到cookie
	 * 
	 * @param request
	 * @param uid
	 * @return
	 * @throws NullPointerException
	 * @throws NoSuchAlgorithmException
	 * @since 1.0.0
	 */
	public static Cookie getServerCookieStr(HttpServletRequest request,
			String uid) throws NoSuchAlgorithmException, NullPointerException {
		StringBuffer buf = new StringBuffer();
		buf.append(uid);
		buf.append(request.getSession().getId());
		buf.append(IPUtil.getIpAddr(request));
		String md5Str = MD5Util.getMD5Lower(buf.toString());
		Cookie cookie = new Cookie("c", md5Str);
		cookie.setPath("/");
		return cookie;
	}

	/**
	 * 删除cookie
	 * 
	 * @return
	 * @since 1.0.0
	 */
	public static Cookie removeCookie() {
		return new Cookie("c", "");
	}

}
