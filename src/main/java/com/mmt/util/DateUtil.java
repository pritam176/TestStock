/**
 * 
 */
package com.mmt.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author pkumar
 *
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public Date getSQLFromString(String date){
		return null;
		
	}

	public static String getDateAsString(java.util.Date d){
		return sdf.format(d);
	}
	
	public static String getDateTimeAsString(java.util.Date d){
		return sdfTime.format(d);
	}
	
	
	public Date getCurrentDate(){
		return null;
		
	}

}
