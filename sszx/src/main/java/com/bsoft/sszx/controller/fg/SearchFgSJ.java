package com.bsoft.sszx.controller.fg;

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

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 查询法官收件
 */
@Controller
public class SearchFgSJ {

	@ResponseBody
	@RequestMapping("searchFgSJ")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String djr = request.getParameter("djr");
		String ah = request.getParameter("ah");
		System.out.println(djr+"@"+ah);
		int zt = Integer.valueOf(request.getParameter("zt")); // 状态

		String user = (String) session.getAttribute("user");
		String fydm = (String) session.getAttribute("fydm");

		List<Zjqd> resultList = (List<Zjqd>) new ZjqdDao().findDsrZzSJ_Search(
				user, zt, djr, ah, fydm);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("data", resultList);

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
