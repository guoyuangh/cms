package cn._2vin.common.bean;

/**
 * 需要搜索时间的类（firstTime,lastTime）
 * 
 * @author liuxuewen
 * @date 2014-1-22 
 */
public class SearchTimeBean extends AbstractNumberVO {
	protected String firstTime;
	protected String lastTime;

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
