package com.neusoft.duanmlssh.comment.json.impl;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MetaDataColumn {

	public MetaDataColumn()
    {
        dataType = 0;
        isPrimaryKey = false;
        precision = -1;
        scale = 0;
        nullable = true;
        columnName = "";
        attribute = new HashMap();
    }

    public Map getAttribute()
    {
        return attribute;
    }

    @SuppressWarnings("unchecked")
	public void setAttribute(String s, Object obj)
    {
        attribute.put(s, obj);
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String s)
    {
        columnName = s;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String s)
    {
        label = s;
    }

    public int getDataType()
    {
        return dataType;
    }

    public void setDataType(int i)
    {
        dataType = i;
    }

    public boolean isPrimaryKey()
    {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean flag)
    {
        isPrimaryKey = flag;
    }

    public int getPrecision()
    {
        return precision;
    }

    public void setPrecision(int i)
    {
        precision = i;
    }

    public int getScale()
    {
        return scale;
    }

    public void setScale(int i)
    {
        scale = i;
    }

    public boolean isNullable()
    {
        return nullable;
    }

    public void setNullable(boolean flag)
    {
        nullable = flag;
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(Object obj)
    {
        defaultValue = obj;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(String s)
    {
        format = s;
    }

    private String label;
    private int dataType;
    private boolean isPrimaryKey;
    private int precision;
    private int scale;
    private boolean nullable;
    private Object defaultValue;
    private String format;
    private String columnName;
    private Map attribute;
}
