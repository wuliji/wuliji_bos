package com.wuliji.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * ��ҳ����
 * @author Administrator
 *
 */
public class PageBean {
	private int currentPage;//��ǰҳ��
	private int pageSize;//ÿҳ��ʾ����
	private int total;//�ܼ�¼��
	private List rows;//��ǰ����
	private DetachedCriteria detachedCriteria;//���߲�ѯ
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	
}
