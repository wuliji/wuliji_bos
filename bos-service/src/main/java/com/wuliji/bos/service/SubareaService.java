package com.wuliji.bos.service;

import java.util.List;

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
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	List<Subarea> findAll();
	
	/**
	 * ��ѯ����δ���������ķ�������
	 * @return
	 */
	List<Subarea> findListNotRelate();
	
}
