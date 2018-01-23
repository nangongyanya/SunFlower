package com.sunflower.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sunflower.back.dao.admin.AdminUserDao;
import com.sunflower.back.dao.admin.AdminUserToRoleDao;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminUser;

/**
 * 管理员信息管理Service
 * 
 * 类名称：AdminUserDetailServiceImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 *          实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数。
 *          你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等。
 */
public class AdminUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AdminUserDao adminUserDao;
	@Autowired
    private AdminUserToRoleDao adminUserToRoleDao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		AdminUser adminuser = this.adminUserDao.findByUsername(username);
		if (adminuser == null) {
			return null;
		}
		// 状态：0 正常，1 锁定
		boolean enabled = false;
		if (0 == adminuser.getStatus()) {
			enabled = true;
		}
		List<AdminRole> adminRoleList = null;
		try {
			adminRoleList = this.adminUserToRoleDao.getListAdminRole(adminuser.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (adminRoleList == null || adminRoleList.isEmpty()) {
			return null;
		}
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (AdminRole adminrole : adminRoleList) {
			String roleCode = adminrole.getRoleCode();
			GrantedAuthorityImpl auth2 = new GrantedAuthorityImpl(roleCode);
			auths.add(auth2);
		}
		User user = new User(adminuser.getUsername(), adminuser.getPsw(),
				enabled, true, true, true, auths);
		return user;
	}

}
