package com.neusoft.duanmlssh.webService.pojo;

import java.io.Serializable;

public class Cat implements Serializable{

	private static final long serialVersionUID = 3926404679630784232L;
	private String name;
	private int age;
	private Tagger tagger;
	
	public Tagger getTagger() {
		return tagger;
	}
	public void setTagger(Tagger tagger) {
		this.tagger = tagger;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
