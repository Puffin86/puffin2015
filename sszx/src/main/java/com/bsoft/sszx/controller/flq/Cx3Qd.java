package com.bsoft.sszx.controller.flq;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

/**
 * 撤销确定
 */
@Controller
public class Cx3Qd {

	@ResponseBody
	@RequestMapping("cx3Qd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);
		Zjqd.setZt(20);// 状态设置为撤销

		String dqcyr = Zjqd.getDqcyr();

		UserDao userDao = new UserDao();
		String zjrName = userDao.findUserById(dqcyr, fydm).getYhxm();

		String lzjl = Zjqd.getLzjl() + "任务由创建人【" + zjrName + "】于【"
				+ GetTime.gettime() + "】撤销;";
		Zjqd.setLzjl(lzjl);

		Zjqd.setZjrq(GetTime.gettime());

		new ZjqdDao().saveZjqd(Zjqd);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("after", "1");

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
