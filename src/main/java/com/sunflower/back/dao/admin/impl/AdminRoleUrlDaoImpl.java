package com.sunflower.back.dao.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.admin.AdminRoleUrlDao;
import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.common.base.BaseDaoImpl;

/**
 * 角色和权限关系DaoImpl类
 * 
 * 类名称：AdminRoleUrlDaoImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class AdminRoleUrlDaoImpl extends BaseDaoImpl<AdminRoleUrl> implements AdminRoleUrlDao {

	/**
	 * 根据角色获取功能菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<AdminRoleUrl> findByRoleId(String roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
		String sql = " from AdminRoleUrl ar where ar.roleId = :roleId";
		params.put("roleId", roleId);
		Query query = this.createQuery(sql.toString(), params);
		return query.list();
    }
    
    /**
	 * 根据角色删除功能菜单和角色关系
	 * 
	 * @param roleId
	 */
    @Override
    public void removeByRoleId(String roleId) {
    	Map<String, Object> params = new HashMap<String, Object>();
		String sql = "delete AdminRoleUrl ar where ar.roleId = :roleId";
		params.put("roleId", roleId);
		Query query = this.createQuery(sql.toString(), params);
        query.executeUpdate();
    }

    /**
	 * 保存角色和功能菜单列表
	 * 
	 * @param roleId
	 * @param urlIds
	 */
    @Override
    public void saveByRoleIdAndurlIds(String roleId, String urlIds) {
        removeByRoleId(roleId);
        String urlIdss[] = urlIds.split(",");
        for (String urlId : urlIdss) {
            if (!StringUtils.isEmpty(urlId)) {
                AdminRoleUrl ars = new AdminRoleUrl();
                ars.setMenuId(urlId);
                ars.setRoleId(roleId);
                this.save(ars);
            }
        }
    }

}
