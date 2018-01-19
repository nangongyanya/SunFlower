package com.sunflower.common.base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T extends Serializable> {

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 返回对象ID
	 */
	public Serializable save(T entity);

	/**
	 * 保存对象的数组
	 * 
	 * @param entitys
	 * @return
	 */
	public Serializable[] save(T[] entitys);

	/**
	 * 保存对象的集合
	 * 
	 * @param entitys
	 * @return
	 */
	public Serializable[] save(Collection<T> entitys);

	/**
	 * 直接删除记录的对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 根据ID删除记录
	 * 
	 * @param id 记录ID
	 */
	public void deleteById(Serializable id);

	/**
	 * 根据ID数组删除记录，当发生异常时，操作终止并回滚
	 * 
	 * @param ids 记录ID数组
	 * @return 删除的对象
	 */
	public void deleteById(Serializable[] ids);

	/**
	 * 更新对象
	 * 
	 * @param entity 实体对象
	 * @return 返回对象ID
	 */
	public void update(T entity);

	/**
	 * 更新对象的数组
	 * 
	 * @param entitys
	 * @return
	 */
	public void update(T[] entitys);

	/**
	 * 更新对象的集合
	 * 
	 * @param entitys
	 * @return
	 */
	public void update(Collection<T> entitys);

	/**
	 * 通过ID查找对象
	 * 
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	public T findById(Serializable id);

	/**
	 * 通过ID获取对象
	 * 
	 * @param id 记录的ID
	 * @return 实体对象
	 */
	public T get(Serializable id);

}
