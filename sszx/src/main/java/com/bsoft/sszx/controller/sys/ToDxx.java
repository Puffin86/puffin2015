package com.bsoft.sszx.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.DxxDao;
import com.bsoft.sszx.entity.sms.Dxx;

/**
 * 短信维护
 */
@Controller
public class ToDxx {
	
	@RequestMapping("to_dxx")
	public String toDxx() {
		return "sys/dxx";
	}
	
	@RequestMapping("dxxSz")
	public String dxxSz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("id");

		Dxx Dxx = (Dxx) new DxxDao().findDxx(id, fydm);
		session.setAttribute("editDxx", Dxx);

		return "sys/dxxSz";
	}

}
