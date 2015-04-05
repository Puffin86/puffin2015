package com.bsoft.sszx.controller.flq;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

/**
 * 通知诉讼中心取件 - 确认
 */
@Controller
public class FlqTjSszxTz {

	@ResponseBody
	@RequestMapping("flqTjSszxTz")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);
		Zjqd.setZt(6);// 状态设置为诉讼中心待取件

		String dqcyr = Zjqd.getDqcyr();
		String hscyr = Zjqd.getHscyr();

		String lzjl = Zjqd.getLzjl() + "材料由承办人【" + Zjqd.getSjrXm() + "】于【"
				+ GetTime.gettime() + "】通知诉讼服务中心接收;";
		Zjqd.setLzjl(lzjl);

		Zjqd.setDqcyr(hscyr);
		Zjqd.setQscyr(dqcyr);
		Zjqd.setHscyr("dsr");// 后手持有人当事人
		Zjqd.setZjrq(GetTime.gettime());

		new ZjqdDao().saveZjqd(Zjqd);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("after", "1");

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
