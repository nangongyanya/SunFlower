package com.sunflower.back.service.admin.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.back.dao.admin.AdminMenusDao;
import com.sunflower.back.dao.admin.AdminRoleDao;
import com.sunflower.back.dao.admin.AdminRoleUrlDao;
import com.sunflower.back.dao.admin.AdminUserDao;
import com.sunflower.back.dao.admin.AdminUserToRoleDao;
import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.support.admin.AdminRoleCriteria;
import com.sunflower.back.support.admin.AdminUserCriteria;
import com.sunflower.back.support.admin.AdminUserToRoleCriteria;
import com.sunflower.common.base.PagedObject;

/**
 * 管理员相关ServiceImpl
 * 
 * 类名称：AdminService 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Service
public class AdminServiceImpl implements AdminService {
	// private Logger log = Logger.getLogger(this.getClass());

	/** ************** AdminUser s *************** */
	@Autowired
	private AdminUserDao adminUserDao;

	/**
	 * 根据id删除管理员
	 * 
	 * @param id
	 */
	@Override
	public void deleteAdminUser(Integer id) {
		this.adminUserDao.deleteById(id);
	}

	/**
	 * 根据id查询管理员
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public AdminUser findAdminUserById(Integer id) {
		return this.adminUserDao.findById(id);
	}

	/**
	 * 根据登录名获取管理员
	 * 
	 * @param loginName
	 * @return
	 * @exception
	 * @since 1.0.0
	 */
	@Override
	public AdminUser findAdminUserByUsername(String username) {
		return this.adminUserDao.findByUsername(username);
	}

	/**
	 * 查询管理员列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public List<AdminUser> finAdminUserAll(AdminUserCriteria criteria) {
		return this.adminUserDao.finAll(criteria);
	}

	/**
	 * 保存管理员
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public Integer saveAdminUser(AdminUser obj) {
		Date current = new Date();
		obj.setDateAdded(current);
		return (Integer) this.adminUserDao.save(obj);
	}

	/**
	 * 更新管理员
	 * 
	 * @param obj
	 */
	@Override
	public void updateAdminUser(AdminUser obj) {
		this.adminUserDao.update(obj);
	}

	/** ************** AdminUser e *************** */

	/** ************** AdminRole s *************** */
	@Autowired
	private AdminRoleDao adminRoleDao;

	/**
	 * 根据id删除管理员角色
	 * 
	 * @param id
	 */
	@Override
	public void deleteAdminRole(Integer id) {
		this.adminRoleDao.deleteById(id);
	}

	/**
	 * 根据id查询管理员角色
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public AdminRole findAdminRoleById(Integer id) {
		return this.adminRoleDao.findById(id);
	}
	
	/**
     * 获取所有角色列表
     *   
     * @param id  
     * @exception    
     * @since  1.0.0
     */
	@Override
    public List<AdminRole> findAdminRoleAll() {
		return this.adminRoleDao.findAll();
	}

	/**
	 * 查询管理员角色列表（无分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public List<AdminRole> finAdminRoleAll(AdminRoleCriteria criteria) {
		return this.adminRoleDao.finAll(criteria);
	}

	/**
	 * 保存管理员角色
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public Integer saveAdminRole(AdminRole obj) {
		return (Integer) this.adminRoleDao.save(obj);
	}

	/**
	 * 更新管理员角色
	 * 
	 * @param obj
	 */
	@Override
	public void updateAdminRole(AdminRole obj) {
		this.adminRoleDao.update(obj);
	}

	/** ************** AdminRole e *************** */

	/** ************** AdminUserToRole s *************** */
	@Autowired
    private AdminUserToRoleDao adminUserToRoleDao;
	
	/**
	 * 根据管理员id删除关系列表
	 * 
	 * @param adminId
	 */
	@Override
	public void deleteAdminUserToRole(Integer adminId) {

	}

	/**
	 * 根据管理员id和角色id删除关系
	 * 
	 * @param roleId
	 * @param adminId
	 */
	@Override
	public void deleteAdminUserToRole(Integer roleId, Integer adminId) {

	}

	/**
	 * 根据角色id删除管理员和角色的关系
	 * 
	 * @param roleId
	 */
	public void deleteAdminUserToRoleByRoleId(Integer roleId) {

	}

	/**
	 * 根据adminId获取角色列表
	 * 
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AdminRole> getListAdminRole(Integer adminId) {
		return adminUserToRoleDao.getListAdminRole(adminId);
	}

	/**
	 * 根据roleId获取用户列表
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AdminUser> getListAdminUserByRoleId(Integer roleId)
			throws Exception {
		return null;
	}

	/**
	 * 获取角色下的用户列表
	 * 
	 * @param roleId
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject queryAdminUserByRoleId(Integer roleId,
			AdminUserToRoleCriteria criteria) {
		return null;
	}

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleIds
	 * @param adminId
	 */
	@Override
	public void saveAdminUserToRole(Integer[] roleIds, Integer adminId) {
	}

	/**
	 * 保存管理员和角色的关系
	 * 
	 * @param roleId
	 * @param adminIds
	 */
	@Override
	public void saveAdminUserToRole(Integer roleId, Integer[] adminIds) {

	}

	/** ************** AdminUserToRole e *************** */
	
	/** ************** AdminMenus s *************** */
	@Autowired
	private AdminMenusDao adminMenusDao;
	
	/**
	 * 根据id获得系统菜单
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public AdminMenus findAdminMenus(String id) {
		return this.adminMenusDao.findById(id);
	}
	
	/**
	 * 获得所有系统菜单
	 * @return
	 */
	@Override
	public List<AdminMenus> findAdminMenusAll() {
		return this.adminMenusDao.findAll();
	}
	
	/** ************** AdminMenus e *************** */
	
	/** ************** AdminRoleUrl s *************** */
	@Autowired
	private AdminRoleUrlDao adminRoleUrlDao;
	/**
	 * 根据角色获得系统功能列表
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<AdminRoleUrl> findAdminRoleUrlByRoleId(String roleId) {
		return this.adminRoleUrlDao.findByRoleId(roleId);
	}
	
	/** ************** AdminRoleUrl e *************** */
}
