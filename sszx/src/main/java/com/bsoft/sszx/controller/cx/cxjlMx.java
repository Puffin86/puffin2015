package com.bsoft.sszx.controller.cx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
@Controller
public class cxjlMx  {
	@RequestMapping("cxjlMx")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception
	{		
	String fydm=(String)session.getAttribute("fydm");
	String id=request.getParameter("yhid");
	
	Zjqd zjqd =(Zjqd) new ZjqdDao().findbyid(id, fydm);
	session.setAttribute("cljlMx",zjqd);
	zjqd.setLzjl(zjqd.getLzjl().replaceAll(";", ";\r\n"));
	System.out.println(zjqd.getLzjl());
	return "cx/cxjlMx";
	}
}
