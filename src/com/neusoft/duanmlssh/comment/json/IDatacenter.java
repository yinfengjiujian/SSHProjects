package com.neusoft.duanmlssh.comment.json;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface IDatacenter {
	
	public abstract void setCode(long l);

    public abstract long getCode();

    public abstract void setTitle(String s);

    public abstract String getTitle();

    public abstract void setDetail(String s);

    public abstract String getDetail();

    public abstract void addParameter(String s, Object obj);

    public abstract Map getParameters();

    public abstract Object getParameter(String s);

    public abstract void addHeaderAttribute(String s, String s1);

    public abstract Map getHeaderAttributes();

    public abstract void addDataStore(IDataStore idatastore);
    
	public abstract List getDataStores();

    public abstract IDataStore getDataStore(String s);

    public abstract String toString();

    public abstract Object clone();
    
    public abstract void setStatusCode(int code);
    public abstract void setMessage(String message);
    public abstract void setNavTabId(String navTabId);
    public abstract void setRel(String rel);
    public abstract void setCallbackType(String callbackType);
    public abstract void setForwardUrl(String forwardUrl);
    public abstract void setConfirmMsg(String confirmMsg);

}
