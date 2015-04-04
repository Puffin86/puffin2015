package com.bsoft.sszx.controller.fwzx;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class searchDjyw {
	
	@ResponseBody
	@RequestMapping("searchDjyw")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String djr=request.getParameter("djr");
		djr = URLDecoder.decode(djr, "UTF-8"); 
		djr = URLDecoder.decode(djr, "UTF-8"); 
		
		String ah=request.getParameter("ah");
		ah = URLDecoder.decode(ah, "UTF-8"); 
		ah = URLDecoder.decode(ah, "UTF-8"); 
		
		int zt=Integer.valueOf(request.getParameter("zt"));//状态

		String fydm=(String)session.getAttribute("fydm");
		
		List<Zjqd> resultList =(List<Zjqd>) new ZjqdDao()
		       .findDsrZzSJ_Search_2(zt, djr, ah, fydm);
		Map result = new HashMap();
		result.put("success", true);
		result.put("data", resultList);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
