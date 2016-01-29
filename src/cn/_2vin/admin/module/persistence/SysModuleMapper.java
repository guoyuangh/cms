package cn._2vin.admin.module.persistence;

import java.util.List;
import java.util.Map;

import cn._2vin.admin.bean.SysModule;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
public interface SysModuleMapper {

	/**
	 * 查看所有顶级模块
	 * @param bean
	 * @return
	 */
	public List<SysModule> allParentModule(SysModule bean);
	/**
	 * 根据条件查找出所有系统模块列表
	 * @param bean
	 * @return
	 */
	public List<SysModule> queryList(SysModule bean);
	
	/**
	 * 列出系统模块列表
	 * @param bean
	 * @return
	 */
	public List<SysModule> selectPageSysModule(SysModule bean);
	/**
	 * 统计系统模块数目
	 * @param bean
	 * @return
	 */
	public int countPageSysModule(SysModule bean) ;

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysModule queryPrimaryKey(String id);
	
	/**
	 * 查询 权限Name 是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param map
	 * @return
	 */
	public int selectByNameExcludeId(Map<String,String> map);
	
	/**
	 * 添加系统模块
	 * @param bean
	 */
	public void insert(SysModule bean);
	
	/**
	 * 修改系统模块
	 * @param bean
	 */
	public void update(SysModule bean);

	/**
	 * 删除系统模块
	 * @param id
	 */
	public void delete(String id);
	
	/******************************************
	 * 权限角色中间表
	 ******************************************
	 */
	/**
	 * 获取权限模块授权
	 * @param roleId
	 * @return
	 */
	public List<SysModule> getSysModuleByRoleId(String roleId);
	
	/**
	 * 获取权限模块授权的所有列表（包含父模块，用于管理员登录后的模块显示）
	 * @param roleId
	 * @return
	 */
	public List<SysModule> getAllSysModuleByRoleId(String roleId);
	
	/**
	 * 插入权限角色中间表
	 * @param insertMap
	 */
	public void insertRoleRightModule(Map<String, String> map);
	
	/**
	 * 删除权限角色中间表 by roleId
	 * @param roleId
	 */
	public void deleteSysModuleByRoleId(String roleId);
	
	/**
	 * 删除权限角色中间表 by rightId
	 * @param rightId
	 */
	public void deleteSysModuleByRightId(String rightId);
	
}
