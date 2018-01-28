package com.sunflower.back.controller.cms;

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

import com.sunflower.back.domain.cms.McCommonData;
import com.sunflower.back.domain.cms.McCommonDataType;
import com.sunflower.back.service.cms.McCommonDataService;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.back.support.cms.McCommonDataTypeCriteria;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.base.PagedObject;
import com.sunflower.common.vo.JsonDetail;

/**
 * 基础数据控制器
 * 
 * 类名称：McCommonDataController 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/cms")
public class McCommonDataController extends BaseController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private McCommonDataService mcCommonDataService;

	/**
	 * 获取基础数据类型列表
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcCommonDataType_list")
	public String mcCommonDataTypeList(ModelMap modelMap,
			HttpServletRequest request) {
		String typeName = request.getParameter("typeName");

		McCommonDataTypeCriteria criteria = new McCommonDataTypeCriteria();
		criteria.setName(typeName);

		List<McCommonDataType> typeList = this.mcCommonDataService
				.findMcCommonDataTypeAll(criteria);
		modelMap.put("results", typeList);
		modelMap.put("criteria", criteria);

		return "mcCommonData/mcCommonDataType_list";
	}

	/**
	 * 删除基础数据类型
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcCommonDataType_delete")
	public String mcCommonDataTypeDelete(ModelMap modelMap,
			HttpServletRequest request) {
		String delIds = request.getParameter("delIds");
		String[] ids = delIds.split(",");
		for (String id : ids) {
			if (!StringUtils.isEmpty(id)) {
				McCommonDataCriteria criteria = new McCommonDataCriteria();
				criteria.setType(Integer.parseInt(id));
				PagedObject po = this.mcCommonDataService.queryMcCommonData(criteria);
				if (po != null && po.getTotal() > 0) {
					request.getSession().setAttribute("rs", "该类别下用户基础数据,请先删除基础数据!");
					return "redirect:mcCommonDataType_list.h";
				}
				this.mcCommonDataService.deleteMcCommonDataType(Integer.parseInt(id));
			}
		}

		String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:mcCommonDataType_list.h";
	}

	/**
	 * 异步获取基础数据类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax_get_mcCommonDataType", method = RequestMethod.POST)
	public String ajaxGetMcCommonDataType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			McCommonDataType type = this.mcCommonDataService
					.findMcCommonDataTypeById(Integer.parseInt(id));
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

	/**
	 * 更新基础数据类型
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcCommonDataType_update", method = RequestMethod.POST)
	public String mcCommonDataTypeUpdate(ModelMap modelMap,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		McCommonDataType type = new McCommonDataType();
		if (!StringUtils.isEmpty(id)) {
			type = this.mcCommonDataService.findMcCommonDataTypeById(Integer
					.parseInt(id));
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

		return "redirect:mcCommonDataType_list.h";
	}

	@RequestMapping(value = "/mcCommonData_list")
	public String mcCommonDataList(ModelMap modelMap,
			HttpServletRequest request) {
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

		modelMap.addAttribute("typeList", this.mcCommonDataService
				.findMcCommonDataTypeAll(new McCommonDataTypeCriteria()));

		return "mcCommonData/mcCommonData_list";
	}

	@RequestMapping(value = "/mcCommonData_delete")
	public String mcCommonDataDelete(ModelMap modelMap,
			HttpServletRequest request) {
		String delIds = request.getParameter("delIds");
		String[] ids = delIds.split(",");
		for (String id : ids) {
			if (!StringUtils.isEmpty(id)) {
				this.mcCommonDataService.deleteMcCommonData(Integer
						.parseInt(id));
			}
		}

		String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:mcCommonData_list.h";
	}

	@RequestMapping(value = "/ajax_get_mcCommonData", method = RequestMethod.POST)
	public String ajaxGetMcCommonData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			McCommonData data = this.mcCommonDataService
					.findMcCommonDataById(Integer.parseInt(id));
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
	public String mcCommonDataUpdate(ModelMap modelMap,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");
		String status = request.getParameter("status");
		String type = request.getParameter("type");

		McCommonData data = new McCommonData();
		if (!StringUtils.isEmpty(id)) {
			data = this.mcCommonDataService.findMcCommonDataById(Integer
					.parseInt(id));
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
		return "redirect:mcCommonData_list.h";
	}

}
