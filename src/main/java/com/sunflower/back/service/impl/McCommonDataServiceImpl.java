package com.sunflower.back.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflower.back.dao.McCommonDataDao;
import com.sunflower.back.dao.McCommonDataTypeDao;
import com.sunflower.back.domain.McCommonData;
import com.sunflower.back.domain.McCommonDataType;
import com.sunflower.back.service.McCommonDataService;
import com.sunflower.back.support.McCommonDataCriteria;
import com.sunflower.back.support.McCommonDataTypeCriteria;
import com.sunflower.common.base.PagedObject;

@Service
public class McCommonDataServiceImpl implements McCommonDataService {
	//private Logger log = Logger.getLogger(this.getClass());

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
	public List<McCommonDataType> findMcCommonDataTypeAll(McCommonDataTypeCriteria criteria) {
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
		return (Integer)this.mcCommonDataDao.save(obj);
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
}
