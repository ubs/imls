package com.imls.sandbox.activeobject;

import java.util.Random;

import net.java.ao.DBParam;

public class ModelDefaults {
	
	private static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();

	public static String getRandomString(){
		return getRandomString(7);
	}
	
	public static String getRandomString(int length){
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ )
			sb.append(ModelDefaults.validCharacters.charAt(random.nextInt(ModelDefaults.validCharacters.length())));
		return sb.toString();
	}
	
	public static DBParam[] getUserDefaults(){
		DBParam defaultValues[] = {
			new DBParam(Users.USERNAME_FIELD, getRandomString()),
			new DBParam(Users.PASSWORD_FIELD, getRandomString())
		};
		return defaultValues;
	}

}
