package phd.collins.imls.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	public static String getNowDateTime(){
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}
}
