package com.wuliji.bos.service;

import com.wuliji.bos.entity.Decidedzone;
import com.wuliji.bos.utils.PageBean;

public interface DecidedzoneService {
	
	/**
	 * 添加定区 
	 * @param model
	 * @param subareaid
	 */
	void save(Decidedzone model, String[] subareaid);
	
	/**
	 * 分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
}
