package phd.collins.imls.util;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class UtilGeneral {
	
	private static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();

	public static String getRandomString(){
		return getRandomString(7);
	}
	
	public static String getRandomStringLowerCase(){
		return getRandomString().toLowerCase();
	}
	
	public static String getRandomStringLowerCase(int length){
		return getRandomString(length).toLowerCase();
	}
	
	public static String getRandomString(int length){
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ )
			sb.append(UtilGeneral.validCharacters.charAt(random.nextInt(UtilGeneral.validCharacters.length())));
		return sb.toString();
	}
	
	public static String digestStringToMD5(String strPlain){
		String strHashed = DigestUtils.md5Hex(strPlain);
		return strHashed;
	}
}
