package com.vendetta.vms.utils;

import java.util.Random;

public class Utils {

	public static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwyz0123456789";

	public static Random rd = new Random();

	public static String randomString(int length) {

		char[] text = new char[length];
		for(int i = 0; i < length; i++)
			text[i] = CHARS.charAt(rd.nextInt(CHARS.length()-1));
		
		return new String(text);
	}

	
}
