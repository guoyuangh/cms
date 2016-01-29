package cn._2vin.admin.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn._2vin.common.bean.SearchTimeBean;
import cn._2vin.common.util.DateHelper;

/**
 * 管理员用户实体类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class SysUser extends SearchTimeBean {
	private String id;
	private String loginName;
	private String realName;

	private String loginPassword;
	private long loginCount;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Date arcDate;
	private Date crtDate;
	
	/**
	 * 用户权限角色
	 */
	private SysRole sysRole;

	List<SysModule> sysModules = new ArrayList<SysModule>();

	public SysUser() {
		super();
	}

	public SysUser(String loginName, String loginPassword) {
		super();
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(long loginCount) {
		this.loginCount = loginCount;
	}

	public String getLastLoginDate() {
		return DateHelper.getDateStrByPattern(this.lastLoginDate, DateHelper.FORMAT_SECOND);
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public List<SysModule> getSysModules() {
		return sysModules;
	}

	public void setSysModules(List<SysModule> sysModules) {
		this.sysModules = sysModules;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", loginName=" + loginName + ", realName=" + realName + ", loginPassword=" + loginPassword + ", loginCount=" + loginCount
				+ ", lastLoginDate=" + lastLoginDate + ", lastLoginIp=" + lastLoginIp + ", arcDate=" + arcDate + ", crtDate=" + crtDate + ", roleCode="
				+ (null != sysRole?sysRole.getRoleCode():"") + "]";
	}
}
