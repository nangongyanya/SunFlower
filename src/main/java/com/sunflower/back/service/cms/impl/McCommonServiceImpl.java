package com.sunflower.back.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.back.dao.cms.McCommonDataDao;
import com.sunflower.back.dao.cms.McCommonDataTypeDao;
import com.sunflower.back.dao.cms.McCommonFaqDao;
import com.sunflower.back.domain.cms.McCommonData;
import com.sunflower.back.domain.cms.McCommonDataType;
import com.sunflower.back.domain.cms.McCommonFaq;
import com.sunflower.back.service.cms.McCommonService;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.back.support.cms.McCommonDataTypeCriteria;
import com.sunflower.back.support.cms.McCommonFaqCriteria;
import com.sunflower.common.base.PagedObject;

/**
 * 基础数据相关ServiceImpl
 * 
 * 类名称：McCommonDataServiceImpl 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Service
public class McCommonServiceImpl implements McCommonService {
	// private Logger log = Logger.getLogger(this.getClass());

	/** ************** McCommonDataType s *************** */
	@Autowired
	private McCommonDataTypeDao mcCommonDataTypeDao;

	/**
	 * 根据id删除基础数据类型
	 * 
	 * @param id
	 */
	@Override
	public void deleteMcCommonDataType(Integer id) {
		this.mcCommonDataTypeDao.deleteById(id);
	}

	/**
	 * 根据条件查询基础数据类型列表
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public List<McCommonDataType> findMcCommonDataTypeAll(
			McCommonDataTypeCriteria criteria) {
		return this.mcCommonDataTypeDao.finAll(criteria);
	}

	/**
	 * 根据id查询基础数据类型
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public McCommonDataType findMcCommonDataTypeById(Integer id) {
		return this.mcCommonDataTypeDao.findById(id);
	}

	/**
	 * 保存基础数据类型
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public Integer saveMcCommonDataType(McCommonDataType obj) {
		Date current = new Date();
		obj.setDateAdded(current);
		obj.setLastModified(current);
		return (Integer) this.mcCommonDataTypeDao.save(obj);
	}

	/**
	 * 更新基础数据类型
	 * 
	 * @param obj
	 */
	@Override
	public void updateMcCommonDataType(McCommonDataType obj) {
		obj.setLastModified(new Date());
		this.mcCommonDataTypeDao.update(obj);
	}

	/** ************** McCommonDataType e *************** */

	/** ************** McCommonData s *************** */
	@Autowired
	private McCommonDataDao mcCommonDataDao;

	/**
	 * 根据id删除基础数据类型
	 * 
	 * @param id
	 */
	@Override
	public void deleteMcCommonData(Integer id) {
		this.mcCommonDataDao.deleteById(id);
	}

	/**
	 * 根据id查询基础数据类型
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public McCommonData findMcCommonDataById(Integer id) {
		return this.mcCommonDataDao.findById(id);
	}

	/**
	 * 查询基础数据类型列表（可分页）
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject queryMcCommonData(McCommonDataCriteria criteria) {
		return this.mcCommonDataDao.query(criteria);
	}

	/**
	 * 保存基础数据类型
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public Integer saveMcCommonData(McCommonData obj) {
		Date current = new Date();
		obj.setDateAdded(current);
		obj.setLastModified(current);
		return (Integer) this.mcCommonDataDao.save(obj);
	}

	/**
	 * 更新基础数据类型
	 * 
	 * @param obj
	 */
	@Override
	public void updateMcCommonData(McCommonData obj) {
		obj.setLastModified(new Date());
		this.mcCommonDataDao.update(obj);
	}

	/** ************** McCommonData e *************** */
	
	/** ************** McCommonFaq s *************** */
	@Autowired
	private McCommonFaqDao mcCommonFaqDao;
	
	/**
	 * 根据id删除常见问答
	 * 
	 * @param id
	 */
	@Override
	public void deleteMcCommonFaq(Integer id) {
		this.mcCommonFaqDao.deleteById(id);
	}

	/**
	 * 根据条件查询常见问答列表
	 * 
	 * @param criteria
	 * @return
	 */
	@Override
	public PagedObject queryMcCommonFaq(McCommonFaqCriteria criteria) {
		return this.mcCommonFaqDao.query(criteria);
	}

	/**
	 * 根据id查询常见问答
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public McCommonFaq findMcCommonFaqById(Integer id) {
		return this.mcCommonFaqDao.findById(id);
	}

	/**
	 * 保存常见问答
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public Integer saveMcCommonFaq(McCommonFaq obj) {
		Date current = new Date();
		obj.setDateAdded(current);
		obj.setLastModified(current);
		return (Integer)this.mcCommonFaqDao.save(obj);
	}

	/**
	 * 更新常见问答
	 * 
	 * @param obj
	 */
	@Override
	public void updateMcCommonFaq(McCommonFaq obj) {
		Date current = new Date();
		obj.setLastModified(current);
		this.mcCommonFaqDao.update(obj);
	}

	/** ************** McCommonFaq e *************** */
}
