package com.neusoft.duanmlssh.comment.json.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.RowSetWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.neusoft.duanmlssh.comment.json.ConvertionUtil;
import com.neusoft.duanmlssh.comment.json.IDataStore;
import com.neusoft.duanmlssh.comment.json.IMetaData;
import com.neusoft.duanmlssh.comment.json.IRowSet;

@SuppressWarnings("rawtypes")
public class DataStore implements IDataStore {

	public DataStore(String s) {
		rowSetWriter = null;
		storeName = s;
		String s1 = "{rowSet: {\"primary\": [],\"filter\": [],\"delete\": []},parameters:{},name: \""
				+ s + "\",pageNumber: 1,pageSize: 2147483647,recordCount: 0}";
		storeObj = JSONObject.fromObject(s1);
	}

	public DataStore(String s, JSONObject jsonobject) {
		rowSetWriter = null;
		storeName = s;
		storeObj = jsonobject;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String s) {
		storeName = s;
	}

	public final JSONObject getJSONObject() {
		return storeObj;
	}

	public final JSONArray getPrimaryArray() {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		return jsonobject.getJSONArray("primary");
	}

	public final JSONArray getFilterArray() {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		return jsonobject.getJSONArray("filter");
	}

	public final JSONArray getDeleteArray() {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		return jsonobject.getJSONArray("delete");
	}

	@SuppressWarnings("unchecked")
	public Map getAttributes() {
		HashMap hashmap = null;
		if (storeObj.containsKey("attributes")) {
			hashmap = new HashMap();
			JSONObject jsonobject = storeObj.getJSONObject("attributes");
			for (Iterator iterator = jsonobject.entrySet().iterator(); iterator
					.hasNext();) {
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				ArrayList arraylist = new ArrayList();
				try {
					Object aobj[] = ((JSONArray) entry.getValue()).toArray();
					if (aobj == null || aobj.length != 2) {
						System.out.println("attributes \u7684\u503C\u683C\u5F0F\u4E0D\u6B63\u786E\u3002");
					} else {
						arraylist.add(aobj[0]);
						arraylist.add(aobj[1]);
						hashmap.put(entry.getKey().toString(), arraylist);
					}
				} catch (Exception exception) {
					System.out.println("attributes \u7684\u503C\u683C\u5F0F\u4E0D\u6B63\u786E\u3002");
					exception.printStackTrace();
				}
			}

		}
		return hashmap;
	}

	public void addAttribute(String s, String s1, String s2) {
		if (!storeObj.containsKey("attributes"))
			storeObj.accumulate("attributes", "{}");
		String s3 = "[\"" + s1 + "\",\"" + s2 + "\"]";
		storeObj.getJSONObject("attributes").accumulate(s, s3);
	}

	public void setAttributes(Map map) {
		if (map != null) {
			java.util.Map.Entry entry;
			for (Iterator iterator = map.entrySet().iterator(); iterator
					.hasNext(); storeObj.getJSONObject("attributes")
					.accumulate(entry.getKey().toString(), entry.getValue())) {
				entry = (java.util.Map.Entry) iterator.next();
				if (!storeObj.containsKey("attributes"))
					storeObj.accumulate("attributes", "{}");
			}

		}
	}

	public String getCondition() {
		String s;
		if (storeObj.containsKey("condition"))
			s = storeObj.get("condition").toString();
		else
			s = null;
		return s;
	}

	public void setCondition(String s) {
		storeObj.put("condition", s);
	}

	public Object getPropertyValue(String s) {
		Object obj = null;
		if (storeObj.containsKey(s))
			obj = storeObj.get(s);
		return obj;
	}

	public void addProperty(String s, Object obj) {
		storeObj.put(s, obj.toString());
	}

	public Map getParameters() {
		return getMapUtil("parameters");
	}

	public void addParameter(String s, Object obj) {
		if (!storeObj.containsKey("parameters"))
			storeObj.accumulate("parameters", "{}");
		storeObj.getJSONObject("parameters").accumulate(s, obj);
	}

