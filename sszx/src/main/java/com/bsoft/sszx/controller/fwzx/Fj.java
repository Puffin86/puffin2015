package com.bsoft.sszx.controller.fwzx;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.hibernate.HibernateUtil;
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
	
	@ResponseBody
	@RequestMapping("canclAddDsrCl")
	public void canclAddDsrCl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
		String fydm=(String)session.getAttribute("fydm");
		String bh=request.getParameter("bh");
		List<Fjb> fjb =(List<Fjb>) new FjDao().fjlist(bh, fydm);
		
		Session hibsession = HibernateUtil.getSession(); // 获取Session
		hibsession.beginTransaction();
		if(fjb.size()>0){
			for(Fjb fjBean : fjb){
				hibsession.delete(fjBean);
			}
		}
		hibsession.getTransaction().commit();
		
		String serverRealPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
		if(fjb.size()>0){
			for(Fjb fjBean : fjb){
				String fileName = fjBean.getFjdz();
				File file = new File(serverRealPath+"\\"+fileName);  
			    if (file.exists() && file.isFile()) {  // 不存在返回 false  
			    	file.delete();  
			    }
				
			}
		}
		JSONArray json = JSONArray.fromObject(fjb);
		HttpHelper.renderJson(json.toString(), response);
		
	}
	
	
	
}
