package com.sunflower.back.dao.admin;

import java.util.List;

import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.common.base.BaseDao;

/**
 * 功能菜单Dao类
 * 
 * 类名称：AdminMenusDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminMenusDao extends BaseDao<AdminMenus> {

	/**
	 * 删除系统菜单
	 * 
	 * @param obj
	 */
    public void delete(AdminMenus obj);
    
	/**
	 * 根据url删除栏目权限
	 * 
	 * @param url
	 */
	public void deleteAdminMenus(String url);
	
	/**
	 * 获取顶级系统功能列表
	 * 
	 * @return
	 */
	public List<AdminMenus> findAdminMenusFirst();
	
	/**
	 * 根据url获取权限信息
	 * 
	 * @param url
	 * @return
	 */
	public AdminMenus getAdminMenusByUrl(String url);

}
