package com.bsoft.sszx.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bsoft.sszx.entity.zjqd.Zjqd;

public class GetTime {

	public static String gettime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(c.getTime());
	}
	
	
	public static int checkOutTime(Zjqd zjqd) throws ParseException{
		int isout = 0;
		
		int sx = zjqd.getSx();
		Timestamp zjsj = zjqd.getSxsj();
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
		String zjsjstr = df.format(zjsj);
		Date zjsjDate = df.parse(zjsjstr);
		Calendar calzjsj = Calendar.getInstance();
		calzjsj.setTime(zjsjDate);
		
		long gap = (calzjsj.getTimeInMillis()-cal.getTimeInMillis())/(1000*3600*24);
		gap +=1;
		if(gap==0||gap<0){//超时
			isout = -1;
		}else{
			double dis = sx/3.0;
			if(gap>dis){//未接近1/3的时限 正常状态
				isout = 0;
			}else{//接近1/3时限
				isout = 1;
			}
		}
		return isout;
	}
	

}
