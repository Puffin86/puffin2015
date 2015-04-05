package com.bsoft.sszx.controller.flq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;

/**
 * 通知诉讼中心取件
 */
@Controller
public class FlqTjSszx {

	@RequestMapping("flqTjSsZx")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);

		session.setAttribute("flqtjSsZx", Zjqd);

		return "fg/flqTjSszx";
	}

}
