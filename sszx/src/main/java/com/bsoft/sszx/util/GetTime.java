package com.bsoft.sszx.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.bsoft.sszx.entity.zjqd.Zjqd;

public class GetTime {

	public static String gettime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(c.getTime());
	}
	
	
	public int checkOutTime(Zjqd zjqd){
		int isout = 0;
		
		int sx = zjqd.getSx();
		Timestamp zjsj = zjqd.getSxsj();
		
		
		
		
		return isout;
	}
	

}
