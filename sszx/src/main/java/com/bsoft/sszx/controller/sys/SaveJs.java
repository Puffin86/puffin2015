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
 * 保存角色
 */
@Controller
public class SaveJs {

	@ResponseBody
	@RequestMapping("saveJs")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) request.getParameter("fydm");// 法院代码
		String yhid = (String) request.getParameter("yhid");// 用户id
		String lx = (String) request.getParameter("lx");// 用户id

		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			UserDao userDao = new UserDao();
			User user = userDao.findUserById(yhid, fydm);
			user.setJs(lx);
			userDao.saveOrUpdateUser(user);

			result.put("success", true);
			result.put("after", "1");
			result.put("js", lx);
		} catch (Exception e) {
			e.printStackTrace();
			
			result.put("success", true);
			result.put("after", "0");
		}
		
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
