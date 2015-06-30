package com.bsoft.sszx.dao;

import java.util.HashMap;
import java.util.Map;

public class HomeDao {
	
	public Map<String,Integer> getHomeData(String fydm,String user,String userBm,String userJs){
		ZjqdDao dao = new ZjqdDao();
		Map<String,Integer> homeInfo = new HashMap<String, Integer>();
		
		//材料退回数量
		int htN=dao.countByZt(fydm, user, 3);
		//待接收材料数量
		int drN=dao.countByZt(fydm, user, 1);
		//案件被领取数量
		int jsN=dao.countByZt8(fydm, userBm, 8);
		//接近领取时限
		int jjlqsx=0;
		//超过领取时限
		int cglqsx=0;
		//接近提交时限
		int jjtjsx=0;
		//超过提交时限
		int cgtjsx=0;
		//服务中心人员或者系统管理员
		if("3".equals(userJs) || "1".equals(userJs)){
			jjlqsx=dao.countjjsx_fwzx(fydm, user,"flq","6,7");
			cglqsx=dao.countcgsx_fwzx(fydm, user,"flq","6,7");
			jjtjsx=dao.countjjsx_fwzx(fydm, user,"flj","10");
			cgtjsx=dao.countcgsx_fwzx(fydm, user,"flj","10");
		}else{//法官 2
			jjlqsx=dao.countjjsx(fydm, user,"flq","6,7");
			cglqsx=dao.countcgsx(fydm, user,"flq","6,7");
			jjtjsx=dao.countjjsx(fydm, user,"flj","10");
			cgtjsx=dao.countcgsx(fydm, user,"flj","10");
		}
		
		
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
