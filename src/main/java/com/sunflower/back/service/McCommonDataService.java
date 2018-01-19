package com.sunflower.back.service;

import java.util.List;

import com.sunflower.back.domain.McCommonData;
import com.sunflower.back.domain.McCommonDataType;
import com.sunflower.back.support.McCommonDataCriteria;
import com.sunflower.back.support.McCommonDataTypeCriteria;
import com.sunflower.common.base.PagedObject;

public interface McCommonDataService {
	
	/** ************** McCommonDataType s *************** */
	/**
	 * 根据id删除基础数据类型
	 * 
	 * @param id
	 */
	public void deleteMcCommonDataType(Integer id);
	
	/**
	 * 根据条件查询基础数据类型列表
	 * 
	 * @param criteria
	 * @return
	 */
	public List<McCommonDataType> findMcCommonDataTypeAll(McCommonDataTypeCriteria criteria);
	
	/**
	 * 根据id查询基础数据类型
	 * 
	 * @param id
	 * @return
	 */
	public McCommonDataType findMcCommonDataTypeById(Integer id);

	/**
	 * 保存基础数据类型
	 * 
	 * @param obj
	 * @return
	 */
	public Integer saveMcCommonDataType(McCommonDataType obj);
	
	/**
	 * 更新基础数据类型
	 * 
	 * @param obj
	 */
	public void updateMcCommonDataType(McCommonDataType obj);

	/** ************** McCommonDataType e *************** */
	
	/** ************** McCommonData s *************** */
	/**
	 * 根据id删除基础数据类型
	 * 
	 * @param id
	 */
	public void deleteMcCommonData(Integer id);
	
	/**
	 * 根据id查询基础数据类型
	 * 
	 * @param id
	 * @return
	 */
	public McCommonData findMcCommonDataById(Integer id);
	
	/**
	 * 查询基础数据类型列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	public PagedObject queryMcCommonData(McCommonDataCriteria criteria);

	/**
	 * 保存基础数据类型
	 * 
	 * @param obj
	 * @return
	 */
	public Integer saveMcCommonData(McCommonData obj);
	
	/**
	 * 更新基础数据类型
	 * 
	 * @param obj
	 */
	public void updateMcCommonData(McCommonData obj);

	/** ************** McCommonDataType e *************** */
}
