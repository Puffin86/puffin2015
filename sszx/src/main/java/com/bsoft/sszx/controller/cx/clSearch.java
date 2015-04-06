package com.bsoft.sszx.controller.cx;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONObject;

@Controller
public class clSearch { 
	
	@ResponseBody
	@RequestMapping("clSearch")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception
	{
	String fydm=(String)session.getAttribute("fydm");
	Map map = new HashMap();
	String ah=request.getParameter("ah");
	ah = URLDecoder.decode(ah, "UTF-8"); 
	ah = URLDecoder.decode(ah, "UTF-8"); 
	
	String dsr=request.getParameter("dsr");
	dsr = URLDecoder.decode(dsr, "UTF-8"); 
	dsr = URLDecoder.decode(dsr, "UTF-8"); 
	
	String sjrbm=request.getParameter("sjrbm");
	sjrbm = URLDecoder.decode(sjrbm, "UTF-8"); 
	sjrbm = URLDecoder.decode(sjrbm, "UTF-8"); 
	
	String user=(String)session.getAttribute("user");
	String lx=request.getParameter("lx");
	
	List<Zjqd> al =(List<Zjqd>) new ZjqdDao()
	    .findCljl(user, lx, dsr, ah, fydm, sjrbm);
	
	
	List<Tree> tree=(List<Tree>) new ArrayList();
	for(int i=0; i<al.size();i++){
		Tree leaf=new Tree();
		leaf.setId(i);
		leaf.setState("open");
		
		int id=al.get(i).getId().getBh();
		
		String text="【案号："+al.get(i).getAh()+"】"
				+" 当事人："+al.get(i).getDjr()
				+" 承办人："+al.get(i).getSjrXm()
				+" 承办部门："+al.get(i).getSjrBmmc();
		
		leaf.setText(text);
		
		Map attributes=new HashMap<String, String>();
		attributes.put("leaf", "true");
		attributes.put("yhid", id);
		leaf.setAttributes(attributes);
		tree.add(leaf);
	}	
	map.put("data", tree);
	JSONObject resultObj=JSONObject.fromObject(map); //将map对象转换成为json对象
	HttpHelper.renderJson(resultObj.toString(), response);
	}
}
