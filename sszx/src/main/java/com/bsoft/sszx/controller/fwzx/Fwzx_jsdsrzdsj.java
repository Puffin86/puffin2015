package com.bsoft.sszx.controller.fwzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;
/**
 * 服务中心处理流程
 * @author shaobt
 *
 */
@Controller
public class Fwzx_jsdsrzdsj {
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_jsdsrzdsj")
	public String jsdsrzdsj(){
		return "fwzx/jsdsrzdsj";
	}
	
	@RequestMapping("adddsrcl")
	public String add_dsrcl(){
		return "fwzx/addDsrCL";
	}
	
	/**
	 * 接收当事人主动送件 grid查询
	 * @param page
	 * @param rows
	 * @param user
	 * @param fydm
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("jsdsrzdsj")
	public void findDsrZdSj(String sort,String order,Integer page, Integer rows, String user,
			String fydm, HttpServletResponse response) {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();
		
		ZjqdDao zjqdDao = new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 0, fydm,sort,order);
		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 0, fydm);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject json = JSONObject.fromObject(map);
		HttpHelper.renderJson(json.toString(), response);
		
	}
	
	
//	@ResponseBody
//	@RequestMapping("jsdsrzdsj")
//	public void findDsrZdSj(HttpServletRequest request, HttpServletResponse response) {
//		int page = Integer.parseInt(request.getParameter("page"));
//		int rows = Integer.parseInt(request.getParameter("rows"));
//		String user = (String)request.getParameter("user");
//		String fydm = (String)request.getParameter("fydm");
//		Object ss = request.getParameter("sortName");
//		// 当前页
//		int intPage = page == 0 ? 1 : page;
//		// 每页显示条数
//		int number = rows == 0 ? 10 : rows;
//		// 每页的开始记录 第一页为1, 第二页为number +1
//		int start = (intPage - 1) * number;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		ZjqdDao zjqdDao = new ZjqdDao();
//		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 0, fydm);
//		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 0, fydm);
//
//		map.put("total", all.size());
//		map.put("rows", al);
//
//		JSONObject json = JSONObject.fromObject(map);
//		HttpHelper.renderJson(json.toString(), response);
//		
//	}
	
	
	@ResponseBody
	@RequestMapping("searchDsrZzSj")
	public void findDsrZdSjBydjr_ah(String djr, String ah, String user,
			String fydm, HttpServletResponse response) {
		
		List<Zjqd> resultList =(List<Zjqd>) new ZjqdDao()
		       .findDsrZzSJ_Search(user, 0, djr, ah, fydm);

		JSONArray json = JSONArray.fromObject(resultList);
		HttpHelper.renderJson(json.toString(), response);
		
	}
	
	
}
