package com.bsoft.sszx.controller.flq;

import java.net.URLDecoder;
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

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONObject;

@Controller
public class UserSearch {

	@ResponseBody
	@RequestMapping("userSearch")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "UTF-8");
		name = URLDecoder.decode(name, "UTF-8");

		List<User> al = (List<User>) new UserDao().findUserByName(fydm, name);
		List<Tree> tree = new ArrayList<Tree>();
		for (int i = 0; i < al.size(); i++) {
			Tree leaf = new Tree();
			leaf.setId(i+"");
			leaf.setState("open");

			String yhbmid = al.get(i).getYhbm();

			String yhbm = new UserDao().findBm(yhbmid, fydm).getBmmc();
			String text = al.get(i).getYhxm() + "：" + yhbm;
			leaf.setText(text);

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("leaf", "true");
			String yhid = al.get(i).getId().getYhid();
			attributes.put("yhid", yhid);
			attributes.put("yhbm", yhbmid);
			leaf.setAttributes(attributes);
			
			tree.add(leaf);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", tree);
		JSONObject resultObj = JSONObject.fromObject(result); // 将map对象转换成为json对象
		HttpHelper.renderJson(resultObj.toString(), response);
	}
}
