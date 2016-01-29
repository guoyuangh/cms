package cn._2vin.admin.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.log.persistence.LogMapper;
/**
 * 操作日志服务层类
 * @author liuxuewen
 * @date 2014-1-22
 */
@Service
public class LogService {

	@Autowired
	LogMapper mapper;
	/**
	 * 插入日志
	 * @param bean
	 */
	@Transactional(rollbackFor=Exception.class)
	public void insertLog(LogBean bean) {
		mapper.insertLog(bean);
	}
	/**
	 * 列出日志
	 * @param bean
	 * @return
	 */
	public List<LogBean> selectPageLog(LogBean bean) {
		return mapper.selectPageLog(bean);
	}
	/**
	 * 统计日志数目
	 * @param bean
	 * @return
	 */
	public int countPageLog(LogBean bean) {
		return mapper.countPageLog(bean);
	}
}
