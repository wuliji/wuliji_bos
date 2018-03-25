/**
 * 
 */
package com.wuliji.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wuliji.bos.dao.base.IBaseDao;
import com.wuliji.bos.utils.PageBean;

/**
 * 持久层通用实现类
 * @author Administrator
 *
 */
public class IBaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{

	private Class<T> entityClass;
	
	//在父类的构造方法中动态获得entityClass实例
	public IBaseDaoImpl() {
		//获得父类运行时类
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
		
	}
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "from " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		//为HQL语句赋值
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		//执行更新
		query.executeUpdate();
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//查询total---总数据量
		detachedCriteria.setProjection(Projections.rowCount());//指定框架发送sql形式
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count = countList.get(0);
		pageBean.setTotal(count.intValue());
		//查询rows---当前页需要展示的数据集合
		detachedCriteria.setProjection(null);
		int first = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, first, maxResults);
		pageBean.setRows(rows);
	}
	
}
