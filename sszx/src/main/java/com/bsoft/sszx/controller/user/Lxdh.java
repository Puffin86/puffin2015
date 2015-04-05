package com.bsoft.sszx.controller.user;

import java.util.HashMap;
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

import net.sf.json.JSONObject;

/**
 * 联系电话
 */
@Controller
public class Lxdh {

	@RequestMapping("to_lxdh")
	public String setLxdh() {
		return "user/lxdh";
	}

	@ResponseBody
	@RequestMapping("saveLxdh")
	public void saveLxdh(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String fydm = (String) session.getAttribute("fydm");
		String userid = (String) session.getAttribute("user");

		String pas = request.getParameter("lxdh");

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			UserDao userDao = new UserDao();
			User user = userDao.findUserById(userid, fydm);
			user.setLxdh(pas);
			userDao.saveOrUpdateUser(user);

			result.put("success", true);
			result.put("after", "1");
		} catch (Exception e) {
			result.put("success", true);
			result.put("after", "0");
		}

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
