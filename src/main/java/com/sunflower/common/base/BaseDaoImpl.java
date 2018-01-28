package com.sunflower.common.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


/**
 * 基础DaoImpl
 * 
 * 类名称：BaseDao 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {

	@Autowired
	public SessionFactory sessionFactory;

	public Class<T> getClasses() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// 获取和当前线程绑定的 Session.
	public Session getSession() {
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
		// return sessionFactory.getCurrentSession();
	}

	/**
	 * 清除
	 */
	public void clear() {
		getSession().clear();
	}
	
	/**
	 * 刷新
	 */
    public void flush() {
    	getSession().flush();
    }
    
	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 返回对象ID
	 */
	@Override
	public Serializable save(T entity) {
		try {
			return getSession().save(entity);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存对象的数组
	 * 
	 * @param entitys
	 * @return
	 */
	@Override
	public Serializable[] save(T[] entitys) {
		if (entitys == null || entitys.length == 0) {
			return null;
		}
		Serializable[] ids = new Serializable[entitys.length];
		int i = 0;
		for (T t : entitys) {
			ids[i] = this.save(t);
			i++;
		}
		return ids;
	}

	/**
	 * 保存对象的集合
	 * 
	 * @param entitys
	 * @return
	 */
	@Override
	public Serializable[] save(Collection<T> entitys) {
		if (CollectionUtils.isEmpty(entitys)) {
			return null;
		}
		Serializable[] ids = new Serializable[entitys.size()];

		int i = 0;
		for (T t : entitys) {
			ids[i] = this.save(t);
			i++;
		}
		return ids;
	}

	/**
	 * 直接删除记录的对象
	 * 
	 * @param o
	 */
	@Override
	public void deleteAdminMenus(T o) {
		getSession().delete(o);
		getSession().flush();
	}

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 *            记录ID
	 */
	@Override
	public void deleteById(Serializable id) {
		this.deleteAdminMenus(this.get(id));
	}

	/**
	 * 根据ID数组删除记录，当发生异常时，操作终止并回滚
	 * 
	 * @param ids
	 *            记录ID数组
	 * @return 删除的对象
	 */
	@Override
	public void deleteById(Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(id);
		}
	}

	/**
	 * 更新对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 返回对象ID
	 */
	@Override
	public void update(T entity) {
		getSession().update(entity);
		getSession().flush();
	}

	/**
	 * 更新对象的数组
	 * 
	 * @param entitys
	 * @return
	 */
	@Override
	public void update(T[] entitys) {
		for (T entity : entitys) {
			this.update(entity);
		}
	}

	/**
	 * 更新对象的集合
	 * 
	 * @param entitys
	 * @return
	 */
	@Override
	public void update(Collection<T> entitys) {
		for (T entity : entitys) {
			this.update(entity);
		}
	}

	/**
	 * 通过ID查找对象
	 * 
	 * @param id
	 *            记录的ID
	 * @return 实体对象
	 */
	@Override
	public T findById(Serializable id) {
		return (T) get(id);
	}

	/**
	 * 通过ID获取对象
	 * 
	 * @param id
	 *            记录的ID
	 * @return 实体对象
	 */
	@Override
	public T get(Serializable id) {
		return (T) getSession().get(getClasses(), id);
	}
	
	/**
     * 查找所有对象
     *
     * @return 对象列表
     */
	@Override
	public List<T> findAll() {
		return getSession().createQuery("from " + getClasses().getName()).list();
    }

	/**
	 * 返回参数类型所对应的整型数值 null-0，数组-1，集合 2
	 * 
	 * @param param
	 * @return
	 */
	private int getParameterType(Object param) {
		if (param == null)
			return 0;
		if (param.getClass().isArray())
			return 2;
		Class[] clss = param.getClass().getInterfaces();
		if (clss == null)
			return 0;
		for (Class cls : clss) {
			if (cls == Collection.class || cls == List.class)
				return 1;
		}
		return 0;
	}

	/**
	 * 设置查询语句参数
	 * 
	 * @param q
	 * @param params
	 * @return
	 */
	private Query putParameters(Query q, Map<String, Object> params) {
		String[] keys = q.getNamedParameters();
		if (keys != null && params != null) {
			for (String key : keys) {
				if (!params.containsKey(key)) {
					throw new RuntimeException("没有设置参数" + key + "的值");
				}
				Object value = params.get(key);
				int flg = getParameterType(value);
				switch (flg) {
				case 0:
					q.setParameter(key, value);
					break;
				case 1:
					q.setParameterList(key, (Collection) value);
					break;
				case 2:
					q.setParameterList(key, (Object[]) value);
					break;
				}
			}
		}
		return q;
	}

	/**
	 * 删除查询条件中的order by子句
	 * 
	 * @param queryString
	 *            查询SQL语句
	 * @return 删除查询语句中的order by子句后的查询语句
	 */
	private String delOrderbySQL(String queryString) {
		String result = queryString;
		int idx = queryString.indexOf("order by");
		if (idx > 0) {
			result = queryString.substring(0, idx);
		}
		return result;
	}

	/**
	 * 创建hsql查询语句
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	protected Query createQuery(String hsql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hsql);

		return this.putParameters(q, params);
	}

	/**
	 * 创建sql查询语句
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	protected Query createSqlQuery(String hsql, Map<String, Object> params) {
		Query q = this.getSession().createSQLQuery(hsql);
		return this.putParameters(q, params);
	}

	/**
	 * 返回共有多少条数
	 * 
	 * @param hsql
	 *            hql语句
	 * @param params
	 *            查询参数
	 * @return 总条数
	 */
	protected Long getResultCnt(String hsql, Map<String, Object> params) {
		// 删除查询语句中的order by子句
		String newSql = delOrderbySQL(hsql);
		int distinctindex = newSql.toUpperCase().indexOf("GROUP");
		if (distinctindex > 0) {
			StringTokenizer st = new StringTokenizer(newSql, ", \t\n\r\f");
			while (st.hasMoreTokens()) {
				if (st.nextToken().toUpperCase().equals("GROUP")) {
					if (st.nextToken().toUpperCase().equals("BY")) {
						int idx = newSql.toUpperCase().indexOf("FROM ");
						newSql = "select count(*) " + newSql.substring(idx);
						Query q = createQuery(newSql, params);
						List list = q.list();
						return (long) list.size();
					} else {
						break;
					}
				}
			}
		}
		// 修改SQL语句
		int idx = newSql.toUpperCase().indexOf("FROM ");
		newSql = "select count(*) " + newSql.substring(idx);
		Query q = createQuery(newSql, params);
		return (Long) q.iterate().next();
	}

	/**
	 * 获取分页范围内的结果集
	 * 
	 * @param sql
	 * @param first
	 * @param max
	 * @param params
	 * @return
	 */
	public PagedObject getPagedObject(String sql, Integer first, Integer max,
			Map<String, Object> params) {
		if (first == null) {
			first = 0;
		}
		if (max == null) {
			max = Integer.MAX_VALUE;
		}
		PagedObject po = new PagedObject();

		// 数据库中的所有数据的总数量
		Long cnt = getResultCnt(sql, params);
		if (cnt == 0) {
			po.setResults(new ArrayList());
			return po;
		}
		// 数据库中的所有数据的总数量
		po.setTotal(cnt.intValue());

		// 取得本次查询返回结果集
		Query q = createQuery(sql, params);
		q.setFirstResult(first);
		q.setMaxResults(max);
		po.setResults(q.list());
		return po;
	}

	/**
	 * 获取分页范围内的结果集
	 * 
	 * @param sql
	 * @param first
	 * @param max
	 * @param params
	 * @return
	 */
	public PagedObject getPagedObject(StringBuilder sql, Integer first,
			Integer max, Map<String, Object> params) {
		return getPagedObject(sql.toString(), first, max, params);
	}
}
