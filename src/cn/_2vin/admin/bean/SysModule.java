package cn._2vin.admin.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.common.bean.SearchTimeBean;
import cn._2vin.common.util.DateHelper;

/**
 * 系统模块实体类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class SysModule extends SearchTimeBean{

	/**
	 * 模块ID
	 */
	private String moduleId;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 模块URL
	 */
	private String moduleUrl;
	/**
	 * A targetString
	 */
	private String targetString;
	/**
	 * 模块树编码
	 */
	private String treeCode;
	/**
	 * 权限编码
	 */
	private String rightCode;
	
	/**
	 * 显示序号
	 */
	private int seqNum;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 修改时间
	 */
	private Date arcDate;

	/**
	 * 创建时间
	 */
	private Date crtDate;

	
	/**
	 * 父模块
	 */
	private SysModule parentSysModule;
	private String parentModuleId;
	
	/**
	 * 子模块
	 */
	private List<SysModule> children = new ArrayList<SysModule>();

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleUrl() {
		if(null == moduleUrl){
			moduleUrl = ConstantAdmin.DEFAULT_MODULE_URL;
		}
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getTargetString() {
		if(null == targetString){
			targetString = "";
		}
		return targetString;
	}

	public void setTargetString(String targetString) {
		this.targetString = targetString;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public SysModule getParentSysModule() {
		return parentSysModule;
	}

	public void setParentSysModule(SysModule parentSysModule) {
		this.parentSysModule = parentSysModule;
	}

	public List<SysModule> getChildren() {
		return children;
	}

	public void setChildren(List<SysModule> children) {
		this.children = children;
	}

	public String getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
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

	@Override
	public String toString() {
		return "SysModule [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleUrl=" + moduleUrl + ", targetString=" + targetString + ", treeCode=" + treeCode
				+ ", rightCode=" + rightCode + ", seqNum=" + seqNum + ", remark=" + remark + ", arcDate=" + arcDate + ", crtDate=" + crtDate
				+ ", parentModuleId=" + parentModuleId + "]";
	}
}
