package cn._2vin.admin.bean;

import java.util.Date;

import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.common.bean.SearchTimeBean;
import cn._2vin.common.util.DateHelper;
/**
 * 日志实体类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class LogBean  extends SearchTimeBean{
	private String id; //id
	private String title;
	/*不全显示action*/
	private String actionSmall; 
	private String action; 
	private Date crtDate; 

	/**
     * 用于时间查询
     */
	private String firstTime;
    
	private String lastTime;
    
	public LogBean() {
		super();
	}
	public LogBean(String title) {
		super();
		this.title = title;
	}
	public LogBean(String title, String action) {
		super();
		this.title = title;
		this.action = action;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * 30个字符之后的隐藏
	 * @return
	 */
	public String getActionSmall() {
		actionSmall = action;
		if(null != action && action.length() > 30){
			actionSmall = action.substring(0,30)+ConstantAdmin.OMIT_THE_SUFFIX;
		}
		return actionSmall;
	}
	public void setActionSmall(String actionSmall) {
		this.actionSmall = actionSmall;
	}
	public String getCrtDate() {
		return DateHelper.getDateStrByPattern(crtDate, DateHelper.FORMAT_SECOND);
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
}
