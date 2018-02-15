package com.sunflower.back.service.cms;

import java.util.List;

import com.sunflower.back.domain.cms.McCommonData;
import com.sunflower.back.domain.cms.McCommonDataType;
import com.sunflower.back.domain.cms.McCommonFaq;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.back.support.cms.McCommonDataTypeCriteria;
import com.sunflower.back.support.cms.McCommonFaqCriteria;
import com.sunflower.common.base.PagedObject;

/**
 * 基础数据相关Service
 * 
 * 类名称：McCommonDataService 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public interface McCommonService {

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
	public List<McCommonDataType> findMcCommonDataTypeAll(
			McCommonDataTypeCriteria criteria);

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
	
	/** ************** McCommonFaq s *************** */
	/**
	 * 根据id删除常见问答
	 * 
	 * @param id
	 */
	public void deleteMcCommonFaq(Integer id);

	/**
	 * 根据条件查询常见问答列表
	 * 
	 * @param criteria
	 * @return
	 */
	public PagedObject queryMcCommonFaq(McCommonFaqCriteria criteria);

	/**
	 * 根据id查询常见问答
	 * 
	 * @param id
	 * @return
	 */
	public McCommonFaq findMcCommonFaqById(Integer id);

	/**
	 * 保存常见问答
	 * 
	 * @param obj
	 * @return
	 */
	public Integer saveMcCommonFaq(McCommonFaq obj);

	/**
	 * 更新常见问答
	 * 
	 * @param obj
	 */
	public void updateMcCommonFaq(McCommonFaq obj);

	/** ************** McCommonFaq e *************** */
}
