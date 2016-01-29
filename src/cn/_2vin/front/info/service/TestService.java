package cn._2vin.front.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn._2vin.front.bean.TestBean;
import cn._2vin.front.info.persistence.TestMapper;
/**
 * @author liuxuewen
 *  @date 2014-1-22
 */
@Service
public class TestService {
	
	@Autowired
	private TestMapper testMapper;


	public void insertTest(TestBean bean){
		testMapper.insertTest(bean);
	}

	public void updateTest(TestBean bean){
		testMapper.updateTest(bean);
	}

	public void deleteTest(String ids){
		String[] idList = ids.split(",");
		for(String id:idList){
			testMapper.deleteTest(id);
		}
	}

	public List<TestBean> selectPageTest(TestBean test) {
		return testMapper.selectPageTest(test);
	}
	
	public int countPageTest(TestBean bean){
		return testMapper.countPageTest(bean);
	}

	public TestBean selectPrimaryKey(String id){
		return testMapper.selectPrimaryKey(id);
	}

}
