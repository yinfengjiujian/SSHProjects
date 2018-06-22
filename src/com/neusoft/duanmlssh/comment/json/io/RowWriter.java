package com.neusoft.duanmlssh.comment.json.io;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface RowWriter {

	public abstract void writeRow(Map map) throws IOException;
}
