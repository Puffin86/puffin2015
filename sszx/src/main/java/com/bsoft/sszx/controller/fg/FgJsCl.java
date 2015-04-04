package com.bsoft.sszx.controller.fg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 法官接收材料
 */
@Controller
public class FgJsCl {
	
	@RequestMapping("to_fgJsCl")
	public String toFgJsCl(){
		return "fg/fgJsCL";
	}

	@ResponseBody
	@RequestMapping("fgJsCl")
	public void findDsrZdSj(Integer page, Integer rows, String user,
			String fydm, HttpServletResponse response) {
		// 当前页
		int intPage = (page == null || page == 0) ? 1 : page;
		// 每页显示条数
		int number = (rows == null || rows == 0) ? 10 : rows;
		// 每页的开始记录 第一页为1, 第二页为number +1
		int start = (intPage - 1) * number;

		Map<String, Object> map = new HashMap<String, Object>();
		
		ZjqdDao zjqdDao = new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start, number, user, 1, fydm);
		List<Zjqd> all = zjqdDao.findDsrZzSJ(user, 1, fydm);

		map.put("total", all.size());
		map.put("rows", al);

		JSONObject json = JSONObject.fromObject(map);
		HttpHelper.renderJson(json.toString(), response);
	}

}
