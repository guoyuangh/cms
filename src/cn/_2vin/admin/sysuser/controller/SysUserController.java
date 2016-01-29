package cn._2vin.admin.sysuser.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.bean.SysRole;
import cn._2vin.admin.bean.SysUser;
import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.admin.role.service.SysRoleService;
import cn._2vin.admin.sysuser.service.SysUserService;
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
@RequestMapping("/admin/sysuser/*")
public class SysUserController extends AdminController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 系统管理列表
	 * 
	 * @return
	 * @throws NotRoleRightException 
	 */
	@RequestMapping("sysuserList.do")
	public ModelAndView list(SysUser bean) throws NotRoleRightException {
		ModelAndView mnv = new ModelAndView();
		/**
		 * 设置导航名称
		 */
		setNavTitle(bean,mnv);
		
		mnv.addObject(DEFAULT_LIST_KEY,sysUserService.selectPageSysUser(bean));
		bean.getPager().setTotalSize(sysUserService.countPageSysUser(bean));
		mnv.addObject(PAGE_KEY,bean.getPager());
		mnv.addObject(BEAN_KEY,bean);
		mnv.addObject("sysRoleList",sysRoleService.queryList(null));
		mnv.setViewName("/admin/sys/sysuser/sysuserList");
		return mnv;
	}
	
	/**
	 * 添加或者修改form
	 * @param id
	 * @return
	 */
	@RequestMapping("sysuserForm.do")
	public ModelAndView sysuserForm(String id) {
		ModelAndView mav = new ModelAndView();
		SysUser bean = new SysUser();
		if(!StringHelper.isNull(id)){
			bean = sysUserService.queryPrimaryKey(id);
		}
		mav.addObject(BEAN_KEY,bean);
		
		mav.addObject("sysRoleList",sysRoleService.queryList(null));

		mav.setViewName("/admin/sys/sysuser/sysuserForm");
		return mav;
	}
	
	@RequestMapping("insertOrUpdate.do")
	@ResponseBody
	public ActionResult insertOrUpdate(SysUser bean,HttpSession session) {
		ActionResult result = new ActionResult();
		try {
			if(StringHelper.isNull(bean.getId())){
				/**
				 * 防止权限角色为空
				 */
				if(null == bean.getSysRole()){
					bean.setSysRole(new SysRole());
				}
				sysUserService.insert(bean);
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
				result.setMessage(OPERATOR_RESULT_SUCCESS_ADD_VALUE);
			}else{
				SysUser isAadminUser = sysUserService.queryPrimaryKey(bean.getId());
				/**
				 * 修改的时候，首先，超级管理员 admin 不能修改；然后再进行判断，当前登录用户是否为超级管理员，是就可以修改
				 */
				if(!ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(isAadminUser.getLoginName())){
					sysUserService.update(bean,isAadminUser);
					result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
					result.setMessage(OPERATOR_RESULT_SUCCESS_UPDATE_VALUE);
				}else{
					SysUser loginUser = (SysUser)session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
					if(ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(loginUser.getLoginName())){
						/*如果超级管理员名称改了，提示不能修改*/
						if(!ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(bean.getLoginName())){
							result.setResult(OPERATOR_RESULT_ERROR_KEY);
							result.setMessage("超级管理员用户登录名["+ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR+"]不能修改！");
						}else{
							sysUserService.update(bean,isAadminUser);
							result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
							result.setMessage(OPERATOR_RESULT_SUCCESS_UPDATE_VALUE);
						}
					}else{
						result.setResult(OPERATOR_RESULT_ERROR_KEY);
						result.setMessage("您不是超级管理员用户！不能修改超级管理员！");
					}
				}
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
				result = sysUserService.batchDelete(ids);
				if(result.getCode()>=0){
					result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
					result.setMessage(OPERATOR_RESULT_SUCCESS_DELETE_VALUE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResult(OPERATOR_RESULT_ERROR_KEY);
				result.setMessage("可能原因是：“id不存在”或者“超级管理员["+ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR+"]不能删除”！");
			}
		return result;
	}
	
	/**
	 * 新增或修改时验证是否已经存在相同登陆用户,传入id，为修改
	 * @param 
	 * @return PageResultBean对象
	 */
	@RequestMapping("checkLoginName.do")
	public @ResponseBody ActionResult checkLoginName(String loginName,String id) {
		ActionResult result = new ActionResult();
		try {
			int count = sysUserService.selectByLoginNameExcludeId(loginName,id);
			if(count == 0){
				result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
			}else{
				result.setResult(OPERATOR_RESULT_FAIL_KEY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(OPERATOR_RESULT_ERROR_KEY);
		}
		return result;
	}
	
	/**
	 * 初始化修改密码界面
	 * @return
	 */
	@RequestMapping("initPaw.do")
	public ModelAndView init(){
		ModelAndView mnv = new ModelAndView();
		mnv.addObject(NAV_TITLE_KEY,"修改密码");
		mnv.setViewName("/admin/sys/sysuser/updatePaw");
		return mnv;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("savePaw.do")
	@ResponseBody
	public Map<String,Object> savePaw(String oldPassword,String newPassword,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();

		ActionResult result = null;
		try {
			if(!StringHelper.isNull(oldPassword) && !StringHelper.isNull(newPassword)){
				SysUser user = (SysUser)session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
				 result = sysUserService.updatePassWord(user,oldPassword,newPassword);
				if(result.isSuccess()){
					/**
					 * 清除session key
					 */
					session.removeAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
					result.setResult(OPERATOR_RESULT_SUCCESS_KEY);
				}else{
					result.setResult(OPERATOR_RESULT_FAIL_KEY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(OPERATOR_RESULT_ERROR_KEY);
		}finally{
			map.put(OPERATOR_RESULT_KEY, result.getResult());
			map.put(OPERATOR_MESSAGE_KEY,result.getMessage());
		}
		return map;
	}
}
