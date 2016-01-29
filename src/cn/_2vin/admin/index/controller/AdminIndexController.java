package cn._2vin.admin.index.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.common.base.controller.AdminController;
/**
 * 管理员首页控制器
 * @author liuxuewen
 * @date 2014-1-22
 */
@Controller
@RequestMapping("/admin/*")
public class AdminIndexController extends AdminController {
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("index.do")
	public ModelAndView index() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("/admin/index");
		return mnv;
	}
	
	/**
	 * header
	 * 
	 * @return
	 */
	@RequestMapping("header.do")
	public ModelAndView header() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("/admin/header");
		return mnv;
	}
	
	/**
	 * menu
	 * 
	 * @return
	 */
	@RequestMapping("menu.do")
	public ModelAndView menu() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("/admin/menu");
		return mnv;
	}
	
	/**
	 * center
	 * 
	 * @return
	 */
	@RequestMapping("center.do")
	public ModelAndView center(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("serverDateTime",new Date().getTime());
		mnv.setViewName("/admin/center");
		return mnv;
	}
}
