package com.sunflower.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunflower.back.domain.admin.AdminMenus;
import com.sunflower.back.domain.admin.AdminRole;
import com.sunflower.back.domain.admin.AdminRoleUrl;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.common.constants.Constants;

/**
 * 资源数据定义，即定义某一资源可以被哪些角色访问
 * 
 * 类名称：AdminUserInvocationSecurityMetadataSource 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
@Component
public class AdminUserInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static final Log log = LogFactory.getLog("AdminUserInvocationSecurityMetadataSource.class");
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
   
    private AdminService adminService;

    public AdminService getAdminService() {
        return adminService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
                loadResourceDefine();
        }
    }
    
    public AdminUserInvocationSecurityMetadataSource() {}

    public void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        log.info("**********************************************************************");
        log.info("Spring Security All role loading.......................");
        List<AdminRole> adminRoleList = this.adminService.findAdminRoleAll();
        for (AdminRole adminrole : adminRoleList) {
            ConfigAttribute ca = new SecurityConfig(adminrole.getRoleCode());
            // 系统管理员 默认拥有系统所有权限
            if (Constants.SYSTEM_ROLE.equals(adminrole.getId())) {
                List<AdminMenus> adminMenusList = this.getAdminService().findAdminMenusAll();
                for (AdminMenus adminMenu : adminMenusList) {
                    String urlStr = adminMenu.getUrl();
                    if (!org.apache.commons.lang.StringUtils.isEmpty(urlStr)) {
                        urlStr = urlStr.replace("\r\n", "@");
                        String urls[] = StringUtils.split(urlStr, "@");
                        for (String _url : urls) {
                            _url=StringUtils.trim(_url);
                            if (!org.apache.commons.lang.StringUtils.isEmpty(_url)) {
                                //todo 添加所有权限. 并判断重复
                                Collection<ConfigAttribute> obj = resourceMap.get(_url);
                                if (obj == null) {
                                    obj = new HashSet<ConfigAttribute>();
                                    obj.add(ca);
                                    resourceMap.put(_url, obj);
                                } else {
                                    obj.add(ca);
                                    resourceMap.put(_url, obj);
                                }
                            }
                        }
                    }
                }
            } 
            // 非最高权限只能通过数据库设置的来读取
            else { 
                List<AdminRoleUrl> urlList = this.adminService.findAdminRoleUrlByRoleId(adminrole.getId().toString());
                for (AdminRoleUrl url : urlList) {
                    AdminMenus menu = this.adminService.findAdminMenus(url.getMenuId());
                    
                    if (menu == null) {
                    	continue;
                    }
                    String urlStr = menu.getUrl();
                    if (!org.apache.commons.lang.StringUtils.isEmpty(urlStr)) {
                        urlStr = urlStr.replace("\r\n", "@");
                        String urls[] = StringUtils.split(urlStr, "@");
                        for (String _url : urls) {
                            _url=StringUtils.trim(_url);
                            if (!org.apache.commons.lang.StringUtils.isEmpty(_url)) {
                                // 添加所有权限. 并判断重复
                                Collection<ConfigAttribute> obj = resourceMap.get(_url);
                                if (obj == null) {
                                    obj = new HashSet<ConfigAttribute>();
                                    obj.add(ca);
                                    resourceMap.put(_url, obj);
                                } else {
                                    obj.add(ca);
                                    resourceMap.put(_url, obj);
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("urls:" + resourceMap.size() + "  roles:" + adminRoleList.size() + "");
        log.info("**********************************************************************");
    }

    /**
     * 根据URL，查找此URL的权限配置
     * 
     * @param object
     * @return
     */
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getHttpRequest().getRequestURI();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(url, resURL)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 根据角色获得功能菜单
     *   
     * @param roleCode
     * @return  
     * Set<String>   
     * @exception    
     * @since  1.0.0
     */
    public static Set<String> getUrlsByRoleCode(String roleCode) {
        Set<String> urlList = new HashSet<String>();
        Set<String> urlSet = resourceMap.keySet();
        for (String url : urlSet) {
            Collection<ConfigAttribute> collCa = resourceMap.get(url);
            for (ConfigAttribute ca : collCa) {
                if (!org.apache.commons.lang.StringUtils.isEmpty(ca.getAttribute()) && ca.getAttribute().equals(roleCode)) {
                    urlList.add(url);
                }
            }
        }
        return urlList;
    }

    /**
     * 判断功能菜单url和角色
     *   
     * @param url
     * @param roleCode
     * @return  
     * boolean   
     * @exception    
     * @since  1.0.0
     */
    public static boolean checkUrlAndRoleCode(String url, String roleCode) {
        Collection<ConfigAttribute> collCa = resourceMap.get(url);
        if(collCa==null){
            return false;
        }
        for (ConfigAttribute ca : collCa) {
            if (!org.apache.commons.lang.StringUtils.isEmpty(ca.getAttribute()) && ca.getAttribute().equals(roleCode)) {
                return true;
            }
        }
        return false;
    }
}
