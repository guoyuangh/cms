package cn._2vin.front.bean;

import cn._2vin.common.bean.SearchTimeBean;
/**
 * 用于测试的实体类（需要建立数据库表test（id,name））
 * @author liuxuewen
 * @date 2014-1-22
 */
public class TestBean extends SearchTimeBean {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
