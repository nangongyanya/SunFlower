package com.sunflower.back.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunflower.back.service.McCommonDataService;
import com.sunflower.common.base.BaseController;

@Controller
@RequestMapping(value = "/system") 
public class BackStageIndexController extends BaseController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private McCommonDataService mcCommonDataService;
	
	@RequestMapping(value = "/blank") 
	public String blank(ModelMap modelMap, HttpServletRequest request) {
		return "blank";
	}
	
}
