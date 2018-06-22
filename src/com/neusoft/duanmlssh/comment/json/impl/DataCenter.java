package com.neusoft.duanmlssh.comment.json.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.neusoft.duanmlssh.comment.json.IDataStore;
import com.neusoft.duanmlssh.comment.json.IDatacenter;

@SuppressWarnings("rawtypes")
public class DataCenter implements IDatacenter {

	public DataCenter() {
		paramMap = new HashMap();
		dataStoreMap = new HashMap();
		String s = "{\"returnStr\":{\"statusCode\":\"\",\"message\":\"\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"},header:{\"code\": 0,\"message\":{\"title\": \"\",\"detail\": \"\"}},body: {parameters:{},dataStores:{}}}";
		jsonObj = JSONObject.fromObject(s);
	}

	public final JSONObject getJSONObject() {
		return jsonObj;
	}

	public DataCenter(JSONObject jsonobject) {
		paramMap = new HashMap();
		dataStoreMap = new HashMap();
		jsonObj = jsonobject;
		initDataStores();
	}

	public String toString() {
		return getJSONObject().toString();
	}

	@SuppressWarnings("unchecked")
	private void initDataStores() {
		JSONObject jsonobject = getBodyObj().getJSONObject("dataStores");
		DataStore datastore;
		String s;
		for (Iterator iterator = jsonobject.entrySet().iterator(); iterator.hasNext(); dataStoreMap.put(s, datastore)) {
			java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
			s = entry.getKey().toString();
			datastore = new DataStore(s, jsonobject.getJSONObject(s));
		}
	}

	private JSONObject getHeaderObj() {
		return jsonObj.getJSONObject("header");
	}

	private JSONObject getBodyObj() {
		return jsonObj.getJSONObject("body");
	}

	public long getCode() {
		String s = getHeaderObj().get("code").toString();
		return Long.parseLong(s);
	}

	public void setCode(long l) {
		getHeaderObj().put("code", String.valueOf(l));
	}

	public IDataStore getDataStore(String s) {
		return (IDataStore) dataStoreMap.get(s);
	}

	@SuppressWarnings("unchecked")
	public List getDataStores() {
		Iterator iterator = dataStoreMap.entrySet().iterator();
		ArrayList arraylist = new ArrayList();
		java.util.Map.Entry entry;
		for (; iterator.hasNext(); arraylist.add(entry.getValue()))
			entry = (java.util.Map.Entry) iterator.next();

		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public void addDataStore(IDataStore idatastore) {
		JSONObject jsonobject = getBodyObj().getJSONObject("dataStores");
		DataStore datastore = (DataStore) idatastore;
		jsonobject.put(idatastore.getStoreName(), datastore.getJSONObject());
		dataStoreMap.put(idatastore.getStoreName(), datastore);
	}

	public String getDetail() {
		JSONObject jsonobject = getHeaderObj().getJSONObject("message");
		String s = jsonobject.get("detail").toString();
		return s;
	}

	public void setDetail(String s) {
		JSONObject jsonobject = getHeaderObj().getJSONObject("message");
		jsonobject.put("detail", s);
	}

	@SuppressWarnings("unchecked")
	public Map getParameters() {
		JSONObject jsonobject = getBodyObj().getJSONObject("parameters");
		Object obj;
		java.util.Map.Entry entry;
		for (Iterator iterator = jsonobject.entrySet().iterator(); iterator
				.hasNext(); paramMap.put(entry.getKey().toString(), obj)) {
			entry = (java.util.Map.Entry) iterator.next();
			obj = entry.getValue();
		}

		return paramMap;
	}

	public Object getParameter(String s) {
		JSONObject jsonobject = getBodyObj().getJSONObject("parameters");
		return jsonobject.get(s);
	}

	public void addParameter(String s, Object obj) {
		JSONObject jsonobject = getBodyObj().getJSONObject("parameters");
		jsonobject.put(s, obj);
	}

	public String getTitle() {
		JSONObject jsonobject = getHeaderObj().getJSONObject("message");
		String s = jsonobject.get("title").toString();
		return s;
	}

	public void setTitle(String s) {
		JSONObject jsonobject = getHeaderObj().getJSONObject("message");
		jsonobject.put("title", s);
	}

	@SuppressWarnings("unchecked")
	public Map getHeaderAttributes() {
		Iterator iterator = getHeaderObj().entrySet().iterator();
		HashMap hashmap = new HashMap();
		do {
			if (!iterator.hasNext())
				break;
			java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
			if (!entry.getKey().toString().equals("code")
					&& !entry.getKey().toString().equals("message"))
				hashmap.put(entry.getKey().toString(), entry.getValue()
						.toString());
		} while (true);
		return hashmap;
	}

	public void addHeaderAttribute(String s, String s1) {
		getHeaderObj().put(s, s1);
	}

	public Object clone() {
		return new DataCenter(JSONObject.fromObject(jsonObj));
	}
	
	@Override
	public void setStatusCode(int code) {
		jsonObj.getJSONObject("returnStr").put("statusCode", code);
	}

	@Override
	public void setMessage(String message) {
		jsonObj.getJSONObject("returnStr").put("message", message);
	}

	/**
	 * 回写需要刷新的navTabId值，系统会自动刷新次navTab
	 */
	@Override
	public void setNavTabId(String navTabId) {
		jsonObj.getJSONObject("returnStr").put("navTabId", navTabId);
	}

	@Override
	public void setRel(String rel) {
		jsonObj.getJSONObject("returnStr").put("rel", rel);
	}

	@Override
	public void setCallbackType(String callbackType) {
		jsonObj.getJSONObject("returnStr").put("callbackType", callbackType);
	}

	@Override
	public void setForwardUrl(String forwardUrl) {
		jsonObj.getJSONObject("returnStr").put("forwardUrl", forwardUrl);
	}

	@Override
	public void setConfirmMsg(String confirmMsg) {
		jsonObj.getJSONObject("returnStr").put("confirmMsg", confirmMsg);
	}

	private JSONObject jsonObj;
	private Map paramMap;
	private Map dataStoreMap;

}
