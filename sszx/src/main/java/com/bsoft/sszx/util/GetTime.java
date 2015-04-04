package com.bsoft.sszx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetTime {

	public static String gettime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(c.getTime());
	}

}
