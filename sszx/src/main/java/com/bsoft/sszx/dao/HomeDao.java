package com.bsoft.sszx.dao;

import java.util.HashMap;
import java.util.Map;

public class HomeDao {
	
	public Map<String,Integer> getHomeData(String fydm,String user,String userBm){
		ZjqdDao dao = new ZjqdDao();
		Map<String,Integer> homeInfo = new HashMap<String, Integer>();
		
		int htN=dao.countByZt(fydm, user, 3);
		int drN=dao.countByZt(fydm, user, 1);
		int jsN=dao.countByZt8(fydm, userBm, 8);
		int jjlqsx=dao.countjjsx(fydm, user,"flq","6,7");
		int cglqsx=dao.countcgsx(fydm, user,"flq","6,7");
		int jjtjsx=dao.countjjsx(fydm, user,"flj","10");
		int cgtjsx=dao.countcgsx(fydm, user,"flj","10");
		
		homeInfo.put("htN", htN);
		homeInfo.put("drN", drN);
		homeInfo.put("jsN", jsN);
		homeInfo.put("jjlqsx", jjlqsx);
		homeInfo.put("cglqsx", cglqsx);
		homeInfo.put("jjtjsx", jjtjsx);
		homeInfo.put("cgtjsx", cgtjsx);
		
		return homeInfo;
	}
	
}
