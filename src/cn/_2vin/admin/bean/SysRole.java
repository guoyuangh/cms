package cn._2vin.admin.bean;

import java.util.Date;

import cn._2vin.common.bean.SearchTimeBean;
import cn._2vin.common.util.DateHelper;
/**
 * 权限角色实体类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class SysRole extends SearchTimeBean {
	private String roleId;
	private String roleCode;
	private String roleName;
	private String remark;
	private Date arcDate;
	private Date crtDate;
	/**
	 * 权限授权模块id列表
	 */
	private String treeSysModuleModalIds;
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getArcDate() {
		return DateHelper.getDateStrByPattern(this.arcDate, DateHelper.FORMAT_SECOND);
	}

	public void setArcDate(Date arcDate) {
		this.arcDate = arcDate;
	}

	public String getCrtDate() {
		return DateHelper.getDateStrByPattern(this.crtDate, DateHelper.FORMAT_SECOND);
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getTreeSysModuleModalIds() {
		return treeSysModuleModalIds;
	}

	public void setTreeSysModuleModalIds(String treeSysModuleModalIds) {
		this.treeSysModuleModalIds = treeSysModuleModalIds;
	}

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleCode=" + roleCode + ", roleName=" + roleName + ", remark=" + remark + ", treeSysModuleModalIds="+treeSysModuleModalIds+", arcDate=" + arcDate + ", crtDate="
				+ crtDate + "]";
	}
}
