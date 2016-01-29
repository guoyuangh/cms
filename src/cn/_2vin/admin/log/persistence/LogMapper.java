package cn._2vin.admin.log.persistence;

import java.util.List;

import cn._2vin.admin.bean.LogBean;

/**
 * 操作日志mybatis映射类
 * @author liuxuewen
 * @date 2014-1-22
 */
public interface LogMapper {
	/**
	 * 插入日志
	 * @param bean
	 */
	public void insertLog(LogBean bean);
	/**
	 * 列出日志
	 * @param bean
	 * @return
	 */
	public List<LogBean> selectPageLog(LogBean bean);
	/**
	 * 统计日志数目
	 * @param bean
	 * @return
	 */
	public int countPageLog(LogBean bean) ;

}
