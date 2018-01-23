package com.sunflower.back.service.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.support.admin.AdminRoleCriteria;
import com.sunflower.back.support.admin.AdminUserCriteria;
import com.sunflower.back.support.admin.AdminUserToRoleCriteria;
import com.sunflower.common.base.PagedObject;

/**
 * 管理员相关Service
 * 
 * 类名称：AdminService 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminService {

	/** ************** AdminUser s *************** */
	/**
	 * 根据id删除管理员
	 * 
	 * @param id
	 */
	public void deleteAdminUser(Integer id);

	/**
	 * 根据id查询管理员
	 * 
	 * @param id
	 * @return
	 */
	public AdminUser findAdminUserById(Integer id);

	/**
	 * 根据登录名获取管理员
	 * 
	 * @param loginName
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	public AdminUser findAdminUserByUsername(String username);

	/**
	 * 查询管理员列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<AdminUser> finAdminUserAll(AdminUserCriteria criteria);

	/**
	 * 保存管理员
	 * 
	 * @param obj
	 * @return
	 */
	public Integer saveAdminUser(AdminUser obj);

	/**
	 * 更新管理员
	 * 
	 * @param obj
	 */
	public void updateAdminUser(AdminUser obj);

	/** ************** AdminUser e *************** */

	/** ************** AdminRole s *************** */
	/**
	 * 根据id删除管理员角色
	 * 
	 * @param id
	 */
	public void deleteAdminRole(Integer id);

	/**
	 * 根据id查询管理员角色
	 * 
	 * @param id
	 * @return
	 */
	public AdminRole findAdminRoleById(Integer id);
	
	/**
     * 获取所有角色列表
     *   
     * @param id  
     * @exception    
     * @since  1.0.0
     */
    public List<AdminRole> findAdminRoleAll();

	/**
	 * 查询管理员角色列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public List<AdminRole> finAdminRoleAll(AdminRoleCriteria criteria);

	/**
	 * 保存管理员角色
	 * 
	 * @param obj
	 * @return
	 */
	public Integer saveAdminRole(AdminRole obj);

	/**
	 * 更新管理员角色
	 * 
	 * @param obj
	 */
	public void updateAdminRole(AdminRole obj);

	/** ************** AdminRole e *************** */

	/** ************** AdminUserToRole s *************** */
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

	/** ************** AdminUserToRole e *************** */
	
	/** ************** AdminMenus s *************** */
	/**
	 * 根据id获得系统菜单
	 * 
	 * @param id
	 * @return
	 */
	public AdminMenus findAdminMenus(String id);
	
	/**
	 * 获得所有系统菜单
	 * @return
	 */
	public List<AdminMenus> findAdminMenusAll();
	
	/** ************** AdminMenus e *************** */
	
	/** ************** AdminRoleUrl s *************** */
	/**
	 * 根据角色获得系统功能列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<AdminRoleUrl> findAdminRoleUrlByRoleId(String roleId);
	
	/** ************** AdminRoleUrl e *************** */
}
