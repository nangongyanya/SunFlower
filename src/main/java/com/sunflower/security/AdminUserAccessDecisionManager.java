package com.sunflower.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

/**
 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 * 
 * 类名称：AdminUserAccessDecisionManager 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 *          最重要的是decide方法，如果不存在对该资源的定义，直接放行；
 *          存在对该资源的定义时，如果当前用户是正确的角色拥有权限，则放行；否则抛出异常AccessDeniedException，进入403.jsp页面。
 */
public class AdminUserAccessDecisionManager implements AccessDecisionManager {

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		/** 如果不存在对该资源的定义，直接放行 */
		if (configAttributes == null) {
			return;
		}
		
		/** 存在对该资源的定义时，如果当前用户是正确的角色拥有权限，则放行 */
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ca.getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		
		/** 抛出异常AccessDeniedException，进入403.jsp页面 */
		String url = ((FilterInvocation) object).getRequestUrl();
		throw new AccessDeniedException("您无权访问" + url);
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
