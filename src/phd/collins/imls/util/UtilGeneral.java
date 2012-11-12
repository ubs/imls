package phd.collins.imls.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class UtilGeneral {
	
	private static final String validNumbers = "0123456789";
	private static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();

	public static String getRandomString(){
		return getRandomString(7);
	}
	
	public static String getRandomString(int length){
		return getRandomCharacters(length, UtilGeneral.validCharacters);
	}
	
	public static String getRandomStringLowerCase(){
		return getRandomString().toLowerCase();
	}
	
	public static String getRandomStringLowerCase(int length){
		return getRandomString(length).toLowerCase();
	}
	
	public static String getRandomNumericString(int length){
		return getRandomCharacters(length, UtilGeneral.validNumbers);
	}
	
	private static String getRandomCharacters(int length, String strCharacterSet){
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ )
			sb.append(strCharacterSet.charAt(random.nextInt(strCharacterSet.length())));
		return sb.toString();
	}
	
	public static String digestStringToMD5(String strPlain){
		String strHashed = DigestUtils.md5Hex(strPlain);
		return strHashed;
	}
	
	public static boolean isNull(Object obj){
		return (obj == null);
	}
	
	public static void dumpHashTable(Hashtable<?, ?> hashTable){
		Info.sout("UtilGeneral Dump HashTable, Size: " + hashTable.size());
		
		Enumeration<?> hashKeys = hashTable.keys();
		while( hashKeys.hasMoreElements() ) {
		  Object hKey = hashKeys.nextElement();
		  Object hValue = hashTable.get(hKey);
		  Info.sout("Key [Value] : " + hKey + " [" + hValue + "]");
		}
	}
	
}
