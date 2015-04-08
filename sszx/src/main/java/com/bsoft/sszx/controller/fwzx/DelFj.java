package com.bsoft.sszx.controller.fwzx;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

@Controller
public class DelFj  {
	@RequestMapping("delFj")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
	 String fydm=request.getParameter("fydm");
	    String bh=request.getParameter("bh");  
	    String xh=request.getParameter("xh"); 
	 try{
		FjDao fjbDao=new FjDao();
		Fjb fjb=fjbDao.findFjb(bh, xh, fydm);
		fjbDao.delFj(fjb);
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	 }catch (Exception e){
		e.printStackTrace();
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "0");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
   }
 }
}
