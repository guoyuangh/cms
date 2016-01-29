package cn._2vin.admin.sysuser.persistence;

import java.util.List;
import java.util.Map;

import cn._2vin.admin.bean.SysUser;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
public interface SysUserMapper {

	/**
	 * 根据条件查找出所有管理员用户列表
	 * @param bean
	 * @return
	 */
	public List<SysUser> queryList(SysUser bean);
	
	/**
	 * 列出管理员用户列表
	 * @param bean
	 * @return
	 */
	public List<SysUser> selectPageSysUser(SysUser bean);
	/**
	 * 统计管理员用户数目
	 * @param bean
	 * @return
	 */
	public int countPageSysUser(SysUser bean) ;

	/**
	 * id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysUser queryPrimaryKey(String id);
	
	/**
	 * 查询登录名是否存在（id传入时候，为修改，排除id后的结果）；
	 * @param loginName
	 * @param id
	 * @return
	 */
	public int selectByLoginNameExcludeId(Map<String,String> map);
	
	/**
	 * 添加管理员用户
	 * @param bean
	 */
	public void insert(SysUser bean);
	
	/**
	 * 修改管理员用户
	 * @param bean
	 */
	public void update(SysUser bean);

	/**
	 * 删除管理员用户
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 更新登录后的记录
	 * 
	 * @param userId
	 */
	public void updateLoginAfter(SysUser user);
	
	/**
	 * 更新密码
	 * 
	 * @param user
	 */
	public void updatePassWord(SysUser user);
}
