package com.bsoft.sszx.controller.fwzx;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;

@Controller
public class word  { 
	
	@ResponseBody
	@RequestMapping("word")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
	String fydm=(String)session.getAttribute("fydm");
	
	String bh=request.getParameter("bh");
	
	
	
	Zjqd Zjqd=new ZjqdDao().findbyid(bh, fydm);
	session.setAttribute("wordZjqd",Zjqd);
	
	List clbList=new ClbDao().findByZjqd(bh, fydm);
	session.setAttribute("wordclb",clbList);
	return "fwzx/word";
	}
}
