package com.bsoft.sszx.controller.fwzx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Fj  { 
	
	@RequestMapping("fj")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
	String fydm=(String)session.getAttribute("fydm");
	String bh=request.getParameter("bh");
	session.setAttribute("fjbh",bh);
	return "fwzx/addFj";
	}
}
