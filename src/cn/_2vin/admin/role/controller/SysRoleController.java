package cn._2vin.admin.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.bean.SysModule;
import cn._2vin.admin.bean.SysRole;
import cn._2vin.admin.module.service.SysModuleService;
import cn._2vin.admin.role.service.SysRoleService;
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
@RequestMapping("/admin/sysrole/*")
public class SysRoleController extends AdminController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysModuleService sysModuleService;
	/**
	 * 权限角色列表
	 * 
	 * @return
	 * @throws NotRoleRightException 
	 */
	@RequestMapping("sysroleList.do")
	public ModelAndView list(SysRole bean) throws NotRoleRightException {
		ModelAndView mnv = new ModelAndView();
		/**
		 * 设置导航名称
		 */
		setNavTitle(bean, mnv);

		mnv.addObject(DEFAULT_LIST_KEY, sysRoleService.selectPageSysRole(bean));
		bean.getPager().setTotalSize(sysRoleService.countPageSysRole(bean));
		mnv.addObject(PAGE_KEY, bean.getPager());
		mnv.addObject(BEAN_KEY, bean);
		mnv.setViewName("/admin/sys/sysrole/sysroleList");
		return mnv;
	}

	/**
	 * 添加或者修改form
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("sysroleForm.do")
	public ModelAndView sysroleForm(String id) {
		ModelAndView mav = new ModelAndView();
		SysRole bean = new SysRole();
		if (!StringHelper.isNull(id)) {
			bean = sysRoleService.queryPrimaryKey(id);
			updateSetTreeSysModuleIds(id, bean);		
		}
		mav.addObject(BEAN_KEY, bean);
		mav.setViewName("/admin/sys/sysrole/sysroleForm");
		return mav;
	}

	@RequestMapping("insertOrUpdate.do")
	@ResponseBody
	public ActionResult insertOrUpdate(SysRole bean) {
		ActionResult result = new ActionResult();
		try {
			if (StringHelper.isNull(bean.getRoleId())) {
				sysRoleService.insert(bean);
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
				result.setMessage(OPERATOR_RESULT_SUCCESS_ADD_VALUE);
			} else {
				SysRole oldBean = sysRoleService.queryPrimaryKey(bean.getRoleId());
				updateSetTreeSysModuleIds(bean.getRoleId(), oldBean);
				sysRoleService.update(bean, oldBean);
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
				result = sysRoleService.batchDelete(ids);
				if(result.getCode()>=0){
					result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
					result.setMessage(OPERATOR_RESULT_SUCCESS_DELETE_VALUE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResult(OPERATOR_RESULT_ERROR_KEY);
				result.setMessage("可能原因是：“id不存在”！");
			}
		return result;
	}
	
	/**
	 * 新增或修改时验证是否已经存在相同登陆用户,传入id，为修改
	 * 
	 * @param
	 * @return PageResultBean对象
	 */
	@RequestMapping("checkRoleCode.do")
	public @ResponseBody
	ActionResult checkRoleCode(String roleCode, String roleId) {
		ActionResult result = new ActionResult();
		try {
			int count = sysRoleService.selectByNameExcludeId(roleCode, roleId);
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
	 * 设置权限角色授权的权限模块id
	 * @param id
	 * @param bean
	 */
	private void updateSetTreeSysModuleIds(String id, SysRole bean) {
		if(null != bean){
			/**
			 * 权限授权列表，以字符串分隔“，”,放入treeSysModuleModalIds
			 */
			StringBuffer moduleStr =  new StringBuffer();
			List<SysModule> listModule = sysModuleService.getSysModuleByRoleId(id);
			if(null != listModule){
				for(SysModule m : listModule){
					moduleStr.append(m.getModuleId()+",");
				}
				bean.setTreeSysModuleModalIds(moduleStr.toString());
			}
		}
	}
}
