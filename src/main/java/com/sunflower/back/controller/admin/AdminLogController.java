package com.sunflower.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunflower.back.service.admin.AdminService;
import com.sunflower.back.support.admin.AdminLogCriteria;
import com.sunflower.common.base.BaseController;
import com.sunflower.common.base.PagedObject;

/**
 * 系统日志Controller
 * 
 * 类名称：AdminLogController 创建时间：Feb 7, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system/admin")
public class AdminLogController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	/**
	 * 系统日志列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/adminLog_list")
	public String AdminLogList(ModelMap model, HttpServletRequest request) {
		String optType = request.getParameter("optType");
		AdminLogCriteria criteria = (AdminLogCriteria) this
				.getCriteria(new AdminLogCriteria(), request);
		criteria.setOptType(optType);
		PagedObject po = this.adminService.queryAdminLog(criteria);
		model.addAttribute("po", po);
		po.setCriteria(criteria);
		return "admin/adminLog_list";
	}

}
