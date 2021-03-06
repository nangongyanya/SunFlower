<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
						
			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>
			
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>
			
						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>
			
						<!-- #section:basics/sidebar.layout.shortcuts -->
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>
			
						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
			
						<!-- /section:basics/sidebar.layout.shortcuts -->
					</div>
			
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>
			
						<span class="btn btn-info"></span>
			
						<span class="btn btn-warning"></span>
			
						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->
			
				<ul class="nav nav-list">
					<li <c:if test="${fn:startsWith(param.menu, '0-')}">class="active open"</c:if>>
						<a href="/system/index.h">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> Dashboard </span>
						</a>

						<b class="arrow"></b>
					</li>
					<li <c:if test="${fn:startsWith(param.menu, '1-')}">class="active open"</c:if>>
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-cog"></i>
							<span class="menu-text">
								基础设置
							</span>
			
							<b class="arrow fa fa-angle-down"></b>
						</a>
			
						<b class="arrow"></b>
			
						<ul class="submenu">
							<li <c:if test="${fn:startsWith(param.menu, '1-1-')}">class="open"</c:if>>
								<a href="#" class="dropdown-toggle">
									<i class="menu-icon fa fa-caret-right"></i>
									基础数据管理
									<b class="arrow fa fa-angle-down"></b>
								</a>
			
								<b class="arrow"></b>
			
								<ul class="submenu">
									<li <c:if test="${fn:startsWith(param.menu, '1-1-1')}">class="active"</c:if>>
										<a href="/system/cms/mcCommonDataType_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											数据类型
										</a>
			
										<b class="arrow"></b>
									</li>
			
									<li <c:if test="${fn:startsWith(param.menu, '1-1-2')}">class="active"</c:if>>
										<a href="/system/cms/mcCommonData_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											基础数据
										</a>
			
										<b class="arrow"></b>
									</li>
								</ul>
							</li>
							
							<li <c:if test="${fn:startsWith(param.menu, '1-2-')}">class="open"</c:if>>
								<a href="#" class="dropdown-toggle">
									<i class="menu-icon fa fa-caret-right"></i>
									常见问答管理
									<b class="arrow fa fa-angle-down"></b>
								</a>
			
								<b class="arrow"></b>
			
								<ul class="submenu">
									<li <c:if test="${fn:startsWith(param.menu, '1-2-1')}">class="active"</c:if>>
										<a href="/system/cms/mcCommonFaq_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											常见问答
										</a>
			
										<b class="arrow"></b>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					
					<li <c:if test="${fn:startsWith(param.menu, '2-')}">class="active open"</c:if>>
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-cogs"></i>
							<span class="menu-text">
								系统功能
							</span>
			
							<b class="arrow fa fa-angle-down"></b>
						</a>
			
						<b class="arrow"></b>
			
						<ul class="submenu">
							<li <c:if test="${fn:startsWith(param.menu, '2-1-')}">class="open"</c:if>>
								<a href="#" class="dropdown-toggle">
									<i class="menu-icon fa fa-caret-right"></i>
									权限管理
									<b class="arrow fa fa-angle-down"></b>
								</a>
			
								<b class="arrow"></b>
			
								<ul class="submenu">
									<li <c:if test="${fn:startsWith(param.menu, '2-1-1')}">class="active"</c:if>>
										<a href="/system/admin/adminUser_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											管理员管理
										</a>
			
										<b class="arrow"></b>
									</li>
			
									<li <c:if test="${fn:startsWith(param.menu, '2-1-2')}">class="active"</c:if>>
										<a href="/system/admin/adminRole_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											角色管理
										</a>
			
										<b class="arrow"></b>
									</li>
									
									<li <c:if test="${fn:startsWith(param.menu, '2-1-3')}">class="active"</c:if>>
										<a href="/system/admin/adminMenu_index.h">
											<i class="menu-icon fa fa-caret-right"></i>
											功能菜单管理
										</a>
			
										<b class="arrow"></b>
									</li>
								</ul>
							</li>
							
							<li <c:if test="${fn:startsWith(param.menu, '2-2-')}">class="open"</c:if>>
								<a href="#" class="dropdown-toggle">
									<i class="menu-icon fa fa-caret-right"></i>
									系统日志
									<b class="arrow fa fa-angle-down"></b>
								</a>
			
								<b class="arrow"></b>
			
								<ul class="submenu">
									<li <c:if test="${fn:startsWith(param.menu, '2-2-1')}">class="active"</c:if>>
										<a href="/system/admin/adminLog_list.h">
											<i class="menu-icon fa fa-caret-right"></i>
											系统日志
										</a>
			
										<b class="arrow"></b>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					
					<li <c:if test="${fn:startsWith(param.menu, '100-')}">class="active open"</c:if>>
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-file-o"></i>
			
							<span class="menu-text">
								其他页面
								<!-- <span class="badge badge-primary">1</span> -->
							</span>
			
							<b class="arrow fa fa-angle-down"></b>
						</a>
			
						<b class="arrow"></b>
			
						<ul class="submenu">
							<li <c:if test="${fn:startsWith(param.menu, '100-1')}">class="active"</c:if>>
								<a href="/system/blank.h">
									<i class="menu-icon fa fa-caret-right"></i>
									空白页
								</a>
			
								<b class="arrow"></b>
							</li>
							
							<li <c:if test="${fn:startsWith(param.menu, '100-2')}">class="active"</c:if>>
								<a href="/pages/error/403.jsp">
									<i class="menu-icon fa fa-caret-right"></i>
									禁止访问
								</a>
			
								<b class="arrow"></b>
							</li>
						</ul>
					</li>
				</ul><!-- /.nav-list -->
			
				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			
				<!-- /section:basics/sidebar.layout.minimize -->
			</div>
			<!-- /section:basics/sidebar -->
