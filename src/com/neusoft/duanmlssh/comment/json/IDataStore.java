package com.neusoft.duanmlssh.comment.json;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface IDataStore {

	public abstract String getStoreName();

    public abstract void setStoreName(String s);

    public abstract Map getAttributes();

    public abstract void addAttribute(String s, String s1, String s2);

    public abstract void setAttributes(Map map);

    public abstract String getCondition();

    public abstract void setCondition(String s);

    public abstract Object getPropertyValue(String s);

    public abstract void addProperty(String s, Object obj);

    public abstract Map getParameters();

    public abstract void addParameter(String s, Object obj);

    public abstract void setParameters(Map map);

    public abstract IMetaData getMetaData();

    public abstract void setMetaData(IMetaData imetadata);

    public abstract String getOrder();

    public abstract void setOrder(String s);

    public abstract int getPageNo();

    public abstract void setPageNo(int i);

    public abstract int getPageSize();

    public abstract void setPageSize(int i);

    public abstract List getConditionValues();

    public abstract void setConditionValues(List list);

    public abstract void insertConditionObj(int i, Object obj);

    public abstract void insertConditionValue(int i, String s, String s1);

    public abstract String getPool();

    public abstract void setPool(String s);

    public abstract int getRecordCount();

    public abstract void setRecordCount(int i);

    public abstract List getRowDatasWithStatus();

    public abstract List getRowDatas();
}
