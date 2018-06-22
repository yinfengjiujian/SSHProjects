package com.neusoft.duanmlssh.comment.json.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.neusoft.duanmlssh.comment.json.ConvertionUtil;
import com.neusoft.duanmlssh.comment.json.IMetaData;

@SuppressWarnings("rawtypes")
public class MetaData implements IMetaData {

	public MetaData(JSONObject jsonobject) {
		metaObj = jsonobject;
	}

	public MetaData() {
		String s = "{columns:{},order:\"\",condition:\"\"}";
		metaObj = JSONObject.fromObject(s);
	}

	public final JSONObject getJSONObject() {
		return metaObj;
	}

	private JSONObject getColumnObj() {
		return metaObj.getJSONObject("columns");
	}

	public void addColumn(String s, Map map) {
		if (getColumnObj().containsKey(s))
			getColumnObj().remove(s);
		getColumnObj().accumulate(s, "{}");
		java.util.Map.Entry entry;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); getColumnObj()
				.getJSONObject(s).accumulate(
						ConvertionUtil.toValidJson(entry.getKey().toString()),
						entry.getValue()))
			entry = (java.util.Map.Entry) iterator.next();

	}

	public void addColumn(MetaDataColumn metadatacolumn) {
		String s = metadatacolumn.getColumnName();
		if (getColumnObj().containsKey(metadatacolumn.getColumnName()))
			getColumnObj().remove(s);
		getColumnObj().accumulate(s, "{}");
		if (metadatacolumn.getLabel() != null)
			getColumnObj().getJSONObject(s).accumulate("label",
					ConvertionUtil.toValidJson(metadatacolumn.getLabel()));
		int i = 0;
		i = metadatacolumn.getDataType();
		if (i != 0)
			getColumnObj().getJSONObject(s).accumulate("dataType",
					metadatacolumn.getDataType());
		if (metadatacolumn.getDefaultValue() != null)
			getColumnObj().getJSONObject(s).accumulate("defaultValue",
					metadatacolumn.getDefaultValue().toString());
		if (metadatacolumn.getFormat() != null)
			getColumnObj().getJSONObject(s).accumulate("format",
					metadatacolumn.getFormat());
		getColumnObj().getJSONObject(s).accumulate("nullable",
				metadatacolumn.isNullable());
		int j = -1;
		j = metadatacolumn.getPrecision();
		if (j != -1)
			getColumnObj().getJSONObject(s).accumulate("precision",
					metadatacolumn.getPrecision());
		getColumnObj().getJSONObject(s).accumulate("primaryKey",
				metadatacolumn.isPrimaryKey());
		int k = 0;
		k = metadatacolumn.getScale();
		if (k != 0)
			getColumnObj().getJSONObject(s).accumulate("scale",
					metadatacolumn.getScale());
		if (metadatacolumn.getAttribute() != null) {
			Map map = metadatacolumn.getAttribute();
			if (map != null) {
				java.util.Map.Entry entry;
				for (Iterator iterator = map.entrySet().iterator(); iterator
						.hasNext(); getColumnObj()
						.getJSONObject(s)
						.getJSONObject("attribute")
						.accumulate(entry.getKey().toString(),
								entry.getValue().toString())) {
					entry = (java.util.Map.Entry) iterator.next();
					if (!getColumnObj().getJSONObject(s).containsKey(
							"attribute"))
						getColumnObj().getJSONObject(s).accumulate("attribute",
								"{}");
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	public List getColumns() {
		ArrayList arraylist = new ArrayList();
		if (getColumnObj() != null) {
			Iterator iterator = getColumnObj().entrySet().iterator();
			int i = 0;
			MetaDataColumn metadatacolumn;
			label0: for (; iterator.hasNext(); arraylist.add(metadatacolumn)) {
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				String s = entry.getKey().toString();
				JSONObject jsonobject = getColumnObj().getJSONObject(s);
				Iterator iterator1 = jsonobject.entrySet().iterator();
				metadatacolumn = new MetaDataColumn();
				metadatacolumn.setColumnName(s);
				do {
					if (!iterator1.hasNext())
						continue label0;
					java.util.Map.Entry entry1 = (java.util.Map.Entry) iterator1
							.next();
					String s1 = entry1.getKey().toString();
					if (s1.equals("label"))
						metadatacolumn.setLabel(entry1.getValue().toString());
					if (s1.equals("dataType")) {
						if (entry1.getValue() == null
								|| "".equals(entry1.getValue().toString()))
							i = 0;
						else
							i = Integer.parseInt(entry1.getValue().toString());
						metadatacolumn.setDataType(i);
					}
					if (s1.equals("defaultValue")) {
						Object obj = ConvertionUtil.constructObject(
								entry1.getValue(), i);
						metadatacolumn.setDefaultValue(obj);
					}
					if (s1.equals("format"))
						metadatacolumn.setFormat(entry1.getValue().toString());
					if (s1.equals("nullable"))
						if (entry1.getValue().toString().equals("false"))
							metadatacolumn.setNullable(false);
						else
							metadatacolumn.setNullable(true);
					if (s1.equals("precision"))
						if (entry1.getValue() == null
								|| "".equals(entry1.getValue().toString()))
							metadatacolumn.setPrecision(0);
						else
							metadatacolumn.setPrecision(Integer.parseInt(entry1
									.getValue().toString()));
					if (s1.equals("primaryKey"))
						if (entry1.getValue().toString().equals("false"))
							metadatacolumn.setPrimaryKey(false);
						else
							metadatacolumn.setPrimaryKey(true);
					if (s1.equals("scale"))
						if (entry1.getValue() == null
								|| "".equals(entry1.getValue().toString()))
							metadatacolumn.setScale(0);
						else
							metadatacolumn.setScale(Integer.parseInt(entry1
									.getValue().toString()));
					if (s1.equals("attribute") && entry1.getValue() != null
							&& !"".equals(entry1.getValue().toString())) {
						JSONObject jsonobject1 = (JSONObject) entry1.getValue();
						Iterator iterator2 = jsonobject1.entrySet().iterator();
						while (iterator2.hasNext()) {
							java.util.Map.Entry entry2 = (java.util.Map.Entry) iterator2
									.next();
							metadatacolumn.setAttribute(entry2.getKey()
									.toString(), entry2.getValue());
						}
					}
				} while (true);
			}

		}
		return arraylist;
	}

	public MetaDataColumn getColumn(String s) {
		MetaDataColumn metadatacolumn = null;
		if (getColumnObj() != null && getColumnObj().containsKey(s)) {
			metadatacolumn = new MetaDataColumn();
			metadatacolumn.setColumnName(s);
			JSONObject jsonobject = (JSONObject) getColumnObj().get(s);
			if (jsonobject.containsKey("label"))
				metadatacolumn.setLabel(jsonobject.getString("label"));
			int i = 0;
			if (jsonobject.containsKey("dataType")) {
				i = jsonobject.getInt("dataType");
				metadatacolumn.setDataType(i);
			}
			if (jsonobject.containsKey("defaultValue")) {
				Object obj = ConvertionUtil.constructObject(
						jsonobject.get("defaultValue"), i);
				metadatacolumn.setDefaultValue(obj);
			}
			if (jsonobject.containsKey("format"))
				metadatacolumn.setFormat(jsonobject.getString("format"));
			if (jsonobject.containsKey("nullable"))
				metadatacolumn.setNullable(jsonobject.getBoolean("nullable"));
			if (jsonobject.containsKey("precision"))
				metadatacolumn.setPrecision(jsonobject.getInt("precision"));
			if (jsonobject.containsKey("primaryKey"))
				metadatacolumn.setPrimaryKey(jsonobject
						.getBoolean("primaryKey"));
			if (jsonobject.containsKey("scale"))
				metadatacolumn.setScale(jsonobject.getInt("scale"));
			if (jsonobject.containsKey("attribute")) {
				JSONObject jsonobject1 = (JSONObject) jsonobject
						.get("attribute");
				java.util.Map.Entry entry;
				for (Iterator iterator = jsonobject1.entrySet().iterator(); iterator
						.hasNext(); metadatacolumn.setAttribute(entry.getKey()
						.toString(), entry.getValue()))
					entry = (java.util.Map.Entry) iterator.next();

			}
		}
		return metadatacolumn;
	}

	public Object getPropertyValue(String s) {
		Object obj = null;
		if (metaObj.containsKey(s))
			obj = metaObj.get(s);
		return obj;
	}

	public void addProperty(String s, Object obj) {
		metaObj.put(s, obj);
	}

	private JSONObject metaObj;
}
