package com.bsoft.sszx.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ClqdDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZdDao;
import com.bsoft.sszx.entity.clb.Clqd;
import com.bsoft.sszx.entity.user.User;
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
	
	
	@RequestMapping("zdcx2")
	public void zdcx2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Zd> al = (List<Zd>) new ZdDao().findAll();
		List<Zd> realal = new ArrayList<Zd>();
		
		//字典维护界面，隐藏字典列表下拉菜单里的角色、业务类型、状态三个选项。
		if(al!=null){
			for(Zd zd : al){
				String zdbm = zd.getZdbm();
				if("js".equals(zdbm) || "zt".equals(zdbm) || "ywlx".equals(zdbm)){
					continue;
				}
				realal.add(zd);
			}
		}
		
		
		JSONArray resultObj = JSONArray.fromObject(realal);
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
			return;
		}else{
			String zdbm = (String)request.getParameter("zdbm");
			List<ZdMx> al = new ZdDao().findZdMx(zdbm);
			JSONArray resultObj = JSONArray.fromObject(al);
			HttpHelper.renderJson(resultObj.toString(), response);
		}
	}
	
	
	@RequestMapping("zdmxcx_tree")
	public void zdmxcx_tree(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		if(request.getParameter("zdbm")==null){
			return;
		}else{
			String zdbm = (String)request.getParameter("zdbm");
			List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
			List<ZdMx> al = new ZdDao().findZdMx(zdbm);
			for(ZdMx bean : al){
				Map<String, Object> node = new HashMap<String, Object>();
				node.put("id", bean.getZdmxbm());
				node.put("text", bean.getZdmxmc());
				tree.add(node);
			}
			HttpHelper.renderJson(JSONArray.fromObject(tree).toString(), response);
		}
	}
	
	@RequestMapping("zdmxcx_thyy")
	public void zdmxcx_thyy(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String uid = (String)session.getAttribute("user");
		String zdbm = "thyy";
		String lclx = (String)request.getParameter("lclx");
		String cbr = (String)request.getParameter("cbr");
		List<ZdMx> al = new ZdDao().findZdMx(zdbm);
		List<ZdMx> retal = new ArrayList<ZdMx>();
		for(ZdMx zdmx : al){
			
			if(zdmx.getZdmxbm().equals("2")){//已过领取时效 
				if("flq".equals(lclx)){
					retal.add(zdmx);
				}else{
					continue;
				}
			}else if(zdmx.getZdmxbm().equals("3")){
				if("flj".equals(lclx)){
					retal.add(zdmx);
				}else{
					continue;
				}
			}else if(zdmx.getZdmxbm().equals("1")){
				if(uid.equals(cbr)){
					retal.add(zdmx);
				}else{
					continue;
				}
			}else{
				retal.add(zdmx);
			}
			
		}
		JSONArray resultObj = JSONArray.fromObject(retal);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	@RequestMapping("delzdmx")
	public void delzdmx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String zdbm = (String)request.getParameter("zdbm");
		String zdmxbm = (String)request.getParameter("zdmxbm");
		ZdMx zdmxBean = new ZdMx();
		zdmxBean.setZdbm(zdbm);
		zdmxBean.setZdmxbm(zdmxbm);
		new ZdDao().delZdMx(zdmxBean);
	}
	
	@RequestMapping("saveZdmx")
	public void saveZdmx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String zdbm = (String)request.getParameter("zdbm");
		String zdmxbm = (String)request.getParameter("zdmxbm");
		String zdmxmc = (String)request.getParameter("zdmxmc");
		String parent = (String)request.getParameter("parent");
		
		ZdMx zdmxBean = new ZdMx();
		zdmxBean.setParent(parent);
		zdmxBean.setZdbm(zdbm);
		zdmxBean.setZdmxbm(zdmxbm);
		zdmxBean.setZdmxmc(zdmxmc);
		new ZdDao().saveOrUpdateZdMx(zdmxBean);
	}
	
}
