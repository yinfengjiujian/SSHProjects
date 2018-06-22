package com.neusoft.duanmlssh.comment.json.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.neusoft.duanmlssh.comment.json.IRow;
import com.neusoft.duanmlssh.comment.json.IRowHandler;
import com.neusoft.duanmlssh.comment.json.IRowSet;

@SuppressWarnings("rawtypes")
public class RowSet implements IRowSet {
	
	public RowSet(JSONObject jsonobject) {
		rowsetObj = jsonobject;
	}

	public List getDeleteRows() {
		return getRowsFromArray("delete");
	}

	public List getFilterRows() {
		return getRowsFromArray("filter");
	}

	public List getRows() {
		return getRowsFromArray("primary");
	}

	@SuppressWarnings("unchecked")
	private List getRowsFromArray(String s) {
		JSONArray jsonarray = rowsetObj.getJSONArray(s);
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			Row row = new Row(jsonobject, i);
			arraylist.add(row);
		}

		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public List getNewModifyRows() {
		List list = getRows();
		List list1 = getFilterRows();
		if (list1.size() > 0)
			list.add(getFilterRows());
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Row row = (Row) list.get(i);
			if (row.isNewModify())
				arraylist.add(row);
		}

		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public List getSelectedRows() {
		List list = getRows();
		List list1 = getFilterRows();
		if (list1.size() > 0)
			list.add(getFilterRows());
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Row row = (Row) list.get(i);
			if (row.isSeleted())
				arraylist.add(row);
		}

		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public List getModifyRows() {
		List list = getRows();
		List list1 = getFilterRows();
		if (list1.size() > 0)
			list.add(list1);
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Row row = (Row) list.get(i);
			if (row.isModify())
				arraylist.add(row);
		}

		return arraylist;
	}

	public IRow getRow(int i) {
		return getRow(i, "primary");
	}

	public IRow getRow(int i, String s) {
		List list = null;
		if (s.equalsIgnoreCase("filter"))
			list = getFilterRows();
		else if (s.equalsIgnoreCase("delete"))
			list = getDeleteRows();
		else
			list = getRows();
		if (null == list) {
			return null;
		} else {
			IRow irow = (IRow) list.get(i);
			return irow;
		}
	}

	public void forEach(IRowHandler irowhandler, String s) {
		List list = getRowsFromArray(s);
		for (int i = 0; i < list.size(); i++) {
			IRow irow = (IRow) list.get(i);
			irowhandler.handle(irow);
		}

	}

	public boolean every(IRowHandler irowhandler, String s) {
		List list = getRowsFromArray(s);
		for (int i = 0; i < list.size(); i++) {
			IRow irow = (IRow) list.get(i);
			boolean flag1 = irowhandler.handle(irow);
			if (!flag1)
				return false;
		}

		return true;
	}

	public boolean some(IRowHandler irowhandler, String s) {
		List list = getRowsFromArray(s);
		for (int i = 0; i < list.size(); i++) {
			IRow irow = (IRow) list.get(i);
			boolean flag1 = irowhandler.handle(irow);
			if (flag1)
				return true;
		}

		return false;
	}

	public int getRowCount(String s) {
		List list = getRowsFromArray(s);
		return list.size();
	}

	public int getTotalCount() {
		int i = getFilterRows().size();
		i += getDeleteRows().size();
		i += getRows().size();
		return i;
	}

	public boolean isEmpty() {
		return getTotalCount() == 0;
	}

	public void deleteRow(int i, String s) {
		JSONObject jsonobject = rowsetObj;
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

	public int addDeleteData(Map map) {
		if (null == map)
			map = new HashMap();
		JSONArray jsonarray = rowsetObj.getJSONArray("delete");
		jsonarray.add(map);
		return jsonarray.size() - 1;
	}

	public int addFilterData(Map map) {
		if (null == map)
			map = new HashMap();
		JSONArray jsonarray = rowsetObj.getJSONArray("filter");
		jsonarray.add(map);
		return jsonarray.size() - 1;
	}

	public void clear() {
		rowsetObj.put("primary", new JSONArray());
		rowsetObj.put("delete", new JSONArray());
		rowsetObj.put("filter", new JSONArray());
	}

	public void addBatchRowData(List list) {
		int i = 0;
		for (int j = list.size(); i < j; i++)
			addRowData((Map) list.get(i));

	}

	public int addRow(IRow irow) {
		irow.setRowStatus(1);
		JSONArray jsonarray = rowsetObj.getJSONArray("primary");
		jsonarray.add(((Row) irow).getRowObj());
		return jsonarray.size() - 1;
	}

	public int addRowData(Map map) {
		if (null == map)
			map = new HashMap();
		JSONArray jsonarray = rowsetObj.getJSONArray("primary");
		jsonarray.add(map);
		return jsonarray.size() - 1;
	}

	public void delBatchRow(int ai[]) {
		for (int k = 0; k < ai.length; k++) {
			int j = k;
			int i = ai[k];
			for (int l = k + 1; l < ai.length; l++)
				if (ai[l] > i) {
					i = ai[l];
					j = l;
				}

			ai[j] = ai[k];
			JSONArray jsonarray = rowsetObj.getJSONArray("primary");
			jsonarray.remove(i);
		}

	}

	public void insertRow(int i, IRow irow) {
		irow.setRowStatus(1);
		JSONArray jsonarray = rowsetObj.getJSONArray("primary");
		jsonarray.add(i, ((Row) irow).getRowObj());
	}

	public void insertRowData(int i, Map map) {
		if (null == map)
			map = new HashMap();
		JSONArray jsonarray = rowsetObj.getJSONArray("primary");
		jsonarray.add(i, map);
	}

	JSONObject rowsetObj;
}
