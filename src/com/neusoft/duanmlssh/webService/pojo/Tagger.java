package com.neusoft.duanmlssh.webService.pojo;

import java.io.Serializable;

public class Tagger implements Serializable{

	
	private static final long serialVersionUID = 4496707511317157744L;
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age;
}
