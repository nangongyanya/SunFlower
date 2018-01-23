package com.sunflower.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/index") 
	public String index(ModelMap modelMap, HttpServletRequest request) {
		return "index";
	}
	
}
