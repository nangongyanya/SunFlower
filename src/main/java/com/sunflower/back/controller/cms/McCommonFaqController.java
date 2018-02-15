package com.sunflower.back.controller.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunflower.back.domain.cms.McCommonFaq;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.service.cms.McCommonService;
import com.sunflower.back.support.cms.McCommonDataCriteria;
import com.sunflower.back.support.cms.McCommonFaqCriteria;
import com.sunflower.back.util.SystemLogUtil;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.base.PagedObject;
import com.sunflower.common.constants.CmsConstants;
import com.sunflower.common.vo.JsonDetail;

/**
 * 常见问答控制器
 * 
 * 类名称：McCommonFaqController 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/cms")
public class McCommonFaqController extends BaseController {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private McCommonService mcCommonService;
	@Autowired
	private AdminService adminService;

	/**
	 * 常见问题列表
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcCommonFaq_list")
	public String mcCommonFaqList(ModelMap modelMap,
			HttpServletRequest request) {
		String faqQuestion = request.getParameter("faqQuestion");
		String faqType = request.getParameter("faqType");

		McCommonFaqCriteria criteria = (McCommonFaqCriteria) this
				.getCriteria(new McCommonFaqCriteria(), request);
		criteria.setFaqQuestion(faqQuestion);
		if (!StringUtils.isEmpty(faqType)) {
			criteria.setFaqType(Integer.parseInt(faqType));
		}
		PagedObject po = this.mcCommonService.queryMcCommonFaq(criteria);
		po.setCriteria(criteria);
		modelMap.addAttribute("po", po);

		McCommonDataCriteria dataCriteria = new McCommonDataCriteria();
		dataCriteria.setType(CmsConstants.COMMON_DATA_FAQ);
		modelMap.addAttribute("typePo", this.mcCommonService.queryMcCommonData(dataCriteria));

		return "cms/mcCommonFaq_list";
	}

	@RequestMapping(value = "/mcCommonFaq_delete")
	public String mcCommonFaqDelete(ModelMap modelMap,
			HttpServletRequest request) {
		String delIds = request.getParameter("delIds");
		String[] ids = delIds.split(",");
		for (String id : ids) {
			if (!StringUtils.isEmpty(id)) {
				this.mcCommonService.deleteMcCommonFaq(Integer.parseInt(id));
			}
		}
		SystemLogUtil.log(adminService, this, SystemLogUtil.DELETE, "删除常见问题:".concat(delIds), request);

		String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:McCommonFaq_list.h";
	}

	/**
	 * 异步获取常见问答
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ajax_get_McCommonFaq", method = RequestMethod.POST)
	public String ajaxGetMcCommonFaq(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		JsonDetail json = new JsonDetail();
		try {
			if (StringUtils.isEmpty(id)) {
				json.setInfo("参数不完整!");
				json.setStatus(false);
				return this.json2Response(json, response);
			}

			McCommonFaq data = this.mcCommonService.findMcCommonFaqById(Integer.parseInt(id));
			json.setStatus(true);
			json.setItems(data);
		} catch (Exception e) {
			log.error("异步获取常见问答出错 --> ", e);
			e.printStackTrace();
			json.setStatus(false);
			json.setInfo("数据出错");
		}
		return this.json2Response(json, response);
	}

	/**
	 * 更新常见问答
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mcCommonFaq_update", method = RequestMethod.POST)
	public String mcCommonFaqUpdate(ModelMap modelMap, HttpServletRequest request) {
		String id = request.getParameter("id");
		String faqQuestion = request.getParameter("faqQuestion");
		String faqAnswer = request.getParameter("faqAnswer");
		String faqType = request.getParameter("faqType");

		McCommonFaq data = new McCommonFaq();
		if (!StringUtils.isEmpty(id)) {
			data = this.mcCommonService.findMcCommonFaqById(Integer
					.parseInt(id));
		}
		data.setFaqQuestion(faqQuestion);
		data.setFaqAnswer(faqAnswer);
		data.setFaqType(Integer.parseInt(faqType));
		
		// 保存
		if (StringUtils.isEmpty(id)) {
			this.mcCommonService.saveMcCommonFaq(data);
			SystemLogUtil.log(adminService, this, SystemLogUtil.SAVE, "添加常见问题:".concat(data.getId().toString()), request);
		}
		// 更新
		else {
			this.mcCommonService.updateMcCommonFaq(data);
			SystemLogUtil.log(adminService, this, SystemLogUtil.UPDATE, "修改常见问题:".concat(data.getId().toString()), request);
		}

		String referer = request.getHeader("referer");
		if (!StringUtils.isEmpty(referer)) {
			return "redirect:" + referer;
		}
		return "redirect:mcCommonFaq_list.h?faqType=" + faqType;
	}
	
	/**
	 * 预览常见问答
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mcCommonFaq_preview")
	public String mcCommonFaqPreview(ModelMap modelMap, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		McCommonFaq faq = this.mcCommonService.findMcCommonFaqById(Integer.parseInt(id));
		modelMap.addAttribute("faq", faq);

		return "cms/mcCommonFaq_preview";
	}

}
