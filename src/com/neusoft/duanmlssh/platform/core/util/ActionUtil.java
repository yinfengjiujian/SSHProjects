package com.neusoft.duanmlssh.platform.core.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;



@Service
public class ActionUtil {

	private static final PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
	
	public static String getFullIP(String ip) {
		if (StringUtils.countMatches(ip, ".") != 3)
			return ip;
		String[] items = ip.split("\\.");
		for (int i = 0; i < items.length; i++) {
			while (items[i].length() < 3) {
				items[i] = "0" + items[i];
			}
		}
		ip = "";
		for (int i = 0; i < items.length; i++) {
			ip += "." + items[i];
		}
		return ip.substring(1);
	}

	public static String getLocalHost() {
		String localhost = "";
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					localhost = ips.nextElement().getHostAddress();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localhost;
	}

	public static String getPcSize(long size) {
		String sizeStr;
		DecimalFormat format = new DecimalFormat("#0.##");
		if (size > 1024 * 1024 * 1024)
			sizeStr = format.format(((double) size) / 1024 / 1024 / 1024) + "G";
		else if (size > 1024 * 1024)
			sizeStr = format.format(((double) size) / 1024 / 1024) + "M";
		else if (size > 1024)
			sizeStr = format.format(((double) size) / 1024) + "K";
		else
			sizeStr = size + "B";
		return sizeStr;
	}


