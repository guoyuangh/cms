package cn._2vin.common.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn._2vin.admin.bean.LogBean;
import cn._2vin.admin.log.service.LogService;

/**
 * 服务层基类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class CommonService {
	@Autowired
	protected LogService logService;
	/**
	 * 通用操作日志插入
	 * @param logBean
	 */
	@Transactional(rollbackFor=Exception.class)
	protected void insertLog(LogBean logBean){
		logService.insertLog(logBean);
	}
}
