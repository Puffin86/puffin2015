package com.bsoft.sszx.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.entity.user.User;

/**
 * 角色维护
 */
@Controller
public class Jswh {

	@RequestMapping("jueSeFP")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String yhid = request.getParameter("yhid");

		User user = (User) new UserDao().findUserById(yhid, fydm);

		session.setAttribute("editUser", user);

		return "sys/jueSeFP";
	}
	
}
