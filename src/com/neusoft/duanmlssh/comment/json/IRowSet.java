package com.neusoft.duanmlssh.comment.json;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
public interface IRowSet {

	public abstract List getDeleteRows();

    public abstract List getFilterRows();

    public abstract List getRows();

    public abstract List getNewModifyRows();

    public abstract List getSelectedRows();

    public abstract List getModifyRows();

    public abstract IRow getRow(int i);

    public abstract IRow getRow(int i, String s);

    public abstract int getRowCount(String s);

    public abstract int getTotalCount();

    public abstract boolean isEmpty();

    public abstract void forEach(IRowHandler irowhandler, String s);

    public abstract boolean every(IRowHandler irowhandler, String s);

    public abstract boolean some(IRowHandler irowhandler, String s);

    public abstract void deleteRow(int i, String s);

    public abstract int addDeleteData(Map map);

    public abstract int addFilterData(Map map);

    public abstract void clear();

    public abstract int addRowData(Map map);

    public abstract int addRow(IRow irow);

    public abstract void insertRow(int i, IRow irow);

    public abstract void insertRowData(int i, Map map);

    public abstract void addBatchRowData(List list);

    public abstract void delBatchRow(int ai[]);
}
