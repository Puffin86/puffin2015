package com.bsoft.sszx.controller.sys;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.DxxDao;
import com.bsoft.sszx.entity.sms.Dxx;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

/**
 * 短信
 */
@Controller
public class SaveDxx {

	@ResponseBody
	@RequestMapping("saveDxx")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) request.getParameter("fydm");// 法院代码
		String id = (String) request.getParameter("id");
		String lx = (String) request.getParameter("lx");// 用户id

		String nr = request.getParameter("nr");
		nr = URLDecoder.decode(nr, "UTF-8");
		nr = URLDecoder.decode(nr, "UTF-8");

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			DxxDao dxxDao = new DxxDao();
			Dxx dxx = dxxDao.findDxx(id, fydm);
			dxx.setZdfs(Integer.valueOf(lx));
			dxx.setNr(nr);
			dxxDao.saveOrUpdateDxx(dxx);

			result.put("success", true);
			result.put("after", "1");
			result.put("zdfs", lx);
		} catch (Exception e) {
			result.put("success", true);
			result.put("after", "0");
		}

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
