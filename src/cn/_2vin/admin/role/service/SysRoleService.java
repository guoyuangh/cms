package cn._2vin.admin.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.bean.SysRole;
import cn._2vin.admin.module.service.SysModuleService;
import cn._2vin.admin.role.persistence.SysRoleMapper;
import cn._2vin.common.bean.ActionResult;
import cn._2vin.common.controller.service.CommonService;
import cn._2vin.common.util.StringHelper;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
@Service
public class SysRoleService extends CommonService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysModuleService sysModuleService;
	/**
	 * 根据条件查找出所有权限角色列表
	 * @param bean
	 * @return
	 */
	public List<SysRole> queryList(SysRole bean){
		return sysRoleMapper.queryList(bean);
	}
	/**
	 * 列出权限角色列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<SysRole> selectPageSysRole(SysRole bean) {
		return sysRoleMapper.selectPageSysRole(bean);
	}

	/**
	 * 统计权限角色数目
	 * 
	 * @param bean
	 * @return
	 */
	public int countPageSysRole(SysRole bean) {
		return sysRoleMapper.countPageSysRole(bean);
	}

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysRole queryPrimaryKey(String id) {
		return sysRoleMapper.queryPrimaryKey(id);
	}

	/**
	 * 添加权限角色，添加之后插入权限角色中间表
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(SysRole bean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("新增权限角色");
		logBean.setAction("新增权限角色信息["+bean.toString()+"]");
		/*获取表中的最大值，新的id为最大值+1*/		
		int roleId = sysRoleMapper.getMaxRoleIdAdd1();
		bean.setRoleId(String.valueOf(roleId));
		
		sysRoleMapper.insert(bean);
		
		if(!StringHelper.isNull(bean.getTreeSysModuleModalIds())){
			/**
			 * 批量插入权限角色中间表插入权限角色中间表
			 */
			sysModuleService.batchInsertRoleRightModule(bean);
		}
		insertLog(logBean);
	}
	
	/**
	 * 修改权限角色
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRole bean,SysRole oldBean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("修改权限角色");
		String action = "修改权限角色信息[<b>原始数据：</b>"+oldBean.toString()+"]"+
				"<br/>"+
				"修改权限角色信息[<b>修改数据：</b>"+bean.toString()+"]";
		logBean.setAction(action);
		sysRoleMapper.update(bean);
		/**
		 * 先删除role对应的权限角色中间表,再重新插入
		 */
		sysModuleService.deleteSysModuleByRoleId(bean.getRoleId());
		if(!StringHelper.isNull(bean.getTreeSysModuleModalIds())){
			/**
			 * 批量插入权限角色中间表插入权限角色中间表
			 */
			sysModuleService.batchInsertRoleRightModule(bean);
		}
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
		SysRole bean = null;
		for(String id : idList){
			bean = sysRoleMapper.queryPrimaryKey(id);
			if(null == bean){
				result.setCode(-1);
				throw new Exception();
			}else{
				/**
				 * 插入日志
				 */
				logBean.setTitle("删除权限角色");
				logBean.setAction("删除权限角色信息["+bean.toString()+"]");
				
				sysRoleMapper.delete(id);
				
				insertLog(logBean);
			}
		}
		return result;
	}
	
	/**
	 * 查询权限code是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param roleCode
	 * @param roleId
	 * @return
	 */
	public int selectByNameExcludeId(String roleCode, String roleId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("roleCode", roleCode);
		map.put("roleId", roleId);

		return sysRoleMapper.selectByNameExcludeId(map);
	}
}