	public static void writeJsonString(HttpServletResponse response, Object... values) {
		response.setContentType("text/javascript;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write("[");
			if (values != null && values.length > 0)
				for (int i = 0; i < values.length; i++) {
					if (i > 0)
						out.write(",");
					if (values[i] instanceof Number)
						out.write(values[i].toString());
					else
						out.write("'" + values[i] + "'");
				}
			out.write("]");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//	public static void writeJSONPString(HttpServletResponse response, Object... values) {
//		response.setContentType("text/javascript;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
//		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//		response.setHeader("Pragma", "no-cache");
//		try {
//			PrintWriter out = response.getWriter();
//			out.write("");
//			if (values != null && values.length > 0)
//				for (int i = 0; i < values.length; i++) {
//					if (i > 0)
//						out.write(",");
//					if (values[i] instanceof Number)
//						out.write(values[i].toString());
//					else
//						out.write("'" + values[i] + "'");
//				}
//			out.write("");
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void writeJson(HttpServletResponse response, Object... values) {
		response.setContentType("text/javascript;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write("[");
			if (values != null && values.length > 0)
				for (int i = 0; i < values.length; i++) {
					if (i > 0)
						out.write(",");
					if (values[i] instanceof Number)
						out.write(values[i].toString());
					else
						out.write(values[i] + "");
				}
			out.write("]");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSubCode(String str, int step) {
		String tail = "";
		for (int i = 0; i < step; i++)
			tail += "0";
		while (str.endsWith(tail)) {
			str = str.substring(0, str.length() - step);
		}
		return str;

	}

	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("never happend exception!", e);
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("rawtypes")
	protected static Field getDeclaredField(final Class clazz, final String fieldName) {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强制转换fileld可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	public static Object getSimpleProperty(Object bean, String propName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return bean.getClass().getMethod(getReadMethod(propName)).invoke(bean);
	}

	private static String getReadMethod(String name) {
		return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getClientBrowser(String clientHeader) {
		if (StringUtils.isBlank(clientHeader)) {
			return null;
		}
		String[] browserArray = { "360SE", "MSIE 6.0", "MSIE 8.0", "MSIE 9.0", "MSIE 7.0", "Firefox", "Chrome", "TheWorld", "TencentTraveler", "Safari", "Opera" };
		// String[] browserArray = {
		// "360SE", "360EE",
		// "MSIE 6.0", "MSIE 8.0", "MSIE 9.0","MSIE 7.0",
		// "Firefox/8","Firefox/3","Firefox/7","Firefox/5","Firefox/6","Firefox",
		// "Chrome/14","Chrome/6","Chrome/15","Chrome/12","Chrome/7", "Chrome",
		// "TheWorld",
		// "Safari",
		// "TencentTraveler 4", "TencentTraveler",
		// "iPhone"};
		String browser = "其他";
		for (String b : browserArray) {
			if (clientHeader.indexOf(b) != -1) {
				browser = b;
				break;
			}
		}
		return browser;
	}

	/**
	 * 根据JDBC查询结果装配一个Pagination可以使用的List对象
	 * 
	 * @param <T>
	 * @param values
	 * @param properties
	 * @param beanClass
	 * @return
	 */
	public static <T> List<T> populateBean(List<?> values, String[] properties, Class<T> beanClass) {

		List<T> list = new ArrayList<T>();
		try {
			for (Object o : values) {
				T bean = beanClass.newInstance();
				if (o instanceof Object[]) {
					Object[] objs = (Object[]) o;
					for (int i = 0; i < objs.length; i++) {
						if ((objs[i] == null) && (StringUtils.isNotBlank(properties[i])))
							PropertyUtils.setProperty(bean, properties[i], null);
						else
							BeanUtils.setProperty(bean, properties[i], objs[i]);
					}
				} else {
					BeanUtils.setProperty(bean, properties[0], o);
				}
				list.add(bean);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void copyPropertiesIgnalNull(Object orig, Object dest, String[] ignalString) {
		try {
			if (orig != null && dest != null) {
				final PropertyDescriptor origDescriptors[] = propertyUtilsBean.getPropertyDescriptors(dest);
				for (final PropertyDescriptor descriptor : origDescriptors) {
					if (!stringArrayContain(ignalString, descriptor.getName()) && propertyUtilsBean.isReadable(orig, descriptor.getName()) && propertyUtilsBean.isWriteable(dest, descriptor.getName())) {
						final Object orig_value = propertyUtilsBean.getProperty(orig, descriptor.getName());
						final Class<?> dest_type = propertyUtilsBean.getPropertyType(dest, descriptor.getName());
						if (orig_value == null)
							continue;
						if (dest_type == Set.class)
							continue;
						else if ((dest_type == Date.class || dest_type == Timestamp.class) && "".equals(orig_value))
							BeanUtils.copyProperty(dest, descriptor.getName(), null);
						else if (dest_type == java.util.Date.class)
							propertyUtilsBean.setProperty(dest, descriptor.getName(), orig_value);
						else
							BeanUtils.copyProperty(dest, descriptor.getName(), orig_value);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void copyPropertiesIgnalNull(Object orig, Object dest) {
		try {
			if (orig != null && dest != null) {
				final PropertyDescriptor origDescriptors[] = propertyUtilsBean.getPropertyDescriptors(dest);
				for (final PropertyDescriptor descriptor : origDescriptors) {
					if (propertyUtilsBean.isReadable(orig, descriptor.getName()) && propertyUtilsBean.isWriteable(dest, descriptor.getName())) {
						final Object orig_value = propertyUtilsBean.getProperty(orig, descriptor.getName());
						final Class<?> dest_type = propertyUtilsBean.getPropertyType(dest, descriptor.getName());
						if (orig_value == null)
							continue;
						if (dest_type == Set.class)
							continue;
						else if ((dest_type == Date.class || dest_type == Timestamp.class) && "".equals(orig_value))
							BeanUtils.copyProperty(dest, descriptor.getName(), null);
						else if (dest_type == java.util.Date.class)
							propertyUtilsBean.setProperty(dest, descriptor.getName(), orig_value);
						else
							BeanUtils.copyProperty(dest, descriptor.getName(), orig_value);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> PaginationInterImpl copyPagination(PaginationInterImpl orig, Class<T> clazz) {
		if(orig==null){
			PaginationInterImpl emptyPage = new PaginationInterImpl(1, 0, 0);
			emptyPage.setList(new ArrayList<T>());
			return emptyPage;
		}
		int totalCount = orig.getTotalCount();
		PaginationInterImpl p = new PaginationInterImpl(orig.getPageNo(), orig.getPageSize(), totalCount);

		if (totalCount < 1) {
			p.setList(new ArrayList<T>());
			return p;
		}
		List<T> list = new ArrayList<T>();
		List<?> origList = orig.getList();
		for (Object o : origList) {
			try {
				Object d = clazz.newInstance();
				copyPropertiesIgnalNull(o, d);
				list.add((T) d);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		p.setList(list);
		return p;
	}

	public static boolean stringArrayContain(String[] array, String isContain) {
		for (String str : array) {
			if (str != null && str.equals(isContain)) {
				return true;
			}
		}
		return false;
	}

	public static String getUrlId(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int i = uri.lastIndexOf("/");
		if (i != -1 && uri.endsWith(".html")) {
			return uri.substring(i + 1, uri.length() - 5);
		}
		return null;
	}

	/**
	 * 网站页面公共的分页处理方法
	 * 
	 * @param pag
	 * @return
	 */
	public static StringBuilder getWebPage(PaginationInterImpl pag) {
		Integer PageStart;
		Integer PageEnd;
		Integer ipage = 4;
		if (pag.getPageNo() > 10000) {
			ipage = 2;
		}
		if (pag.getPageNo() - ipage <= 1) {
			PageStart = 1;
		} else {
			PageStart = pag.getPageNo() - ipage;
		}
		if (pag.getPageNo() + ipage >= pag.getTotalPage()) {
			PageEnd = pag.getTotalPage();
		} else {
			PageEnd = pag.getPageNo() + ipage;
		}
		StringBuilder str = new StringBuilder();
		for (int i = PageStart; i <= PageEnd; i++) {
			if (i == pag.getPageNo()) {
				str.append("<td   height='50%'>").append("&nbsp;<SPAN class='current' onclick='_gotoPage(").append(i).append(")'  style='cursor: pointer;line-height:20px;'>").append(i).append("</SPAN>").append("</td>");
			} else {
				str.append("<td   height='50%' >").append("&nbsp;<SPAN class='uncheck' onclick='_gotoPage(").append(i).append(")'  style='cursor: pointer;line-height:20px;'>").append(i).append("</SPAN>").append("</td>");
			}
		}
		return str;
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;
	}
}

