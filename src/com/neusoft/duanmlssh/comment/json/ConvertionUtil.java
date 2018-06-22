package com.neusoft.duanmlssh.comment.json;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;

@SuppressWarnings("rawtypes")
public class ConvertionUtil {

	public static String toValidJson(String s) {
		if (s != null)
			return s.replaceAll("([\"\\\\])", "\\\\$1")
					.replaceAll("[\f]", "\\\\f").replaceAll("[\b]", "\\\\b")
					.replaceAll("[\n]", "\\\\n").replaceAll("[\t]", "\\\\t")
					.replaceAll("[\r]", "\\\\r");
		else
			return s;
	}

	@SuppressWarnings("unchecked")
	public static Object constructObject(Object obj, int i) {
		if ((obj instanceof JSONObject) && ((JSONObject) obj).isNullObject())
			return null;
		Object obj1 = null;
		String s = obj.toString();
		if (s == null || "".equals(s))
			return s;
		if (i == 91)
			obj1 = new Date((new Long(s)).longValue());
		else if (i == 93)
			obj1 = new Timestamp((new Long(s)).longValue());
		else
			try {
				Class class1 = Class.forName(sqlType2JavaType(i));
				obj1 = class1.getConstructor(
						new Class[] { java.lang.String.class }).newInstance(
						new Object[] { s });
			} catch (Exception e) {
				e.printStackTrace();
			}
		return obj1;
	}
	
	public static String toJson(Object obj)
    {
        if(obj == null)
            return "null";
        if((obj instanceof Number) || (obj instanceof Boolean))
            return obj.toString();
        if(obj instanceof Date)
            return ((Date)obj).getTime() + "";
        else
            return "\"" + toValidJson(obj.toString()) + "\"";
    }
	
	public static String sqlType2JavaType(int i)
    {
        switch(i)
        {
        case 4: // '\004'
            return "java.lang.Integer";

        case 8: // '\b'
            return "java.lang.Double";

        case 6: // '\006'
            return "java.lang.Float";

        case 3: // '\003'
            return "java.lang.Long";

        case 5: // '\005'
        case 7: // '\007'
        default:
            return "java.lang.String";
        }
    }
	
	public static int javaTypeToSqlType(String s)
    {
        byte byte0 = 12;
        if(s.equals("java.lang.Integer"))
            byte0 = 4;
        else
        if(s.equals("java.lang.Long"))
            byte0 = 4;
        else
        if(s.equals("java.lang.Double"))
            byte0 = 8;
        else
        if(s.equals("java.sql.Date"))
            byte0 = 91;
        else
        if(s.equals("java.sql.Timestamp"))
            byte0 = 93;
        else
        if(s.equals("java.lang.Float"))
            byte0 = 6;
        else
        if(s.equals("java.math.BigDecimal"))
            byte0 = 3;
        else
        if(s.equals("java.math.BigInteger"))
            byte0 = -5;
        return byte0;
    }
}
