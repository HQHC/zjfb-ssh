package com.zjfb.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.zjfb.model.User;
import com.zjfb.service.UserService;
import com.zjfb.util.JwtUtils;
import com.zjfb.util.MD5Util;
import com.zjfb.util.Result;

@Action(value = "userAction")
public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
	
	public void sign() {
		User user = new User();
		user.setAge(Integer.valueOf(this.getRequest().getParameter("age")));
		user.setName(this.getRequest().getParameter("name"));
		String pwd = this.getRequest().getParameter("password");
		String md5_pwd = MD5Util.md5(pwd);
		user.setPassword(md5_pwd);
		userService.save(user);
		super.writeJson(Result.successResult(true));
	}
	
	
	public void getUser() {
		super.writeJson(Result.successResult(userService.getById(Integer.valueOf(this.getRequest().getParameter("id")))));
	}
	

	public void login() {
		String name = this.getRequest().getParameter("name");
		String password = this.getRequest().getParameter("password");
		String md5_pwd = MD5Util.md5(password);
		User user = userService.getByNameAndPassword(name, md5_pwd);
		if (user != null) {
			String jwt = JwtUtils.createJWT("1", name, 100000000);
			super.writeJson(Result.successResult(jwt));
		}
	}
	
	public void deleteMany() {
		String idList = this.getRequest().getParameter("idList");
		String[] idListArrray = idList.split(",");
		List<Integer> idList2 = new ArrayList<Integer>();
		for (String str : idListArrray) {
			System.out.println(str);
			idList2.add(Integer.valueOf(str));
		}
		
		boolean flag =  userService.deleteMany(idList2);
		super.writeJson(Result.successResult(true));
	}

}
