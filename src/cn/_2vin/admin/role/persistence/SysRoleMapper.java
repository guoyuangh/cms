package cn._2vin.admin.role.persistence;

import java.util.List;
import java.util.Map;

import cn._2vin.admin.bean.SysRole;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
public interface SysRoleMapper {

	/**
	 * 根据条件查找出所有权限角色列表
	 * @param bean
	 * @return
	 */
	public List<SysRole> queryList(SysRole bean);
	
	/**
	 * 列出权限角色列表
	 * @param bean
	 * @return
	 */
	public List<SysRole> selectPageSysRole(SysRole bean);
	/**
	 * 统计权限角色数目
	 * @param bean
	 * @return
	 */
	public int countPageSysRole(SysRole bean) ;

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysRole queryPrimaryKey(String id);
	
	/**
	 * 查询 权限code 是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param map
	 * @return
	 */
	public int selectByNameExcludeId(Map<String,String> map);
	
	/**
	 * 添加权限角色
	 * @param bean
	 */
	public void insert(SysRole bean);
	
	/**
	 * 修改权限角色
	 * @param bean
	 */
	public void update(SysRole bean);

	/**
	 * 删除权限角色
	 * @param id
	 */
	public void delete(String id);
	/**
	 * 获取表中的最大值，新的id为最大值+1
	 * @return
	 */
	public int getMaxRoleIdAdd1();
}
