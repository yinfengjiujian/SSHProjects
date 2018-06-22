package com.neusoft.duanmlssh.comment.json.io.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.neusoft.duanmlssh.comment.json.ConvertionUtil;
import com.neusoft.duanmlssh.comment.json.IDatacenter;
import com.neusoft.duanmlssh.comment.json.impl.DataCenter;
import com.neusoft.duanmlssh.comment.json.impl.DataStore;
import com.neusoft.duanmlssh.comment.json.impl.MetaData;
import com.neusoft.duanmlssh.comment.json.io.DataCenterWriter;
import com.neusoft.duanmlssh.comment.json.io.RowSetWriter;
import com.neusoft.duanmlssh.comment.json.io.RowWriter;

@SuppressWarnings("rawtypes")
public class DataCenterWriterImpl implements DataCenterWriter, RowWriter{

	public DataCenterWriterImpl(OutputStream outputstream)
    {
        writerClose = false;
        rsBool = false;
        dsBool = false;
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void write(IDatacenter idatacenter)
    {
        if(idatacenter == null)
        {
        }
        try
        {
            writeDC((DataCenter)idatacenter);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void writeDC(DataCenter datacenter)
        throws IOException
    {
        if(writerClose)
            return;
        dsBool = false;
        append("{");
        writeHeader(datacenter);
        append(",");
        writeBody(datacenter);
        append("}");
        try
        {
            writer.close();
            writerClose = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void writeHeader(DataCenter datacenter)
        throws IOException
    {
    	append(datacenter.getJSONObject().getJSONObject("returnStr").toString().substring(1, datacenter.getJSONObject().getJSONObject("returnStr").toString().length()-1));
        append(",\"header\":");
        append(datacenter.getJSONObject().getJSONObject("header").toString());
    }

    private void writeBody(DataCenter datacenter)
        throws IOException
    {
        append("\"body\" :{");
        writeParameters(datacenter);
        append(",");
        writeStores(datacenter);
        append("}");
    }

    private void writeParameters(DataCenter datacenter)
        throws IOException
    {
        append("\"parameters\" :");
        append(datacenter.getJSONObject().getJSONObject("body").getJSONObject("parameters").toString());
    }

    private void writeStores(DataCenter datacenter)
        throws IOException
    {
        append("\"dataStores\" :{");
        List list = datacenter.getDataStores();
        int i = 0;
        for(int j = list.size(); i < j; i++)
            writeDS((DataStore)list.get(i));

        append("}");
    }

    private void writeDS(DataStore datastore)
        throws IOException
    {
        if(dsBool || !(dsBool = true))
            append(",");
        append("\"" + datastore.getStoreName() + "\":{");
        writeDSProperties(datastore);
        writeRS(datastore);
        append("}");
    }

    private void writeDSProperties(DataStore datastore)
        throws IOException
    {
        append("\"name\":\"" + datastore.getStoreName() + "\",");
        append("\"pageNumber\":" + datastore.getPageNo() + ",");
        append("\"pageSize\":" + datastore.getPageSize() + ",");
        append("\"recordCount\":" + datastore.getRecordCount() + ",");
        if(datastore.getMetaData() != null)
        {
            append("\"metaData\"");
            append(":");
            append(((MetaData)datastore.getMetaData()).getJSONObject().toString());
            append(",");
        }
        if(datastore.getAttributes() != null)
        {
            append("\"attributes\":{");
            append(getAttrStr(datastore.getAttributes()));
            append("},");
        }
        if(datastore.getCondition() != null)
        {
            append("\"condition\":");
            append("\"");
            append(ConvertionUtil.toValidJson(datastore.getCondition()));
            append("\"");
            append(",");
        }
        if(datastore.getConditionValues() != null)
        {
            append("\"conditionValues\":");
            append(datastore.getJSONObject().getJSONArray("conditionValues").toString());
            append(",");
        }
        if(datastore.getRowSetName() != null)
        {
            append("\"rowSetName\":");
            append("\"");
            append(ConvertionUtil.toValidJson(datastore.getRowSetName()));
            append("\"");
            append(",");
        }
        if(datastore.getStatementName() != null)
        {
            append("\"statementName\":");
            append("\"");
            append(ConvertionUtil.toValidJson(datastore.getStatementName()));
            append("\"");
            append(",");
        }
        if(datastore.getParameters() != null)
        {
            append("\"parameters\":");
            append(datastore.getJSONObject().getJSONObject("parameters").toString());
            append(",");
        }
        if(datastore.getOrder() != null)
        {
            append("\"order\":");
            append("\"");
            append(ConvertionUtil.toValidJson(datastore.getOrder()));
            append("\"");
            append(",");
        }
        if(datastore.getStatistics() != null)
        {
            append("\"statistics\": ");
            append(datastore.getJSONObject().getJSONObject("statistics").toString());
            append(",");
        }
        if(datastore.getPool() != null)
        {
            append("pool:\"");
            append(ConvertionUtil.toValidJson(datastore.getPool()));
            append("\",");
        }
        if(datastore.getJSONObject().containsKey("distinct"))
        {
            append("distinct:");
            if(datastore.isDistinct())
                append("\"true\"");
            else
                append("\"false\"");
            append(",");
        }
    }

    private String getAttrStr(Map map)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        Iterator iterator = map.entrySet().iterator();
        int i = 0;
        List list;
        for(; iterator.hasNext(); stringbuffer.append(ConvertionUtil.toJson(list.get(0)) + "," + ConvertionUtil.toJson(list.get(1)) + "]"))
        {
            if(i != 0)
                stringbuffer.append(",");
            i++;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            stringbuffer.append("\"");
            stringbuffer.append(ConvertionUtil.toValidJson(entry.getKey().toString()) + "\":[");
            list = (List)map.get(entry.getKey());
        }

        return stringbuffer.toString();
    }

    private void writeRS(DataStore datastore)
        throws IOException
    {
        append("rowSet:{");
        writePrimaryBuffer(datastore);
        writeFilterBuffer(datastore);
        writeDeleteBuffer(datastore);
        append("}");
    }

    private void writePrimaryBuffer(DataStore datastore)
        throws IOException
    {
        append("\"primary\": [");
        RowSetWriter rowsetwriter = (RowSetWriter) datastore.getRowSetWriter();
        if(rowsetwriter != null)
        {
            rsBool = false;
            rowsetwriter.writeRowSet(this);
        } else
        {
            writeBuffer(datastore.getPrimaryArray());
        }
        append("]");
        append(",");
    }

    private void writeFilterBuffer(DataStore datastore)
        throws IOException
    {
        append("\"filter\": [");
        writeBuffer(datastore.getFilterArray());
        append("]");
        append(",");
    }

    private void writeDeleteBuffer(DataStore datastore)
        throws IOException
    {
        append("\"delete\": [");
        writeBuffer(datastore.getDeleteArray());
        append("]");
    }

    private void writeBuffer(JSONArray jsonarray)
        throws IOException
    {
        rsBool = false;
        for(int i = 0; i < jsonarray.size(); i++)
            writeRow((Map)jsonarray.get(i));

    }

    public void writeRow(Map map)
        throws IOException
    {
        if(rsBool || !(rsBool = true))
            append(",");
        if(map instanceof JSONObject)
        {
            append(map.toString());
        } else
        {
            Iterator iterator = map.entrySet().iterator();
            boolean flag = false;
            StringBuffer stringbuffer = new StringBuffer("");
            stringbuffer.append("{");
            java.util.Map.Entry entry;
            for(; iterator.hasNext(); stringbuffer.append(ConvertionUtil.toJson(entry.getValue())))
            {
                entry = (java.util.Map.Entry)iterator.next();
                if(flag)
                    stringbuffer.append(",");
                flag = true;
                stringbuffer.append("\"" + ConvertionUtil.toValidJson(entry.getKey().toString()) + "\":");
            }

            stringbuffer.append("}");
            append(stringbuffer.toString());
        }
    }

    private void append(String s)
        throws IOException
    {
        writer.write(s);
    }

    BufferedWriter writer;
    private boolean writerClose;
    private boolean rsBool;
    private boolean dsBool;
}
