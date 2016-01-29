package cn._2vin.front.info.persistence;

import java.util.List;

import cn._2vin.front.bean.TestBean;
/**
 * 
 * @author liuxuewen
 * @date 2014-1-22
 */
public interface TestMapper {

	public void insertTest(TestBean bean);

	public void updateTest(TestBean bean);

	public void deleteTest(String id);

	public List<TestBean> selectPageTest(TestBean bean);

	public int countPageTest(TestBean bean);

	public TestBean selectPrimaryKey(String id);

}
