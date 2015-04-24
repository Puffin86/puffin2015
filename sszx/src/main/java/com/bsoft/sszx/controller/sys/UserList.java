package com.bsoft.sszx.controller.sys;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

/**
 * 用户
 */
@Controller
public class UserList {

	@ResponseBody
	@RequestMapping("userList")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> map = new HashMap<String, Object>();
		
		String bm = request.getParameter("bm");
		bm = URLDecoder.decode(bm, "UTF-8");
		bm = URLDecoder.decode(bm, "UTF-8");

		List<User> al = (List<User>) new UserDao().findUserByBm(bm, fydm);
		List<Tree> tree = new ArrayList<Tree>();
		
		for (int i = 0; i < al.size(); i++) {
			Tree leaf = new Tree();
			leaf.setId(i+"");
			leaf.setState("open");
			leaf.setText(al.get(i).getYhxm());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("leaf", "true");
			String yhid = al.get(i).getId().getYhid();
			attributes.put("yhid", yhid);
			leaf.setAttributes(attributes);
			
			tree.add(leaf);
		}

		map.put("data", tree);
		
		JSONObject resultObj = JSONObject.fromObject(map); 
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	@ResponseBody
	@RequestMapping("userListByJs")
	public void userListByJs(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String bmdm = request.getParameter("bmdm");
		String js = request.getParameter("js");
		
		List<User> al = (List<User>) new UserDao().findUserByJs(js,fydm,bmdm);
		
		JSONArray resultObj = JSONArray.fromObject(al); 
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
}
