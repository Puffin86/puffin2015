package com.bsoft.sszx.controller.fwzx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class Fj  { 
	
	@RequestMapping("fj")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
		String fydm=(String)session.getAttribute("fydm");
		String bh=request.getParameter("bh");
		String path=request.getSession().getServletContext().getRealPath("/scan/jpg/");
		System.out.println(path);
		path = path.replaceAll("\\\\", "/");
		System.out.println(path);
		session.setAttribute("fjbh",bh);
		session.setAttribute("path",path);
		return "fwzx/addFj";
	}
	
	@ResponseBody
	@RequestMapping("fjcx")
	public void fjcx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
		String fydm=(String)session.getAttribute("fydm");
		String bh=request.getParameter("bh");
		List<Fjb> fjb =(List<Fjb>) new FjDao().fjlist(bh, fydm);
		
		JSONArray json = JSONArray.fromObject(fjb);
		HttpHelper.renderJson(json.toString(), response);
		
	}
	
}
