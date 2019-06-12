package com.zjfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjfb.dao.BaseDao;
import com.zjfb.model.User;
import com.zjfb.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private BaseDao<User> userDao;

	

	public User getById(int id) {
		// TODO Auto-generated method stub
		String hql = "from User where user_id = " + id;
		return userDao.get(hql);
	}



	public boolean save(User user) {
		// TODO Auto-generated method stub
		System.out.println(userDao.save(user));
		return false;
	}



	public User getByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		String hql = "from User where name = '" + name + "' and password = '" + password + "'";
		User user = userDao.get(hql);
		if (user != null) {
			return user;
		}
		return null;
	}



	public User getByName(String name) {
		// TODO Auto-generated method stub
		String hql = "from User where name = '" + name + "'";
		User user = userDao.get(hql);
		return user;
	}



	public boolean deleteMany(List idList) {
		// TODO Auto-generated method stub
		String hql = "delete from User where user_id in (:idList)";
		boolean flag = userDao.delete(hql, idList);
		return flag;
	}



}
