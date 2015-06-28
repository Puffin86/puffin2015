package com.bsoft.sszx.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	
	public static Map<String,Object> parseStr(String str){
		Map<String,Object> m = new HashMap<String, Object>();
		String[] sarr = str.split("&");
		for(int i=0;i<sarr.length;i++){
			String sitem = sarr[i];
			String[] insarr = sitem.split("=");
			if(insarr.length>1){
				m.put(insarr[0], insarr[1]);
			}
		}
		return m;
	}
}
