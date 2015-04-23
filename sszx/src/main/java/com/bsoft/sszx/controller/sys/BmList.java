package com.bsoft.sszx.controller.sys;

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
import com.bsoft.sszx.entity.user.BmBean;
import com.bsoft.sszx.entity.user.Bmb;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

/**
 * 部门
 */
@Controller
public class BmList {

	@ResponseBody
	@RequestMapping("bmList")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> result = new HashMap<String, Object>();

		List<Bmb> al = (List<Bmb>) new UserDao().findBmAll(fydm);
		List<Tree> tree = new ArrayList<Tree>();

		for (int i = 0; i < al.size(); i++) {
			Tree leaf = new Tree();
			leaf.setId(i);
			leaf.setState("open");
			leaf.setText(al.get(i).getBmmc());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("opened", "false");
			leaf.setAttributes(attributes);

			tree.add(leaf);
		}

		result.put("data", tree);

		JSONObject resultObj = JSONObject.fromObject(result);
		HttpHelper.renderJson(resultObj.toString(), response);
	}

	
	@ResponseBody
	@RequestMapping("bmList2")
	public void bmList2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String fydm = (String) session.getAttribute("fydm");
		List<BmBean> al = (List<BmBean>) new UserDao().findBm(fydm);
//		al.add(new BmBean("all","all","全部"));
		JSONArray resultObj = JSONArray.fromObject(al);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
}
