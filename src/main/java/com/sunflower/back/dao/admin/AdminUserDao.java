package com.sunflower.back.dao.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.support.admin.AdminUserCriteria;
import com.sunflower.common.base.BaseDao;

/**
 * 管理员类
 * 
 * 类名称：AdminUserDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminUserDao extends BaseDao<AdminUser> {

	/**
	 * 根据登录名获得管理员
	 * 
	 * @param loginName
	 * @return
	 */
	public AdminUser findByUsername(String loginName);

	/**
	 * 查询管理员列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<AdminUser> finAll(AdminUserCriteria criteria);

}
