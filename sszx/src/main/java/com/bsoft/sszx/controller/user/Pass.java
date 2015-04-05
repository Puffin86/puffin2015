package com.bsoft.sszx.controller.user;

import java.net.URLDecoder;
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
 * 修改密码
 */
@Controller
public class Pass {

	@RequestMapping("to_pass")
	public String setPass() {
		return "user/pass";
	}

	@ResponseBody
	@RequestMapping("savePass")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String userid = (String) session.getAttribute("user");

		String pas = request.getParameter("pas");
		pas = URLDecoder.decode(pas, "UTF-8");
		pas = URLDecoder.decode(pas, "UTF-8");

		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			UserDao userDao = new UserDao();
			User user = userDao.findUserById(userid, fydm);
			
			user.setPass(pas);
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
