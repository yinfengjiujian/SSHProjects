package com.neusoft.duanmlssh.comment;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String dataFormate(Date date){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormater.format(date);
	}
	
	public static Date dataFormateDate(Date date){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date utilDate = dateFormater.parse(dateFormater.format(date),pos);
		java.sql.Date dateTime = new java.sql.Date(utilDate.getTime());
		return dateTime;
	}
	
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
}
