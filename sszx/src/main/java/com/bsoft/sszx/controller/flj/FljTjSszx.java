package com.bsoft.sszx.controller.flj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;

/**
 * 通知当事人提交材料
 */
@Controller
public class FljTjSszx {

	@RequestMapping("fljTjSszx")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd zjqd = new ZjqdDao().findbyid(id, fydm);

		session.setAttribute("fljtjSsZx", zjqd);

		return "fg/fljTjSszx";
	}

}
