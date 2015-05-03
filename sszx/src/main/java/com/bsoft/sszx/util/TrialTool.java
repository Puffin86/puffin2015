package com.bsoft.sszx.util;

import com.google.gdata.util.common.util.Base64;
import com.google.gdata.util.common.util.Base64DecoderException;

public class TrialTool {
	
	public static String encode(String id){
		return Base64.encode(id.getBytes());
	}
	
	public static String decode(String str){
		try {
			return new String(Base64.decode(str));
		} catch (Base64DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(encode("2015-05-02"));
//		System.out.println(decode("MjAxNS0wNC0yNg=="));
	}

}
