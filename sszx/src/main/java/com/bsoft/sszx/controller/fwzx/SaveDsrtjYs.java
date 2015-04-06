package com.bsoft.sszx.controller.fwzx;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class SaveDsrtjYs  {
	@ResponseBody
	@RequestMapping("saveDsrtjYs")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
	
	    String id=request.getParameter("bh");
		
		
		String cl=request.getParameter("cl");
		cl = URLDecoder.decode(cl, "UTF-8"); 
		cl = URLDecoder.decode(cl, "UTF-8"); 
		
		if(cl.contains(";")){
		String[] clist=cl.split(";");
		ClbDao clbDao=new ClbDao();
		
		List<Clb> listClb=clbDao.findByZjqd(id, fydm);
		for(int i=0;i<listClb.size();i++){
			clbDao.delClb(listClb.get(i));
		}
		
		for(int i=0; i<clist.length;i++){
		  String[] clnr=clist[i].split(",");
		  Clb clb=new Clb();
		  ClbId clbid=new ClbId();
		  clb.setId(clbid);
		  clb.getId().setBh(Integer.valueOf(id));
		  clb.getId().setFydm(fydm);
		  clb.getId().setXh(i);
		  clb.setClmc(clnr[0]);
		  clb.setFs(Integer.valueOf(clnr[1]));
		  clb.setYs(Integer.valueOf(clnr[2]));
		  clbDao.saveClb(clb); 
		}}	
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
