package com.zjfb.util;

public class Result {
	public static SuccessMap successResult(Object object) {
		return new SuccessMap(1, object);
	}
	
	public static FailMap failResult(String msg) {
		return new FailMap(0,msg);
	}
}

class SuccessMap{
	int code;
	Object data;
	public SuccessMap(int code, Object data) {
		this.code = code;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}

class FailMap{
	int code;
	String msg;
	public FailMap(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
