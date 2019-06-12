package com.zjfb.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zjfb.model.User;
import com.zjfb.service.UserService;
import com.zjfb.util.JwtUtils;
import com.zjfb.util.Result;

public class JWTInterceptor extends AbstractInterceptor{

	@Autowired
	UserService userService;
	
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "token");
		if (request.getRequestURI().indexOf("login") != -1) {
			return invocation.invoke();
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					try {
						String name = JwtUtils.parseJWT(cookie.getValue());
						User user = userService.getByName(name);
						if (user != null) {
							return invocation.invoke();
						} else {
							writeToJson(Result.failResult("token不正确"),response);
						}
					} catch (Exception e) {
						// TODO: handle exception
						writeToJson(Result.failResult("token不正确"),response);
					}
					
				}
			}
		}
		writeToJson(Result.failResult("token不存在"),response);
		return null;
		
	}
	
	private void writeToJson(Object object,HttpServletResponse response) throws IOException {
		String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
