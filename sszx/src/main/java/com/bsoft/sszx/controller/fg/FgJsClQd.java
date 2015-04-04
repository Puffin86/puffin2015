package com.bsoft.sszx.controller.fg;

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

@Controller
public class FgJsClQd {

	@ResponseBody
	@RequestMapping("fgJsClQd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);
		Zjqd.setZt(2);// 状态设置为接收

		String dqcyr = Zjqd.getDqcyr();

		UserDao userDao = new UserDao();
		String dqcyrName = userDao.findUserById(dqcyr, fydm).getYhxm();

		String lzjl = Zjqd.getLzjl() + "材料由收件人【" + dqcyrName + "】于【"
				+ GetTime.gettime() + "】确认接收;";
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
