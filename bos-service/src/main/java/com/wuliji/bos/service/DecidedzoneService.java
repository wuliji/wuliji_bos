package com.wuliji.bos.service;

import com.wuliji.bos.entity.Decidedzone;
import com.wuliji.bos.utils.PageBean;

public interface DecidedzoneService {
	
	/**
	 * ��Ӷ��� 
	 * @param model
	 * @param subareaid
	 */
	void save(Decidedzone model, String[] subareaid);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
}
