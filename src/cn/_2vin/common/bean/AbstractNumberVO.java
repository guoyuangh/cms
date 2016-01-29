package cn._2vin.common.bean;

/**
 * 分页类[分页查询时用到的范围]
 * @author liuxuewen
 * @date 2014-1-22
 */

public abstract class AbstractNumberVO {
	private Pager pager;
	
	private String navTitle;

	public Pager getPager() {
		if(null == this.pager){
			this.pager = new Pager();
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getNavTitle() {
		return navTitle;
	}

	public void setNavTitle(String navTitle) {
		this.navTitle = navTitle;
	}

}
