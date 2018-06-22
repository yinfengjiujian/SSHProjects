package com.neusoft.duanmlssh.comment.json.io;

import java.io.OutputStream;

import com.neusoft.duanmlssh.comment.json.io.impl.DataCenterWriterImpl;

public class DataCenterIOManager {

	public DataCenterIOManager()
    {
    }


    public static DataCenterWriter createWriter(OutputStream outputstream)
    {
        return new DataCenterWriterImpl(outputstream);
    }
}
