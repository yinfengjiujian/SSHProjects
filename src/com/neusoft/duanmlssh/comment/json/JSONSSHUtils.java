package com.neusoft.duanmlssh.comment.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.proxy.LazyInitializer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class JSONSSHUtils {
	
	public static HashMap<String, Object> getAMap() {
		return new HashMap<String, Object>();
	}
	
	public static void write(final HttpServletResponse response, final Object mapOrArray,final boolean status) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			final PrintWriter w = response.getWriter();
			if (mapOrArray == null) {
				w.print("{\"ajaxResult\":false}");
			} else if (mapOrArray instanceof Map) {
				String returnStr = JSONObject.fromObject(mapOrArray).toString();
				returnStr = returnStr.substring(0, returnStr.length()-1);
				if (status) {
					returnStr += ",\"ajaxResult\":true}";
				} else {
					returnStr += ",\"ajaxResult\":false}";
				}
				w.print(returnStr);
			} else if (mapOrArray instanceof List) {
				String returnStr = JSONArray.fromObject(mapOrArray).toString();
				if (status) {
					returnStr = "{\"ajaxResult\":\"true\",\"listRows\":" + returnStr + "}";
				} else {
					returnStr = "{\"ajaxResult\":\"false\",\"listRows\":" + returnStr + "}";
				}
				w.print(returnStr);
			} else if (mapOrArray instanceof Object) {
				String returnStr = JSONObject.fromObject(mapOrArray).toString();
				returnStr = returnStr.substring(0, returnStr.length()-1);
				if (status) {
					returnStr += ",\"ajaxResult\":true}";
				} else {
					returnStr += ",\"ajaxResult\":false}";
				}
				w.print(returnStr);
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeDWZ(final HttpServletResponse response, final Object mapOrArray,final boolean status) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			final PrintWriter w = response.getWriter();
			if (mapOrArray == null) {
				w.print("{\"ajaxResult\":false}");
			} else if (mapOrArray instanceof Map) {
				String returnStr = JSONObject.fromObject(mapOrArray).toString();
				returnStr = returnStr.substring(0, returnStr.length()-1);
				if (status) {
					returnStr += ",\"ajaxResult\":true}";
				} else {
					returnStr += ",\"ajaxResult\":false}";
				}
				w.print(returnStr);
			} else if (mapOrArray instanceof List) {
				String returnStr = JSONArray.fromObject(mapOrArray).toString();
				if (status) {
					returnStr = "{\"ajaxResult\":\"true\",\"listRows\":" + returnStr + "}";
				} else {
					returnStr = "{\"ajaxResult\":\"false\",\"listRows\":" + returnStr + "}";
				}
				w.print(returnStr);
			} else if (mapOrArray instanceof Object) {
				String returnStr = JSONObject.fromObject(mapOrArray).toString();
				returnStr = returnStr.substring(0, returnStr.length()-1);
				if (status) {
					returnStr += ",\"ajaxResult\":true}";
				} else {
					returnStr += ",\"ajaxResult\":false}";
				}
				w.print(returnStr);
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writejson(final HttpServletResponse response, final String json,final boolean status){
		response.setContentType("text/html;charset=UTF-8");
		try {
			final PrintWriter w = response.getWriter();
			w.print(json);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String coverToJson(Object object) {
		if (object == null) {
			return null;
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		config.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (source instanceof LazyInitializer || value instanceof LazyInitializer)
					return true;
				if (!Hibernate.isInitialized(source) || !Hibernate.isInitialized(value))
					return true;
				return false;
			}
		});
		if (object instanceof List) {
			return JSONArray.fromObject(object, config).toString();
		} else {
			return JSONObject.fromObject(object, config).toString();
		}
	}
	
	public static JSONObject ObjectToJSONObject(Object object) {
		if (object == null) {
			return null;
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (source instanceof LazyInitializer || value instanceof LazyInitializer)
					return true;
				if (!Hibernate.isInitialized(source) || !Hibernate.isInitialized(value))
					return true;
				return false;
			}
		});
		if (object instanceof List) {
			return null;
		} else {
			return JSONObject.fromObject(object, config);
		}
	}
	
	private static class DateJsonValueProcessor implements JsonValueProcessor {
		public static final String Default_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
		private DateFormat dateFormat;
		public DateJsonValueProcessor(String datePattern) {
			try {
				dateFormat = new SimpleDateFormat(datePattern);
			} catch (Exception e) {
				dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
			}
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		private Object process(Object value) {
			if (value == null)
				return null;
			return dateFormat.format((java.util.Date) value);

		}
	}
	
	public static void main(String[] args){
		String strJson = "{header:{\"code\": 0,\"message\":{\"title\": \"\",\"detail\": \"\"}},body: {parameters:{},dataStores:{},reset:[]}}";
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		jsonObj.getJSONObject("body").getJSONObject("parameters").put("duanml", "duanmlKey");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mapname", "mapvalue");
		jsonObj.getJSONObject("body").getJSONArray("reset").add(map);
		System.out.println(jsonObj.toString());
	}

}
