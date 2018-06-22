package com.neusoft.duanmlssh.httpProxy;

import java.util.ArrayList;
import java.util.List;

public class Obj {
	int type = 1 ; //1、文本替换  2、td替换 、3、tr替换 4、table 替换  5 id替换
	String filterStr = "";
	String action = ""; //
	String objStr = "";  
	List<Obj> list = new ArrayList<Obj>();

	public Obj() {
		
	}

	
	public Obj(int type1, String filterStr1, String action1,
			String objStr1) {
		this.type = type1;
		this.filterStr = filterStr1;
		this.action = action1;
		this.objStr = objStr1;

	}

	List<Obj> getList() {
		Obj  obj= new Obj( 1,"林志容", null, "赵洋");
		list.add(obj);	
		Obj  obj2= new Obj( 1,"陈剑新", null, "段美林");
		list.add(obj2);	
		Obj  obj1= new Obj( 1,"GongGaoDetail", null, "赵洋");
		list.add(obj1);	
		return list;
	}

}
