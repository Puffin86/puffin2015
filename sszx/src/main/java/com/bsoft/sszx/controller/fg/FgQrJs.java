package com.bsoft.sszx.controller.fg;//保存当事人转接清单

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;

/**
 * 法官确认接收
 */
@Controller
public class FgQrJs {

	@RequestMapping("to_fgQrJs")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);
		List<Clb> list = new ClbDao().findByZjqd(id, fydm);

		session.setAttribute("fgQrJsZjqd", Zjqd);
		session.setAttribute("fgQrJsZjqdClb", list);

		return "fg/fgQrJs";
	}

}
