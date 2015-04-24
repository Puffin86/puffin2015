package com.bsoft.sszx.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.DxxDao;
import com.bsoft.sszx.entity.sms.Dxx;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONObject;

/**
 * 短信
 */
@Controller
public class DxxList {

	@ResponseBody
	@RequestMapping("dxxList")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> map = new HashMap<String, Object>();

		List<Dxx> al = (List<Dxx>) new DxxDao().find(fydm);
		List<Tree> tree = new ArrayList<Tree>();

		for (int i = 0; i < al.size(); i++) {
			Tree leaf = new Tree();
			leaf.setId(i+"");
			leaf.setState("open");
			leaf.setText(al.get(i).getMc());

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("leaf", "true");
			int id = al.get(i).getId().getId();
			attributes.put("id", id + "");
			leaf.setAttributes(attributes);

			tree.add(leaf);
		}

		map.put("data", tree);

		JSONObject resultObj = JSONObject.fromObject(map);
		HttpHelper.renderJson(resultObj.toString(), response);
	}

}
