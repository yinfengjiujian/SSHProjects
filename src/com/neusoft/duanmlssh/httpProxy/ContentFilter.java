package com.neusoft.duanmlssh.httpProxy;

import java.util.ArrayList;
import java.util.List;

public class ContentFilter {
	List<Obj> objlist = new ArrayList<Obj>();

	String getCharset(String text) {
		String charset = "";
		if (text.toLowerCase().indexOf("charset=") > 0) {
			charset = text.substring(
					text.toLowerCase().indexOf("charset="),
					text.toLowerCase().indexOf("\n",text.toLowerCase().indexOf("charset=") - 1));
		}
		return charset.replace("\r", "");

	}

	String filter(String text) {
		this.init();
		String str = text;
		for (int i = 0; i < objlist.size(); i++) {
			if (str.indexOf(objlist.get(i).filterStr) > 0) {
				switch (objlist.get(i).type) {
				case 1:
					str = str.replaceAll(objlist.get(i).filterStr, objlist.get(i).objStr);
					break;
				case 2:
					break;
				}
			}
		}
		return str;
	}

	void init() {
		Obj obj = new Obj();
		objlist = obj.getList();

	}

}
