package com.bsoft.sszx.controller.fwzx;//保存当事人转接清单


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;

@Controller
public class DjywSjsj  {
	
	@RequestMapping("djdsrSjsj")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		List<Clb> list=new ClbDao().findByZjqd(id, fydm);
		
		session.setAttribute("djdsrSjZjqd", Zjqd);
		session.setAttribute("djdsrSjZjqdClb", list);
		return "fwzx/djdsrSjsj";
	}

}
