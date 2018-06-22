package com.neusoft.duanmlssh.comment;

import java.util.HashMap;
import java.util.Map;

public class MutualObject {

	private Map<String, Object> param = null;
	private boolean resultFlag;

	public MutualObject(){
		this.param = new HashMap<String, Object>();
	}
	
	public void put(String key, Object obj){
		if (param.containsKey(key)) {
			param.remove(key);
		}
		param.put(key, obj);
	}
	
	public Object get(String key){
		return param.get(key);
	}
	
	public boolean getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}
}
