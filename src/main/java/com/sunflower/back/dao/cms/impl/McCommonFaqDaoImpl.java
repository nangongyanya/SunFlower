package com.sunflower.back.dao.cms.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunflower.back.dao.cms.McCommonFaqDao;
import com.sunflower.back.domain.cms.McCommonFaq;
import com.sunflower.back.support.cms.McCommonFaqCriteria;
import com.sunflower.common.base.BaseDaoImpl;
import com.sunflower.common.base.PagedObject;

/**
 * 常见问答DaoImpl类
 * 
 * 类名称：McCommonFaqDaoImpl 创建时间：Feb 10, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class McCommonFaqDaoImpl extends BaseDaoImpl<McCommonFaq> implements
		McCommonFaqDao {

	/**
	 * 拼凑sql
	 * 
	 * @param params
	 * @param criteria
	 * @return
	 */
	public StringBuilder appSql(Map<String, Object> params,
			McCommonFaqCriteria criteria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from McCommonFaq mc");
		sql.append(" where 1 = 1");
		if (!StringUtils.isEmpty(criteria.getSortColumn())) {
			sql.append(" order by mc." + criteria.getSortColumn());
			sql.append(" "
					+ (!StringUtils.isEmpty(criteria.getSortDirect()) ? criteria
							.getSortDirect() : "desc"));
		} else {
			sql.append(" order by mc.id asc");
		}
		return sql;
	}
	
	/**
	 * 查询常见问答列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject query(McCommonFaqCriteria criteria) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = this.appSql(params, criteria);

		PagedObject po = this.getPagedObject(sql, criteria.getFirstResult(),
				criteria.getMaximumResultSize(), params);
		return po;
	}

}
