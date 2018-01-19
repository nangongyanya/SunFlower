package com.sunflower.back.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunflower.back.domain.McCommonData;
import com.sunflower.back.domain.McCommonDataType;
import com.sunflower.back.service.McCommonDataService;
import com.sunflower.back.support.McCommonDataCriteria;
import com.sunflower.back.support.McCommonDataTypeCriteria;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.base.PagedObject;
import com.sunflower.common.vo.JsonDetail;

@Controller
@RequestMapping(value = "/system") 
public class McCommonDataController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private McCommonDataService mcCommonDataService;
	
	@RequestMapping(value = "/mcCommonDataType_list") 
	public String mcCommonDataType_list(ModelMap modelMap, HttpServletRequest request) {
		String typeName = request.getParameter("typeName");
		
		McCommonDataTypeCriteria criteria = new McCommonDataTypeCriteria();
		criteria.setName(typeName);
		
		List<McCommonDataType> typeList = this.mcCommonDataService.findMcCommonDataTypeAll(criteria);
		modelMap.put("results", typeList);
		modelMap.put("criteria", criteria);
		
		return "mcCommonData/mcCommonDataType_list";
	}
	
	@RequestMapping(value = "/mcCommonDataType_delete")
	public String mcCommonDataType_delete(ModelMap modelMap, HttpServletRequest request) {
		String delIds = request.getParameter("delIds");
		String[] ids = delIds.split(",");
		for (String id : ids) {
			if (!StringUtils.isEmpty(id)) {
				this.mcCommonDataService.deleteMcCommonDataType(Integer.parseInt(id));
			}
		}
		
		String referer = request.getHeader("referer"); 
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:/system/mcCommonDataType_list.h";
	}
	
	@RequestMapping(value = "/ajax_get_mcCommonDataType", method = RequestMethod.POST)
	public String ajax_get_mcCommonDataType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			McCommonDataType type = this.mcCommonDataService.findMcCommonDataTypeById(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(type);
		} catch (Exception e) {
			log.error("异步获取基础数据类型出错 --> ", e);
			e.printStackTrace();
			json.setStatus(false);
			json.setInfo("数据出错");
		}
		return this.json2Response(json, response);
	}
	
	@RequestMapping(value = "/mcCommonDataType_update", method = RequestMethod.POST)
	public String mcCommonDataType_update(ModelMap modelMap, HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		McCommonDataType type = new McCommonDataType();
		if (!StringUtils.isEmpty(id)) {
			type = this.mcCommonDataService.findMcCommonDataTypeById(Integer.parseInt(id));
		} 
		type.setName(name);
		
		// 保存
		if (StringUtils.isEmpty(id)) {
			this.mcCommonDataService.saveMcCommonDataType(type);
		} 
		// 更新
		else {
			this.mcCommonDataService.updateMcCommonDataType(type);
		}
		
		return "redirect:/system/mcCommonDataType_list.h";
	}
	
	@RequestMapping(value = "/mcCommonData_list") 
	public String mcCommonData_list(ModelMap modelMap, HttpServletRequest request) {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		McCommonDataCriteria criteria = (McCommonDataCriteria) this
				.getCriteria(new McCommonDataCriteria(), request);
		criteria.setName(name);
		if (!StringUtils.isEmpty(type)) {
			criteria.setType(Integer.parseInt(type));
		}
		PagedObject po = this.mcCommonDataService.queryMcCommonData(criteria);
		po.setCriteria(criteria);
		modelMap.addAttribute("po", po);
		
		modelMap.addAttribute("typeList", this.mcCommonDataService.findMcCommonDataTypeAll(new McCommonDataTypeCriteria()));
		
		return "mcCommonData/mcCommonData_list";
	}
	
	@RequestMapping(value = "/mcCommonData_delete")
	public String mcCommonData_delete(ModelMap modelMap, HttpServletRequest request) {
		String delIds = request.getParameter("delIds");
		String[] ids = delIds.split(",");
		for (String id : ids) {
			if (!StringUtils.isEmpty(id)) {
				this.mcCommonDataService.deleteMcCommonData(Integer.parseInt(id));
			}
		}
		
		String referer = request.getHeader("referer"); 
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:/system/mcCommonData_list.h";
	}
	
	@RequestMapping(value = "/ajax_get_mcCommonData", method = RequestMethod.POST)
	public String ajax_get_mcCommonData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			McCommonData data = this.mcCommonDataService.findMcCommonDataById(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(data);
		} catch (Exception e) {
			log.error("异步获取基础数据类型出错 --> ", e);
			e.printStackTrace();
			json.setStatus(false);
			json.setInfo("数据出错");
		}
		return this.json2Response(json, response);
	}
	
	@RequestMapping(value = "/mcCommonData_update", method = RequestMethod.POST)
	public String mcCommonData_update(ModelMap modelMap, HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		
		McCommonData data = new McCommonData();
		if (!StringUtils.isEmpty(id)) {
			data = this.mcCommonDataService.findMcCommonDataById(Integer.parseInt(id));
		} 
		data.setName(name);
		data.setSort(Integer.parseInt(sort));
		data.setStatus(Integer.parseInt(status));
		data.setType(Integer.parseInt(type));
		
		// 保存
		if (StringUtils.isEmpty(id)) {
			this.mcCommonDataService.saveMcCommonData(data);
		} 
		// 更新
		else {
			this.mcCommonDataService.updateMcCommonData(data);
		}
		
		String referer = request.getHeader("referer"); 
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:/system/mcCommonData_list.h";
	}
	
}