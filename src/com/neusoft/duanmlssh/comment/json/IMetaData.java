package com.neusoft.duanmlssh.comment.json;

import java.util.List;
import java.util.Map;

import com.neusoft.duanmlssh.comment.json.impl.MetaDataColumn;
@SuppressWarnings("rawtypes")
public interface IMetaData {

	public abstract void addColumn(String s, Map map);

    public abstract void addColumn(MetaDataColumn metadatacolumn);

    public abstract List getColumns();

    public abstract MetaDataColumn getColumn(String s);

    public abstract Object getPropertyValue(String s);

    public abstract void addProperty(String s, Object obj);
}
