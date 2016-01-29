package cn._2vin.admin.module.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.bean.SysModule;
import cn._2vin.admin.module.service.SysModuleService;
import cn._2vin.common.base.controller.AdminController;
import cn._2vin.common.bean.ActionResult;
import cn._2vin.common.exception.NotRoleRightException;
import cn._2vin.common.util.StringHelper;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
@Controller
@RequestMapping("/admin/sysmodule/*")
public class SysModuleController extends AdminController {

	@Autowired
	private SysModuleService sysModuleService;

	/**
	 * 系统模块列表
	 * 
	 * @return
	 * @throws NotRoleRightException 
	 */
	@RequestMapping("sysmoduleList.do")
	public ModelAndView list(SysModule bean) throws NotRoleRightException {
		ModelAndView mnv = new ModelAndView();
		/**
		 * 设置导航名称
		 */
		setNavTitle(bean, mnv);

		mnv.addObject(DEFAULT_LIST_KEY, sysModuleService.selectPageSysModule(bean));
		bean.getPager().setTotalSize(sysModuleService.countPageSysModule(bean));
		mnv.addObject(PAGE_KEY, bean.getPager());
		mnv.addObject(BEAN_KEY, bean);
		mnv.setViewName("/admin/sys/sysmodule/sysmoduleList");
		return mnv;
	}

	/**
	 * 添加或者修改form
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("sysmoduleForm.do")
	public ModelAndView sysroleForm(String id) {
		ModelAndView mav = new ModelAndView();
		SysModule bean = new SysModule();
		if (!StringHelper.isNull(id)) {
			bean = sysModuleService.queryPrimaryKey(id);
			mav.addObject("update", true);
		}else{
			mav.addObject("add", true);
			mav.addObject("parentModuleList", sysModuleService.allParentModule(null));
		}
		mav.addObject(BEAN_KEY, bean);
		mav.setViewName("/admin/sys/sysmodule/sysmoduleForm");
		return mav;
	}

	@RequestMapping("insertOrUpdate.do")
	@ResponseBody
	public ActionResult insertOrUpdate(SysModule bean) {
		ActionResult result = new ActionResult();
		try {
			if (StringHelper.isNull(bean.getModuleId())) {
				sysModuleService.insert(bean);
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
				result.setMessage(OPERATOR_RESULT_SUCCESS_ADD_VALUE);
			} else {
				SysModule oldBean = sysModuleService.queryPrimaryKey(bean.getModuleId());
				sysModuleService.update(bean, oldBean);
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
				result.setMessage(OPERATOR_RESULT_SUCCESS_UPDATE_VALUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(OPERATOR_RESULT_ERROR_KEY);
			result.setMessage(OPERATOR_RESULT_ERROR_VALUE);
		}
		return result;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDelete.do")
	@ResponseBody
	public ActionResult batchDelete(String ids) {
		ActionResult result = new ActionResult();
			try {
				result = sysModuleService.batchDelete(ids);
				if(result.getCode()>=0){
					result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
					result.setMessage(OPERATOR_RESULT_SUCCESS_DELETE_VALUE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResult(OPERATOR_RESULT_ERROR_KEY);
				result.setMessage("可能原因是：“该模块包含子模块！请先删除子模块！”或者“id不存在”！");
			}
		return result;
	}
	
	/**
	 * 新增或修改时验证是否已经存在相同主键,传入id，为修改
	 * 
	 * @param
	 * @return PageResultBean对象
	 */
	@RequestMapping("checkModuleName.do")
	public @ResponseBody
	ActionResult checkModuleName(String moduleName, String moduleId) {
		ActionResult result = new ActionResult();
		try {
			int count = sysModuleService.selectByNameExcludeId(moduleName, moduleId);
			if (count == 0) {
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
			} else {
				result.setResult(OPERATOR_RESULT_FAIL_KEY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(OPERATOR_RESULT_ERROR_KEY);
		}
		return result;
	}
	/**
	 * 用于ztree的数据
	 * @param id
	 * @return
	 */
	@RequestMapping("sysmoduleZTree.do")
	@ResponseBody
	public List<SysModule> sysmoduleZTree() {
		List<SysModule> list =  sysModuleService.queryList(null);
		return list;
	}
}
