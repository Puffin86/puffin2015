package com.bsoft.sszx.controller.flq;

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
 * 预约当事人领取材料
 */
@Controller
public class Flq {

	@RequestMapping("to_flq")
	public String toFlq() {
		return "fg/flq";
	}
	
	@RequestMapping("addFlq")
	public String addFlq() {
		return "fg/addFlq";
	}

	@RequestMapping("editFlq")
	public String editFlq(HttpServletRequest request, HttpSession session) {
		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);
		List<Clb> list = new ClbDao().findByZjqd(id, fydm);

		session.setAttribute("editFlq", Zjqd);
		session.setAttribute("editFlqClb", list);

		return "fg/editFlq";
	}

	@ResponseBody
	@RequestMapping("flqTable")
	public void findDsrZdSj(Integer page, Integer rows,String sort,String order, String user,
			String fydm, HttpServletResponse response) {

		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();

		ZjqdDao zjqdDao = new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 5, fydm,sort,order);// 每页的数据，放入list
		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 5, fydm);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject resultObj = JSONObject.fromObject(map);
		HttpHelper.renderJson(resultObj.toString(), response);
	}

}
