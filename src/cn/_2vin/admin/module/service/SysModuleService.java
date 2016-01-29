package cn._2vin.admin.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.bean.SysModule;
import cn._2vin.admin.bean.SysRole;
import cn._2vin.admin.module.persistence.SysModuleMapper;
import cn._2vin.common.bean.ActionResult;
import cn._2vin.common.controller.service.CommonService;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
@Service
public class SysModuleService extends CommonService {

	@Autowired
	private SysModuleMapper sysModuleMapper;
	/**
	 * 查看所有顶级模块
	 * @param bean
	 * @return
	 */
	public Object allParentModule(SysModule bean) {
		return sysModuleMapper.allParentModule(bean);
	}
	/**
	 * 根据条件查找出所有系统模块列表
	 * @param bean
	 * @return
	 */
	public List<SysModule> queryList(SysModule bean){
		return sysModuleMapper.queryList(bean);
	}
	/**
	 * 列出系统模块列表
	 * 
	 * @param bean
	 * @return
	 */
	public List<SysModule> selectPageSysModule(SysModule bean) {
		return sysModuleMapper.selectPageSysModule(bean);
	}

	/**
	 * 统计系统模块数目
	 * 
	 * @param bean
	 * @return
	 */
	public int countPageSysModule(SysModule bean) {
		return sysModuleMapper.countPageSysModule(bean);
	}

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysModule queryPrimaryKey(String id) {
		return sysModuleMapper.queryPrimaryKey(id);
	}

	/**
	 * 添加系统模块
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(SysModule bean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("新增系统模块");
		logBean.setAction("新增系统模块信息["+bean.toString()+"]");
		sysModuleMapper.insert(bean);
		insertLog(logBean);
	}
	
	/**
	 * 修改系统模块
	 * @param bean
	 */
	@Transactional(rollbackFor = Exception.class)
	public void update(SysModule bean,SysModule oldBean) {
		/*操作日志类*/
		LogBean logBean = new LogBean(); 
		/**
		 * 插入日志
		 */
		logBean.setTitle("修改系统模块");
		String action = "修改系统模块信息[<b>原始数据：</b>"+oldBean.toString()+"]"+
				"<br/>"+
				"修改系统模块信息[<b>修改数据：</b>"+bean.toString()+"]";
		logBean.setAction(action);
		sysModuleMapper.update(bean);
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
		SysModule bean = null;
		for(String id : idList){
			bean = sysModuleMapper.queryPrimaryKey(id);
			if(null == bean){
				result.setCode(-1);
				throw new Exception();
			}else{
				if(null != bean.getChildren() && bean.getChildren().size() > 0){
					result.setCode(-1);
					throw new Exception();
				}
				/**
				 * 插入日志
				 */
				logBean.setTitle("删除系统模块");
				logBean.setAction("删除系统模块信息["+bean.toString()+"]");
				
				sysModuleMapper.delete(id);
				/**
				 * 删除成功模块权限id，再删除 权限角色中间表的数据
				 */
				deleteSysModuleByRightId(id);
				
				insertLog(logBean);
			}
		}
		return result;
	}
	
	/**
	 * 查询name是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param moduleName
	 * @param moduleId
	 * @return
	 */
	public int selectByNameExcludeId(String moduleName, String moduleId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("moduleName", moduleName);
		map.put("moduleId", moduleId);

		return sysModuleMapper.selectByNameExcludeId(map);
	}
	
	public static List<SysModule> buildModuleTree(List<SysModule> modules) {
		List<SysModule> roots = new ArrayList<SysModule>();

		Map<String, List<SysModule>> map = new HashMap<String, List<SysModule>>();
		if (modules != null && modules.size() > 0) {
			for (SysModule m : modules) {
				String parentModuleId = m.getParentModuleId();
				List<SysModule> children = map.get(parentModuleId);
				if (children == null) {
					children = new ArrayList<SysModule>();
				}
				children.add(m);
				map.put(parentModuleId, children);
				if (parentModuleId == null || "0".equals(parentModuleId)) {
					roots.add(m);
				}
			}
			recursion(roots, map);
		}
		return roots;
	}

	public static void recursion(List<SysModule> childs, Map<String, List<SysModule>> map) {
		for (SysModule m : childs) {
			String parentModuleId = m.getModuleId();
			List<SysModule> children = map.get(parentModuleId);
			if (children == null)
				continue;
			m.setChildren(children);
			recursion(children, map);
		}

	}
	
	/******************************************
	 * 权限角色中间表
	 ******************************************
	 */
	
	/**
	 * 获取权限模块授权
	 * @param roleId
	 * @return
	 */
	public List<SysModule> getSysModuleByRoleId(String roleId){
		return sysModuleMapper.getSysModuleByRoleId(roleId);
	}
	/**
	 * 获取权限模块授权的所有列表（包含父模块，用于管理员登录后的模块显示）
	 * @param roleId
	 * @return
	 */
	public List<SysModule> getAllSysModuleByRoleId(String roleId){
		return sysModuleMapper.getAllSysModuleByRoleId(roleId);
	}
	/**
	 * 批量插入权限角色中间表
	 * @param bean
	 */
	public void batchInsertRoleRightModule(SysRole bean) {
		String roleId= bean.getRoleId();
		String[] rightIds = bean.getTreeSysModuleModalIds().split(",");
		Map<String, String> insertMap = new HashMap<String, String>();
		insertMap.put("roleId", roleId);
		for(String rightId : rightIds){
			insertMap.put("rightId", rightId);
			sysModuleMapper.insertRoleRightModule(insertMap);
		}
	}
	
	/**
	 * 删除权限角色中间表 by roleId
	 * @param roleId
	 */
	public void deleteSysModuleByRoleId(String roleId){
		sysModuleMapper.deleteSysModuleByRoleId(roleId);
	}
	
	/**
	 * 删除权限角色中间表 by rightId
	 * @param rightId
	 */
	public void deleteSysModuleByRightId(String rightId){
		sysModuleMapper.deleteSysModuleByRightId(rightId);
	}
}
