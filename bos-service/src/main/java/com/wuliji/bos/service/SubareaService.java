package com.wuliji.bos.service;

import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.utils.PageBean;

public interface SubareaService {
	
	/**
	 * �������
	 * @param model
	 */
	void save(Subarea model);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
}
