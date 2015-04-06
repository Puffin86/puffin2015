package com.bsoft.sszx.controller.sys;

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
 * 重置初始密码
 */
@Controller
public class ResetPass {

	@ResponseBody
	@RequestMapping("resetPass")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) request.getParameter("fydm");// 法院代码
		String yhid = (String) request.getParameter("yhid");// 转交人

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			UserDao userDao = new UserDao();
			User user = userDao.findUserById(yhid, fydm);
			user.setPass("1234");
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
