package phd.collins.imls.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	public static String getNowDateTime(){
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}
	
	public static Date getDateFromISO8601String(String strDate){
		strDate = strDate.replace('T', ' ');
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return getDateFromString(strDate, dateFormat);
	}
	
	public static Date getDateFromString(String strDate){
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return getDateFromString(strDate, dateFormat);
	}
	
	public static Date getDateFromString(String strDate, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date parsedDate;
		try {
			parsedDate = sdf.parse(strDate);
		} catch (ParseException e) {
			parsedDate = null;
			e.printStackTrace();
		}
		return parsedDate;
	}
}
