package com.sunflower.back.dao.admin;

import com.sunflower.back.domain.admin.AdminLog;
import com.sunflower.back.support.admin.AdminLogCriteria;
import com.sunflower.common.base.BaseDao;
import com.sunflower.common.base.PagedObject;

/**
 * 系统日志Dao类
 * 
 * 类名称：AdminLogDao 创建时间：Feb 7, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface AdminLogDao extends BaseDao<AdminLog> {
	/**
	 * 获取系统日志列表
	 * 
	 */
    public PagedObject queryAdminLog(AdminLogCriteria criteria);
}
