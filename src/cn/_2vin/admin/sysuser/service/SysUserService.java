package cn._2vin.admin.sysuser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.bean.SysModule;
import cn._2vin.admin.bean.SysUser;
import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.admin.module.service.SysModuleService;
import cn._2vin.admin.sysuser.persistence.SysUserMapper;
import cn._2vin.common.bean.ActionResult;
import cn._2vin.common.controller.service.CommonService;
import cn._2vin.common.util.StringEncrypt;
import cn._2vin.common.util.StringHelper;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
@Service
public class SysUserService extends CommonService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysModuleService sysModuleService;
	/**
	 * 根据条件查找出所有管理员用户列表
	 * @param bean
	 * @return
	 */
	public List<SysUser> queryList(SysUser bean){
		return sysUserMapper.queryList(bean);
	}
	/**
	 * 列出管理员用户列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<SysUser> selectPageSysUser(SysUser bean) {
		return sysUserMapper.selectPageSysUser(bean);
	}

	/**
	 * 统计管理员用户数目
	 * 
	 * @param bean
	 * @return
	 */
	public int countPageSysUser(SysUser bean) {
		return sysUserMapper.countPageSysUser(bean);
	}

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysUser queryPrimaryKey(String id) {
		return sysUserMapper.queryPrimaryKey(id);
	}

	/**
	 * 用户登录
	 * 
	 * @param v_loginName
	 * @param v_password
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public SysUser login(SysUser user) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("管理员登录");
		List<SysUser> listByLoginName = queryList(user);
		SysUser returnUser = null;
		if (listByLoginName.size() > 0) {
			returnUser = listByLoginName.get(0);
			String password = user.getLoginPassword();
			String encryptPassword = returnUser.getLoginPassword();
			/**
			 * 判断密码是否正确,不正确就返回null
			 */
			if (!StringEncrypt.verifyPassword(password, encryptPassword)) {
				returnUser = null;
				logBean.setAction("用户密码错误！");
			} else {
				logBean.setAction("管理员[" + user.getLoginName() + "]登录成功！");

			}
		} else {
			logBean.setAction("用户名不存在！");
		}
		insertLog(logBean);
		return returnUser;
	}

	/**
	 * 用户退出
	 * 
	 * @param v_loginName
	 * @param v_password
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public void logout(SysUser user) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("管理员退出");
		logBean.setAction("管理员[" + user.getLoginName() + "]手动退出！");
		insertLog(logBean);
	}

	/**
	 * 更新登录后的记录
	 * 
	 * @param userId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateLoginAfter(SysUser user) {
		sysUserMapper.updateLoginAfter(user);
	}
	
	/**
	 * 添加管理员用户
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(SysUser bean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("新增管理员");
		logBean.setAction("新增管理员信息["+bean.toString()+"]");
		/**
		 * 空，使用默认密码
		 */
		if(StringHelper.isNull(bean.getLoginPassword())){
			bean.setLoginPassword(ConstantAdmin.DEFALUT_ADMIN_LOGIN_PASSWORD);
		}
		bean.setLoginPassword(StringEncrypt.EncryptBySHA_256(bean.getLoginPassword()));
		bean.setLoginPassword(StringEncrypt.EncryptBySHA_256(bean.getLoginPassword()));
		sysUserMapper.insert(bean);
		insertLog(logBean);
	}
	
	/**
	 * 修改管理员用户
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUser bean,SysUser oldBean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 不为空，才更改原始密码
		 */
		if(!StringHelper.isNull(bean.getLoginPassword())){
			bean.setLoginPassword(StringEncrypt.EncryptBySHA_256(bean.getLoginPassword()));
		}else{
			oldBean.setLoginPassword(bean.getLoginPassword());
		}
		/**
		 * 插入日志
		 */
		logBean.setTitle("修改管理员");
		String action = "修改管理员信息[<b>原始数据：</b>"+oldBean.toString()+"]"+
				"<br/>"+
				"修改管理员信息[<b>修改数据：</b>"+bean.toString()+"]";
		logBean.setAction(action);
		sysUserMapper.update(bean);
		insertLog(logBean);
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return ActionResult 返回的code = 0，正常，code < 0，异常
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = Exception.class)
	public ActionResult batchDelete(String ids) throws Exception {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		ActionResult result = new ActionResult();
		result.setCode(0);
		String[] idList = ids.split(",");
		SysUser user = null;
		for(String id : idList){
			user = sysUserMapper.queryPrimaryKey(id);
			if(null == user){
				result.setCode(-1);
				throw new Exception();
			}else{
				if(ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(user.getLoginName())){
					result.setCode(-2);
					throw new Exception();
				}
				/**
				 * 插入日志
				 */
				logBean.setTitle("删除管理员");
				logBean.setAction("删除管理员信息["+user.toString()+"]");
				
				sysUserMapper.delete(id);
				
				insertLog(logBean);
			}
		}
		return result;
	}
	
	/**
	 * 更新密码
	 * @param user
	 * @param password
	 * @param newPassword
	 */

	@Transactional(rollbackFor=Exception.class)
	public ActionResult updatePassWord(SysUser user,String password,String newPassword){
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		ActionResult result = new ActionResult();
		/**
		 * 插入日志
		 */
		logBean.setTitle("管理员修改密码");
		logBean.setAction("管理员["+user.getLoginName()+"]修改密码失败！");
		if(null != user && null != user.getId()){
			user = sysUserMapper.queryPrimaryKey(user.getId());
			if(StringEncrypt.verifyPassword(password, user.getLoginPassword())){
				user.setLoginPassword(StringEncrypt.EncryptBySHA_256(newPassword));
				sysUserMapper.updatePassWord(user);
				logBean.setAction("管理员["+user.getLoginName()+"]修改密码成功！");
				result.setMessage("管理员["+user.getLoginName()+"]修改密码成功！请重新登录！");
				result.setSuccess(true);
			}else{
				logBean.setAction("管理员["+user.getLoginName()+"]修改密码失败！原始密码错误！");
				result.setCode(1);/*原始密码错误*/
				result.setMessage("原始密码错误！");
			}
		}
		insertLog(logBean);
		return result;
	}
	/**
	 * 查询登录名是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param loginName
	 * @param id
	 * @return
	 */
	public int selectByLoginNameExcludeId(String loginName, String id) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("loginName", loginName);
		map.put("id", id);

		return sysUserMapper.selectByLoginNameExcludeId(map);
	}
	public List<SysModule> getSysModules(SysUser user) {
		List<SysModule> list = null;
		/**
		 * 默认超级管理员用户ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR，可以查看所有
		 */
		if(ConstantAdmin.DEFAULT_SUPER_ADMINISTRATOR.equals(user.getLoginName())){
			list = sysModuleService.queryList(null);
		}else{
			if(null != user.getSysRole() && null != user.getSysRole().getRoleId()){
				list = sysModuleService.getAllSysModuleByRoleId(user.getSysRole().getRoleId());
			}
		}
		return SysModuleService.buildModuleTree(list);
	}
}
