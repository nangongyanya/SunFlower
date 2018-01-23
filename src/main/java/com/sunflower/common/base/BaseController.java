package com.sunflower.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.sunflower.common.util.ResponseUtil;
import com.sunflower.common.vo.JsonDetail;

/**
 * 控制器基类
 * 
 * 类名称：BaseController 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public abstract class BaseController {
	
	public static int default_pager_items = 20; // 每页默认数量为20

	/**
	 * 获取查询类
	 * 
	 * @param criteria
	 * @param request
	 * @return
	 */
    public BaseCriteria getCriteria(BaseCriteria criteria, HttpServletRequest request) {
        return this.getCriteria(criteria, request, default_pager_items);
    }

    /**
	 * 获取查询类
	 * 
	 * @param criteria
	 * @param request
	 * @return
	 */
    public BaseCriteria getCriteria(BaseCriteria criteria, HttpServletRequest request,int pageItems) {
        Integer offset = 0;
        if (!StringUtils.isEmpty(request.getParameter("pager.offset"))) {
        	offset = Integer.parseInt(request.getParameter("pager.offset"));
        }
        Integer items = pageItems;
        if (!StringUtils.isEmpty(request.getParameter("pager.items"))) {
        	items = Integer.parseInt(request.getParameter("pager.items"));
        }
        criteria.setFirstResult(offset);
        criteria.setMaximumResultSize(items);
        criteria.setName_order(request.getParameter("name_order"));
        return criteria;
    }

	/**
	 * json转成string
	 * 
	 * @param data
	 * @param response
	 * @throws Exception
	 */
	public String json2Response(JsonDetail data, HttpServletResponse response)
			throws Exception {
		ResponseUtil.json2Response(data.serializeToJSONString(), response);
		return null;
	}

}