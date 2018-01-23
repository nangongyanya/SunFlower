package com.sunflower.back.dao.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.domain.admin.AdminUserToRole;
import com.sunflower.back.support.admin.AdminUserToRoleCriteria;
import com.sunflower.common.base.BaseDao;
import com.sunflower.common.base.PagedObject;

/**
 * 管理员和角色关系Dao
 * 
 * 类名称：AdminUserToRoleDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminUserToRoleDao extends BaseDao<AdminUserToRole> {

	/**
	 * 根据管理员id删除关系列表
	 * 
	 * @param adminId
	 */
	public void deleteAdminUserToRole(Integer adminId);

	/**
	 * 根据管理员id和角色id删除关系
	 * 
	 * @param roleId
	 * @param adminId
	 */
	public void deleteAdminUserToRole(Integer roleId, Integer adminId);

	/**
	 * 根据角色id删除管理员和角色的关系
	 * 
	 * @param roleId
	 */
	public void deleteAdminUserToRoleByRoleId(Integer roleId);

	/**
	 * 根据adminId获取角色列表
	 * 
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	public List<AdminRole> getListAdminRole(Integer adminId);

	/**
	 * 根据roleId获取用户列表
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<AdminUser> getListAdminUserByRoleId(Integer roleId)
			throws Exception;

	/**
	 * 获取角色下的用户列表
	 * 
	 * @param roleId
	 * @param criteria
	 * @return
	 */
	public PagedObject queryAdminUserByRoleId(Integer roleId,
			AdminUserToRoleCriteria criteria);

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleIds
	 * @param adminId
	 */
	public void saveAdminUserToRole(Integer[] roleIds, Integer adminId);

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleId
	 * @param adminIds
	 */
	public void saveAdminUserToRole(Integer roleId, Integer[] adminIds);

}