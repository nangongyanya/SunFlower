package com.sunflower.back.dao.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.common.base.BaseDao;

/**
 * 角色和权限关系Dao类
 * 
 * 类名称：AdminRoleUrlDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminRoleUrlDao extends BaseDao<AdminRoleUrl> {

	/**
	 * 根据角色获取功能菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<AdminRoleUrl> findByRoleId(String roleId);
	
	/**
	 * 根据角色删除功能菜单和角色关系
	 * 
	 * @param roleId
	 */
	public void removeByRoleId(String roleId);

	/**
	 * 保存角色和功能菜单列表
	 * 
	 * @param roleId
	 * @param urlIds
	 */
	public void saveByRoleIdAndMenuIds(String roleId, String menuIds);

}
