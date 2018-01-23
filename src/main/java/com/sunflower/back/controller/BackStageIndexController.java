package com.sunflower.back.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunflower.common.base.BaseController;

/**
 * 后台主入口控制器
 * 
 * 类名称：BackStageIndexController 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping(value = "/system")
public class BackStageIndexController extends BaseController {

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 后台首页
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(ModelMap modelMap, HttpServletRequest request) {
		return "index";
	}
	
	/**
	 * 空白页
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/blank")
	public String blank(ModelMap modelMap, HttpServletRequest request) {
		return "blank";
	}

}
