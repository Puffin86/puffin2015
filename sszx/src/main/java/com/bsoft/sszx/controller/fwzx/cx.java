package com.bsoft.sszx.controller.fwzx;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;

@Controller
public class cx  {
	@RequestMapping("cx")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
		
		String fydm=(String)session.getAttribute("fydm");
	    String id=request.getParameter("bh");
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		session.setAttribute("cxZzSj", Zjqd);
		return "fwzx/cx";
	}

}