	public void setParameters(Map map) {
		if (map != null) {
			if (!storeObj.containsKey("parameters"))
				storeObj.accumulate("parameters", "{}");
			java.util.Map.Entry entry;
			for (Iterator iterator = map.entrySet().iterator(); iterator
					.hasNext(); storeObj.getJSONObject("parameters")
					.accumulate(entry.getKey().toString(), entry.getValue()))
				entry = (java.util.Map.Entry) iterator.next();

		}
	}

	public IMetaData getMetaData() {
		MetaData metadata = null;
		if (storeObj.containsKey("metaData"))
			metadata = new MetaData(storeObj.getJSONObject("metaData"));
		return metadata;
	}

	public void setMetaData(IMetaData imetadata) {
		if (!storeObj.containsKey("metaData"))
			storeObj.accumulate("metaData", "{}");
		MetaData metadata = (MetaData) imetadata;
		storeObj.put("metaData", metadata.getJSONObject());
	}

	public String getOrder() {
		String s = null;
		if (storeObj.containsKey("order"))
			s = storeObj.get("order").toString();
		return s;
	}

	public void setOrder(String s) {
		storeObj.put("order", s);
	}

	public int getPageNo() {
		String s = storeObj.get("pageNumber").toString();
		return Integer.parseInt(s);
	}

	public void setPageNo(int i) {
		storeObj.put("pageNumber", new Integer(i));
	}

	public int getPageSize() {
		String s = storeObj.get("pageSize").toString();
		return Integer.parseInt(s);
	}

	public void setPageSize(int i) {
		storeObj.put("pageSize", new Integer(i));
	}

	
	@SuppressWarnings("unchecked")
	public List getConditionValues() {
		ArrayList arraylist = null;
		if (storeObj.containsKey("conditionValues")) {
			JSONArray jsonarray = storeObj.getJSONArray("conditionValues");
			Object aobj[] = jsonarray.toArray();
			arraylist = new ArrayList();
			for (int i = 0; i < aobj.length; i++) {
				Object aobj1[] = ((JSONArray) aobj[i]).toArray();
				arraylist.add(((Object) (aobj1)));
			}

		}
		return arraylist;
	}

	public void setConditionValues(List list) {
		storeObj.put("conditionValues", "[]");
		if (null != list) {
			for (int j = 0; j < list.size(); j++) {
				Object obj = list.get(j);
				int i = ConvertionUtil.javaTypeToSqlType(obj.getClass()
						.toString());
				insertConditionValue(j, ConvertionUtil.toJson(obj),
						String.valueOf(i));
			}

		}
	}

	public void insertConditionValue(int i, String s, String s1) {
		if (!storeObj.containsKey("conditionValues"))
			storeObj.accumulate("conditionValues", "[]");
		JSONArray jsonarray = storeObj.getJSONArray("conditionValues");
		JSONArray jsonarray1 = new JSONArray();
		jsonarray1.add(0, s);
		jsonarray1.add(1, s1);
		jsonarray.add(i, jsonarray1);
	}

	public void insertConditionObj(int i, Object obj) {
		int j = ConvertionUtil.javaTypeToSqlType(obj.getClass().toString());
		insertConditionValue(i, ConvertionUtil.toJson(obj), String.valueOf(j));
	}

	public String getPool() {
		String s = null;
		if (storeObj.containsKey("pool"))
			s = storeObj.get("pool").toString();
		return s;
	}

	public void setPool(String s) {
		storeObj.put("pool", s);
	}

	public int getRecordCount() {
		String s = storeObj.get("recordCount").toString();
		return Integer.parseInt(s);
	}

	public void setRecordCount(int i) {
		storeObj.put("recordCount", new Integer(i));
	}

	public List getRowDatasWithStatus() {
		JSONArray jsonarray = getPrimaryArray();
		return getListUtil(jsonarray);
	}

