package com.neusoft.duanmlssh.study.javaToServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 
* <p>Title: 河源数据中心</p>
* <p>Description: JAVA 类如何调用 Web Servlet的请求，请参考</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 东软集团（广州）有限公司</p>
* <p>Department: 社保研发开发部</p>
* @author duanml
* @date   Mar 13, 2015
* @version 1.0
 */
public class TestYfwsjServlet {
	
	private static final String path = "http://127.0.0.1:8060/yfwsj/YfwsjServlet?module=1&ssqx=55300";

	public static void main(String[] args) {
		try {
            URL url = new URL(path);
            InputStream in = url.openStream();
            ObjectInputStream ooin = new ObjectInputStream(in);
            try {
				@SuppressWarnings("rawtypes")
				List list = (List) ooin.readObject();
				for (int i = 0; i < list.size(); i++) {
					Jcdacx jcda = (Jcdacx) list.get(i);
					System.out.println(jcda.getOldName());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

     } catch (MalformedURLException e) {

            e.printStackTrace();

     } catch (IOException e) {

            e.printStackTrace();

     }
		
	}
}
