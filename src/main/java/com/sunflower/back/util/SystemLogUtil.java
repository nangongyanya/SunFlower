package com.sunflower.back.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sunflower.back.domain.admin.AdminLog;
import com.sunflower.back.domain.admin.AdminUser;
import com.sunflower.back.service.admin.AdminService;
import com.sunflower.common.util.IPUtil;

/**
 * 系统日志操作类
 * 
 * 类名称：SystemLogUtil 创建时间：Feb7, 2018
 * 
 * @version 1.0.0
 * 
 */
public class SystemLogUtil {

    public static final String SAVE = "save";
    public static final String DELETE = "delete";
    public static final String UPDATE = "update";
    public static final String IMPORT = "import";
    public static final String EXPORT = "export";
    public static final String DOWNLOAD = "download";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String ERROR = "ERROR";

    /**
     * 插入系统日志
     *   
     * @param objClass
     * @param optType
     * @param opt_desc
     * @param remake
     * @param request  
     * @since  1.0.0
     */
    public static void log(AdminService adminService, Object objClass, String optType, HttpServletRequest request) {
        log(adminService, objClass, optType, "", "", request);
    }

    /**
     * 插入系统日志
     *   
     * @param objClass
     * @param optType
     * @param optDesc
     * @param remake
     * @param request  
     * @since  1.0.0
     */
    public static void log(AdminService adminService, Object objClass, String optType, String optDesc, HttpServletRequest request) {
        log(adminService, objClass, optType, optDesc, "", request);
    }

    /**
     * 插入系统日志
     *   
     * @param objClass
     * @param optType
     * @param optDesc
     * @param remake
     * @param request  
     * @since  1.0.0
     */
    public static void log(AdminService adminService, Object objClass, String optType, String optDesc, String remake, HttpServletRequest request) {
        try {
            AdminUser admin = AdminUserSessionUtil.getAdminSession(request.getSession());
            String client_ip = "--:--:--:--";
            if (admin != null) {
                client_ip = admin.getLastLoginIp();
            } else {
                client_ip = IPUtil.getIpAddr(request);
            }

            StringBuffer buf = new StringBuffer();
            String adminUsername = "";
            if (admin != null) {
                buf.append(admin.getNickname());
                buf.append("[");
                buf.append(admin.getUsername());
                buf.append("]");
                adminUsername = buf.toString();
            }
            buf.append(":");
            buf.append(optType);
            buf.append("==>");
            buf.append(optDesc);
            buf.append(remake);

            Log log = LogFactory.getLog(objClass.getClass());
            if(optType.equalsIgnoreCase(ERROR)){
                log.error(client_ip.concat("==>").concat(buf.toString()));
            }else{
                log.info(client_ip.concat("==>").concat(buf.toString()));
            }

            AdminLog adminLog = new AdminLog();
            if (admin != null) {
                adminLog.setAdminId(admin.getId());
                adminLog.setAdminName(adminUsername);
            }
            adminLog.setClassMethod(objClass.getClass().getSimpleName());
            adminLog.setOptIp(client_ip);
            adminLog.setOptType(optType);
            adminLog.setOptDesc(optDesc);
            adminLog.setOptDate(new Date());
            adminLog.setRemake(remake);
            adminService.saveAdminLog(adminLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
