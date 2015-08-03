package com.bsoft.sszx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
 * 退回材料
 */
@Controller
public class TuiHuiCL {

	@ResponseBody
	@RequestMapping("tuiHuiCL")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");
		String thdx = request.getParameter("thdx");

		Zjqd Zjqd = new ZjqdDao().findbyid(id, fydm);

		if (thdx.equals("1"))
			Zjqd.setZt(3);// 状态设置为退回诉讼中心

		if (thdx.equals("2")) {
			Zjqd.setLclx("flq");
			Zjqd.setZt(6);// 退回当事人
		}

		if (thdx.equals("3"))
			Zjqd.setZt(3);// 退回承办人

		if (thdx.equals("4"))
			Zjqd.setZt(3);// 当事人未取件退回承办人

		if (thdx.equals("5"))
			Zjqd.setZt(3);// 当事人未交件退回承办人

		String dqcyr = Zjqd.getDqcyr();
		String qscyr = Zjqd.getQscyr();

		String lzjl = "";
		UserDao userDao = new UserDao();
		String userid = (String) session.getAttribute("user");
		if (!thdx.equals("3") && !thdx.equals("5")) {
			String dqcyrName = userDao.findUserById(dqcyr, fydm).getYhxm();
			String qscyrName = userDao.findUserById(qscyr, fydm).getYhxm();

			lzjl = Zjqd.getLzjl() + "材料由接收人【" + dqcyrName + "】于【"
					+ GetTime.gettime() + "】退回给转交人【" + qscyrName + "】;";
		} else if (thdx.equals("3")) {
			String zjrxm = userDao.findUserById(userid, fydm).getYhxm();
			lzjl = Zjqd.getLzjl() + "材料由【"+zjrxm+"】于【" + GetTime.gettime()
					+ "】退回给承办人【" + Zjqd.getSjrXm() + "】;";
			Zjqd.setZjr(userid);
			Zjqd.setZjrXm(zjrxm);
		} else {
			String zjrxm = userDao.findUserById(userid, fydm).getYhxm();
			lzjl = Zjqd.getLzjl() + "【"+zjrxm+"】于【" + GetTime.gettime() + "】退回承办人【"
					+ Zjqd.getSjrXm() + "】的请求;";
			Zjqd.setZjr(userid);
			Zjqd.setZjrXm(zjrxm);
		}
		Zjqd.setLzjl(lzjl);

		if (thdx.equals("1")) {
			Zjqd.setDqcyr(qscyr);
			Zjqd.setQscyr("dsr");// 前手持有人当事人
			Zjqd.setHscyr(dqcyr);
		}

		if (thdx.equals("2")) {
			Zjqd.setDqcyr(qscyr);
			Zjqd.setHscyr("sszx");
			Zjqd.setQscyr(Zjqd.getSjr());
		}

		if (thdx.equals("3")) {
			Zjqd.setDqcyr(qscyr);
			Zjqd.setHscyr("sszx");
			Zjqd.setQscyr(Zjqd.getSjr());// 前手持有人为承办人
		}

		if (thdx.equals("4")) {
			Zjqd.setDqcyr(qscyr);
			Zjqd.setHscyr("sszx");
			Zjqd.setQscyr(Zjqd.getSjr());// 前手持有人为承办人
		}

		if (thdx.equals("5")) {
			Zjqd.setDqcyr(qscyr);
			Zjqd.setHscyr("sszx");
			Zjqd.setQscyr("fg");// 前手持有人为法官
		}

		String htyj = request.getParameter("htyj");
		htyj = URLDecoder.decode(htyj, "UTF-8");
		htyj = URLDecoder.decode(htyj, "UTF-8");
		Zjqd.setHtyj(htyj);
		Zjqd.setZjrq(GetTime.gettime());

		new ZjqdDao().saveZjqd(Zjqd);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("after", "1");

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
