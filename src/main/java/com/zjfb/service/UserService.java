package com.zjfb.service;

import java.util.List;

import com.zjfb.model.User;

public interface UserService {
	public boolean save(User user);
	public User getById(int id);
	public User getByNameAndPassword(String name,String password);
	public User getByName(String name);
	public boolean deleteMany(List idList);
}
