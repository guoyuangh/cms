package cn._2vin.front.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.common.base.controller.FrontController;
import cn._2vin.front.bean.TestBean;
import cn._2vin.front.info.service.TestService;
/**
 * 测试控制器
 * @author liuxuewen
 * @date 2014-1-22
 */
@Controller
@RequestMapping("/test/*")
public class TestController extends FrontController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("list.do")
	public ModelAndView list(TestBean bean) {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject(DEFAULT_LIST_KEY,testService.selectPageTest(bean));
		bean.getPager().setTotalSize(testService.countPageTest(bean));
		mnv.addObject(PAGE_KEY,bean.getPager());
		mnv.addObject(BEAN_KEY,bean);
		mnv.setViewName("/front/test/list");
		return mnv;

	}
}
