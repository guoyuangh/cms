package cn._2vin.common.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.common.base.controller.AdminController;

public class AdminInterceptor implements HandlerInterceptor {

	public static final String loginPage = "/login.do";


	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if (obj instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) obj;
			/**
			 * 管理员后台控制器
			 */
			if (method.getBean() instanceof AdminController) {
				String bastPath = request.getContextPath();
				HttpSession session = request.getSession(true);
				if (null == session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO)) {
					// String thisUri = request.getRequestURI();
					// int thisUriStartIndex = thisUri.indexOf(".do")+3;
					// if (thisUri.length() == thisUriStartIndex) {
					response.sendRedirect(bastPath + loginPage + "?" + ConstantAdmin.LOGIN_ERROR + "=" + ConstantAdmin.LOGIN_ERROR_NOT_LOGIN_ERROR);
					return false;
					// }
				} else {
					return true;
				}
			}
		}
		return true;
	}

}