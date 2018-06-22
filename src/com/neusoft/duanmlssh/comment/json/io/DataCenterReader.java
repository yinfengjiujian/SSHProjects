package com.neusoft.duanmlssh.comment.json.io;

import com.neusoft.duanmlssh.comment.json.IDatacenter;

public interface DataCenterReader {
	public abstract IDatacenter parse() throws Exception;

	public abstract IDatacenter parse(String s);
}
