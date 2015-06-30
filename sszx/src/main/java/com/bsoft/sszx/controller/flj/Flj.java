package com.bsoft.sszx.controller.flj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 预约当事人提交材料
 */
@Controller
public class Flj {

	@RequestMapping("to_flj")
	public String toFlq() {
		return "fg/flj";
	}

	@RequestMapping("addFlj")
	public String addFljJsp() {
		return "fg/addFlj";
	}

	@RequestMapping("editFlj")
	public String editFlj(HttpServletRequest request, HttpSession session) {
		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd zjqd = new ZjqdDao().findbyid(id, fydm);
		List<Clb> list = new ClbDao().findByZjqd(id, fydm);

		session.setAttribute("editFlj", zjqd);
		session.setAttribute("editFljClb", list);

		return "fg/editFlj";
	}

	@ResponseBody
	@RequestMapping("fljTable")
	public void findDsrZdSj(Integer page, Integer rows,String sort,String order, String user,
			String fydm, HttpServletResponse response) throws Exception {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();

		ZjqdDao zjqdDao = new ZjqdDao();

		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 9, fydm,sort,order);
		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 9, fydm);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject resultObj = JSONObject.fromObject(map);
		HttpHelper.renderJson(resultObj.toString(), response);
	}

}