	@SuppressWarnings("unchecked")
	public List getRowDatas() {
		JSONArray jsonarray = getPrimaryArray();
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			Iterator iterator = jsonobject.entrySet().iterator();
			HashMap hashmap = new HashMap();
			do {
				if (!iterator.hasNext())
					break;
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				String s = entry.getKey().toString();
				Object obj = entry.getValue();
				if (!s.equals("_o") && !s.equals("_s") && !s.equals("_t")) {
					if (isNull(obj))
						obj = null;
					hashmap.put(s, obj);
				}
			} while (true);
			arraylist.add(hashmap);
		}

		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public List getRowDataObjs() {
		JSONArray jsonarray = getPrimaryArray();
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			arraylist.add(jsonobject);
		}

		return arraylist;
	}

	public void addRowData(Map map) {
		if (map != null) {
			JSONArray jsonarray = getPrimaryArray();
			jsonarray.add(map);
		}
	}

	public List getDeleteDatas() {
		JSONArray jsonarray = getDeleteArray();
		return getListUtil(jsonarray);
	}

	@SuppressWarnings("unchecked")
	public List getDeleteRowObjs() {
		JSONArray jsonarray = getDeleteArray();
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			arraylist.add(jsonobject);
		}

		return arraylist;
	}

	public void addDeleteData(Map map) {
		if (map != null)
			getDeleteArray().add(map);
	}

	public List getFilterDatasWithStatus() {
		JSONArray jsonarray = getFilterArray();
		return getListUtil(jsonarray);
	}

	@SuppressWarnings("unchecked")
	public List getFilterRowDataObjs() {
		JSONArray jsonarray = getFilterArray();
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			arraylist.add(jsonobject);
		}

		return arraylist;
	}

	public void addFilterData(Map map) {
		if (map != null)
			getFilterArray().add(map);
	}

	public String getRowSetName() {
		String s = null;
		if (storeObj.containsKey("rowSetName"))
			s = storeObj.get("rowSetName").toString();
		return s;
	}

	public void setRowSetName(String s) {
		storeObj.put("rowSetName", s);
	}

	public String getStatementName() {
		String s = null;
		if (storeObj.containsKey("statementName"))
			s = storeObj.get("statementName").toString();
		return s;
	}

	public void setStatementName(String s) {
		storeObj.put("statementName", s);
	}

	@SuppressWarnings("unchecked")
	public Map getStatistics() {
		HashMap hashmap = null;
		if (storeObj.containsKey("statistics")) {
			hashmap = new HashMap();
			JSONObject jsonobject = storeObj.getJSONObject("statistics");
			java.util.Map.Entry entry;
			HashMap hashmap1;
			for (Iterator iterator = jsonobject.entrySet().iterator(); iterator
					.hasNext(); hashmap
					.put(entry.getKey().toString(), hashmap1)) {
				entry = (java.util.Map.Entry) iterator.next();
				JSONObject jsonobject1 = (JSONObject) entry.getValue();
				Iterator iterator1 = jsonobject1.entrySet().iterator();
				hashmap1 = new HashMap();
				java.util.Map.Entry entry1;
				for (; iterator1.hasNext(); hashmap1.put(entry1.getKey()
						.toString(), entry1.getValue().toString()))
					entry1 = (java.util.Map.Entry) iterator1.next();

			}

		}
		return hashmap;
	}

	public void addStatistics(String s, Map map) {
		if (!storeObj.containsKey("statistics"))
			storeObj.accumulate("statistics", "{}");
		JSONObject jsonobject = storeObj.getJSONObject("statistics");
		if (!jsonobject.containsKey(s))
			jsonobject.accumulate(s, "{}");
		java.util.Map.Entry entry;
		String s1;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); jsonobject
				.getJSONObject(s).put(entry.getKey().toString(), s1)) {
			entry = (java.util.Map.Entry) iterator.next();
			s1 = entry.getValue().toString();
		}

	}

	public void addStatistics(String s, String s1) {
		if (!storeObj.containsKey("statistics"))
			storeObj.accumulate("statistics", "{}");
		JSONObject jsonobject = storeObj.getJSONObject("statistics");
		if (!jsonobject.containsKey(s))
			jsonobject.accumulate(s, "{}");
		jsonobject.getJSONObject(s).put(s1, "");
	}

	public boolean isDistinct() {
		boolean flag = false;
		if (storeObj.containsKey("distinct")) {
			String s = storeObj.get("distinct").toString();
			if (s.equals("true"))
				flag = true;
		}
		return flag;
	}

	public void setDistinct(boolean flag) {
		if (flag)
			storeObj.put("distinct", "true");
		else
			storeObj.put("distinct", "false");
	}

	@SuppressWarnings("unchecked")
	private Map getMapUtil(String s) {
		HashMap hashmap = null;
		if (storeObj.containsKey(s)) {
			hashmap = new HashMap();
			JSONObject jsonobject = storeObj.getJSONObject(s);
			java.util.Map.Entry entry;
			for (Iterator iterator = jsonobject.entrySet().iterator(); iterator
					.hasNext(); hashmap.put(entry.getKey().toString(),
					entry.getValue()))
				entry = (java.util.Map.Entry) iterator.next();

		}
		return hashmap;
	}

	@SuppressWarnings("unchecked")
	private List getListUtil(JSONArray jsonarray) {
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = (JSONObject) jsonarray.get(i);
			Iterator iterator = jsonobject.entrySet().iterator();
			HashMap hashmap = new HashMap();
			String s;
			Object obj;
			for (; iterator.hasNext(); hashmap.put(s, obj)) {
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				s = entry.getKey().toString();
				obj = entry.getValue();
				if (s.equals("_o")) {
					JSONObject jsonobject1 = (JSONObject) obj;
					Iterator iterator1 = jsonobject1.entrySet().iterator();
					HashMap hashmap1 = new HashMap();
					java.util.Map.Entry entry1;
					Object obj1;
					for (; iterator1.hasNext(); hashmap1.put(entry1.getKey(),
							isNull(obj1) ? null : obj1)) {
						entry1 = (java.util.Map.Entry) iterator1.next();
						obj1 = entry1.getValue();
					}

					obj = hashmap1;
					continue;
				}
				if (isNull(obj))
					obj = null;
			}

			arraylist.add(hashmap);
		}

		return arraylist;
	}

	private boolean isNull(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject jsonobject = (JSONObject) obj;
			if (jsonobject.isNullObject())
				return true;
		}
		return false;
	}

	public void setRowSetWriter(RowSetWriter rowsetwriter) {
		rowSetWriter = rowsetwriter;
	}

	public RowSetWriter getRowSetWriter() {
		return rowSetWriter;
	}

	public Object clone() {
		DataStore datastore = new DataStore(storeName,
				JSONObject.fromObject(storeObj));
		datastore.setRowSetWriter(rowSetWriter);
		return datastore;
	}

	public void clearRowSet() {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		jsonobject.put("primary", new JSONArray());
		jsonobject.put("delete", new JSONArray());
		jsonobject.put("filter", new JSONArray());
	}

	public void deleteRow(int i, String s) {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		if (s.equalsIgnoreCase("primary")) {
			JSONArray jsonarray = jsonobject.getJSONArray("primary");
			if (jsonarray.size() == 0 || i > jsonarray.size() - 1)
				return;
			jsonarray.remove(i);
		} else if (s.equalsIgnoreCase("delete")) {
			JSONArray jsonarray1 = jsonobject.getJSONArray("delete");
			if (jsonarray1.size() == 0 || i > jsonarray1.size() - 1)
				return;
			jsonarray1.remove(i);
		} else if (s.equalsIgnoreCase("filter")) {
			JSONArray jsonarray2 = jsonobject.getJSONArray("filter");
			if (jsonarray2.size() == 0 || i > jsonarray2.size() - 1)
				return;
			jsonarray2.remove(i);
		}
	}

	public IRowSet getRowSet() {
		JSONObject jsonobject = storeObj.getJSONObject("rowSet");
		return new RowSet(jsonobject);
	}

	private String storeName;
	private JSONObject storeObj;
	private RowSetWriter rowSetWriter;
}
