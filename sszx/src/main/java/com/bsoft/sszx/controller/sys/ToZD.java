package com.bsoft.sszx.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZdDao;
import com.bsoft.sszx.entity.zd.Zd;
import com.bsoft.sszx.entity.zd.ZdMx;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 短信维护
 */
@Controller
public class ToZD {
	
	@RequestMapping("to_zd")
	public String toZD() {
		return "sys/zdWh";
	}
	
	@RequestMapping("zdcx")
	public void zdcx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Zd> al = (List<Zd>) new ZdDao().findAll();
		
		JSONArray resultObj = JSONArray.fromObject(al);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	
	@RequestMapping("editZd")
	public void editZd(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String zdbm = (String)request.getParameter("zdbm");
		String zdmc = (String)request.getParameter("zdmc");
		Zd zd = new Zd();
		zd.setZdbm(zdbm);
		zd.setZdmc(zdmc);
		 new ZdDao().saveOrUpdateZd(zd);
		
//		JSONObject resultObj = JSONObject.fromObject(map);
//		JSONArray resultObj = JSONArray.fromObject(al);
//		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	
	@RequestMapping("delZd")
	public void delZd(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String zdbm = (String)request.getParameter("zdbm");
		String zdmc = (String)request.getParameter("zdmc");
		Zd zd = new Zd();
		zd.setZdbm(zdbm);
		zd.setZdmc(zdmc);
		new ZdDao().delZd(zd);
	}
	
	@RequestMapping("zdmxcx")
	public void zdmxcx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		if(request.getParameter("zdbm")==null){
			System.out.println("rrrrrrrrrrrrrrrrrrrr");
			return;
		}else{
			String zdbm = (String)request.getParameter("zdbm");
			List<ZdMx> al = new ZdDao().findZdMx(zdbm);
			JSONArray resultObj = JSONArray.fromObject(al);
			HttpHelper.renderJson(resultObj.toString(), response);
		}
		
		
	}
	
	
	@RequestMapping("delzdmx")
	public void delzdmx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String zdbm = (String)request.getParameter("zdbm");
		List<ZdMx> al = new ZdDao().findZdMx(zdbm);
		JSONArray resultObj = JSONArray.fromObject(al);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
}
