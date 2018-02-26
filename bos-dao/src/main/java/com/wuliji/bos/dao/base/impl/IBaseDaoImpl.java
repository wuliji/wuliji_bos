/**
 * 
 */
package com.wuliji.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wuliji.bos.dao.base.IBaseDao;

/**
 * �־ò�ͨ��ʵ����
 * @author Administrator
 *
 */
public class IBaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{

	private Class<T> entityClass;
	
	//�ڸ���Ĺ��췽���ж�̬���entityClassʵ��
	public IBaseDaoImpl() {
		//��ø�������ʱ��
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø����������ķ�������
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

	
}
