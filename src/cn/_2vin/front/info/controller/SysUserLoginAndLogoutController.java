package cn._2vin.front.info.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn._2vin.admin.bean.SysUser;
import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.admin.sysuser.service.SysUserService;
import cn._2vin.common.base.controller.FrontController;
import cn._2vin.common.util.IpHelper;
import cn._2vin.common.util.StringHelper;

import com.google.code.kaptcha.Producer;

/**
 * 登录、注销Controller
 * 
 * @author liuxuewen
 * @date 2014-1-22 
 */
@Controller
@RequestMapping("/*")
public class SysUserLoginAndLogoutController extends FrontController {

	@Autowired
	private Producer captchaProducer;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 登录首页
	 * 
	 * @return
	 */
	@RequestMapping("login.do")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("/login");
		String action = request.getParameter("action");
		if (null != action && "login".equals(action)) {
			String errorMessage = null;
			String loginName = request.getParameter("loginName");
			String loginPassword = request.getParameter("loginPassword");
			if (!StringHelper.isNull(loginName) && !StringHelper.isNull(loginPassword)) {
				String validateCode = request.getParameter("adminValidateCode");
				if (!StringHelper.isNull(validateCode)) {
					HttpSession session = request.getSession();
					String backVCode = (String) session.getAttribute(ConstantAdmin.KAPTCHA_ADMIN_SESSION_KEY);
					if (validateCode.equals(backVCode)) {
						SysUser user = new SysUser(loginName, loginPassword);
						user = sysUserService.login(user);
						if (null == user) {
							errorMessage = ConstantAdmin.LOGIN_ERROR_LOGINNAME_OR_PASSWORD_ERROR;
						} else {
							user.setSysModules(sysUserService.getSysModules(user));
							session.setAttribute(ConstantAdmin.SESSION_ADMIN_INFO, user);
							/**
							 * 更新最后登录信息
							 */
							SysUser up = new SysUser();
							up.setId(user.getId());//id
							up.setLastLoginIp(IpHelper.getIpAddr(request));//ip
							sysUserService.updateLoginAfter(up);
							
							/**
							 * 重定向到管理员首页
							 */
							mnv = new ModelAndView(new RedirectView(request.getContextPath()+"/admin/index.do"));
						}
					} else {
						errorMessage = ConstantAdmin.LOGIN_ERROR_VALIDATE_CODE_ERROR;
					}
				} else {
					errorMessage = ConstantAdmin.LOGIN_ERROR_VALIDATE_CODE_NULL;
				}

			} else {
				errorMessage = ConstantAdmin.LOGIN_ERROR_LOGINNAME_OR_PASSWORD_NULL;
			}
			if(null != errorMessage){
				mnv.addObject(ConstantAdmin.LOGIN_ERROR, errorMessage);
			}
		}
		return mnv;
	}

	/**
	 * 注销页面
	 * 
	 * @return
	 */
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request,HttpSession session) {
		ModelAndView mnv = new ModelAndView(new RedirectView(request.getContextPath()+"/login.do"));
		Object user = session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
		/**
		 * 不等于空，才进行service操作
		 */
		if (null != user) {
			sysUserService.logout((SysUser) user);
			session.removeAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
		}
		return mnv;
	}

	/**
	 * 产生验证码图片，验证码保存到session中
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("adminValidateImage.jpg")
	public String image(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(ConstantAdmin.KAPTCHA_ADMIN_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
}
