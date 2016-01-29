package cn._2vin.admin.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.log.service.LogService;
import cn._2vin.common.base.controller.AdminController;
import cn._2vin.common.exception.NotRoleRightException;
/**
 * 操作日志控制器
 * @author liuxuewen
 * @date 2014-1-22
 */
@Controller
@RequestMapping("/admin/log/*")
public class LogController extends AdminController {
	
	@Autowired
	private LogService logService;
	
	/**
	 * 日志
	 * 
	 * @return
	 * @throws NotRoleRightException 
	 */
	@RequestMapping("logList.do")
	public ModelAndView list(LogBean bean) throws NotRoleRightException {
		ModelAndView mnv = new ModelAndView();
		/**
		 * 设置导航名称
		 */
		setNavTitle(bean,mnv);
		
		mnv.addObject(DEFAULT_LIST_KEY,logService.selectPageLog(bean));
		bean.getPager().setTotalSize(logService.countPageLog(bean));
		mnv.addObject(PAGE_KEY,bean.getPager());
		mnv.addObject(BEAN_KEY,bean);
		mnv.setViewName("/admin/sys/log/logList");
		return mnv;

	}
}
