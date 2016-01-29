package cn._2vin.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn._2vin.admin.constant.ConstantAdmin;

/**
 * 管理员登录过滤器；（已经不用）
 * @author liuxuewen
 * @date 2014-1-22
 */
public class AdminIsLoginFilter implements Filter {
	private String loginPage;

	public void init(FilterConfig filterconfig) throws ServletException {
		this.loginPage = filterconfig.getInitParameter("doNotLoginPage");
	}

	public void destroy() {
		loginPage = null;
	}

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		String bastPath = request.getContextPath();
		HttpSession session = request.getSession(true);
		if (null == session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO)) {
			String thisUri = request.getRequestURI();
			int thisUriStartIndex = thisUri.indexOf(bastPath + "/admin/");
			if (0 == thisUriStartIndex) {
				response.sendRedirect(bastPath + loginPage + "?"+ConstantAdmin.LOGIN_ERROR+"=" + ConstantAdmin.LOGIN_ERROR_NOT_LOGIN_ERROR);
				return;
			}
		}
		filterchain.doFilter(servletrequest, servletresponse);
	}
}
