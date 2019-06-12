package com.zjfb.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

public interface BaseDao<T> {
	
	public Serializable save(T t);
	
	public T get(String hql);
	
	public boolean delete(String hql,List idList);
}
