package com.bsoft.sszx.controller.fg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZdDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zd.Zd;
import com.bsoft.sszx.entity.zd.ZdMx;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 法官接收材料
 */
@Controller
public class FgJsCl {
	
	@RequestMapping("to_fgJsCl")
	public String toFgJsCl(){
		return "fg/fgJsCL";
	}
	
	@RequestMapping("to_fgYjsCl")
	public String toFgYjsCl(){
		return "fg/fgYjsCL";
	}
	
	@RequestMapping("to_clsx")
	public String to_clsx(HttpServletRequest request,HttpServletResponse response){
		int op = Integer.parseInt(request.getParameter("op").toString());
		String add = "";
		switch(op){
			case 1 :
				add = "fg/clsx_jjlq";
				break;
			case 2 :
				add = "fg/clsx_cglq";
				break;
			case 3 :
				add = "fg/clsx_jjtj";
				break;
			case 4 :
				add = "fg/clsx_cgtj";
				break;
			default :
				add = "fg/clsx";
				break;
		}
		return add;
		
	}

	
	@ResponseBody
	@RequestMapping("clcx")
	public void clcx(String sort,String order,Integer page, Integer rows, String user,
			String fydm,String lclx,String type, HttpServletResponse response) {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();
		
		ZjqdDao zjqdDao = new ZjqdDao();
		int size = 0;
		List<Zjqd> al = new ArrayList<Zjqd>();
		
		if("jj".equals(type)&&"flq".equals(lclx)){
			al = zjqdDao.findjjsx(fydm, user, lclx,start,number,"6,7");
			size = zjqdDao.countjjsx(fydm, user, lclx,"6,7");
		}else if("jj".equals(type)&&"flj".equals(lclx)){
			al = zjqdDao.findjjsx(fydm, user, lclx,start,number,"10");
			size = zjqdDao.countjjsx(fydm, user, lclx,"10");
		}else if("cg".equals(type) && "flq".equals(lclx)){
			al = zjqdDao.findcgsx(fydm, user, lclx,start,number,"6,7");
			size = zjqdDao.countcgsx(fydm, user, lclx,"6,7");
		}else if("cg".equals(type) && "flj".equals(lclx)){
			al = zjqdDao.findcgsx(fydm, user, lclx,start,number,"10");
			size = zjqdDao.countcgsx(fydm, user, lclx,"10");
		}
		
		map.put("total", size);
		map.put("rows", al);

		JSONObject json = JSONObject.fromObject(map);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	@ResponseBody
	@RequestMapping("fgJsCl")
	public void findDsrZdSj(Integer page, Integer rows,String sort,String order, String user,
			String fydm, HttpServletResponse response) {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();
		
		ZjqdDao zjqdDao = new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 1, fydm,sort,order);
		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 1, fydm);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject json = JSONObject.fromObject(map);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	
	@ResponseBody
	@RequestMapping("fgYjsCl")
	public void fgYjsCl(String sort,String order,Integer page, Integer rows, String user,
			String fydm,String lclx, HttpServletResponse response) {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();
		
		ZjqdDao zjqdDao = new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzYSJbyPage(start, number, user, 8, fydm,lclx,sort,order);
		List<Zjqd> all = zjqdDao.findDsrZzYSJ(user, 8, fydm,lclx);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject json = JSONObject.fromObject(map);
		HttpHelper.renderJson(json.toString(), response);
	}
	

}
