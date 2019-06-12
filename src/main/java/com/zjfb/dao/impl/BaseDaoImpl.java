package com.zjfb.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zjfb.dao.BaseDao;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T t) {
		// TODO Auto-generated method stub
		return getSession().save(t);
		
	}

	public T get(String hql) {
		Query q = this.getSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public boolean delete(String hql,List idList) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
