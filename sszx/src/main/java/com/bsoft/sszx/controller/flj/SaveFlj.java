package com.bsoft.sszx.controller.flj;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.entity.zjqd.ZjqdId;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

@Controller
public class SaveFlj {

	@ResponseBody
	@RequestMapping("saveFlj")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			Zjqd zjqd = new Zjqd();
			ZjqdDao zjqdDao = new ZjqdDao();
			int bh = zjqdDao.getMaxId(fydm);

			ZjqdId zjqdId = new ZjqdId();
			zjqd.setId(zjqdId);
			zjqd.getId().setBh(bh);
			zjqd.getId().setFydm(fydm);

			String ah = request.getParameter("ah");
			ah = URLDecoder.decode(ah, "UTF-8");
			ah = URLDecoder.decode(ah, "UTF-8");
			zjqd.setAh(ah);
			
			String ahdm = request.getParameter("ahdm");
			ahdm = URLDecoder.decode(ahdm, "UTF-8");
			ahdm = URLDecoder.decode(ahdm, "UTF-8");
			zjqd.setAhdm(ahdm);

			String sjrXm = request.getParameter("sjrXm");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			zjqd.setSjrXm(sjrXm);

			String sjrbmMc = request.getParameter("sjrbmMc");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			zjqd.setSjrBmmc(sjrbmMc);

			String sjrbm = request.getParameter("sjrbm");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			zjqd.setSjrbm(sjrbm);

			zjqd.setLclx("flj");// 流程类型当事人自主提交
			zjqd.setQscyr("fg");// 前手持有人法官
			zjqd.setZt(9);// 状态为9

			String sjr = request.getParameter("sjr");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			sjr = URLDecoder.decode(sjr, "UTF-8");

			zjqd.setDqcyr(sjr);// 当前持有人为承办人
			zjqd.setSjr(sjr);// 承办人为承办人
			zjqd.setHscyr("sszx");// 后手持有人诉讼中心

			String tjr = request.getParameter("tjr");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			zjqd.setDjr(tjr);// 当事人

			String djrq = request.getParameter("djrq");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			zjqd.setDjrq(djrq);
			
			//时限
			String lqsx = request.getParameter("lqsx");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			zjqd.setSx(Integer.parseInt(lqsx));
			
			//时限日期
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(djrq);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, Integer.parseInt(lqsx));
			Timestamp sxsj = new Timestamp(cal.getTimeInMillis());
			zjqd.setSxsj(sxsj);

			String tjrlxdh = request.getParameter("tjrlxdh");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			zjqd.setDjrlxdh(tjrlxdh);

			String zjrq = request.getParameter("zjrq");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			zjqd.setZjrq(zjrq);

			String djrsfzhm = request.getParameter("djrsfzhm");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			zjqd.setDsrsfzhm(djrsfzhm);

			zjqd.setLzjl("");
			zjqdDao.saveZjqd(zjqd);

			// 开始保存材料清单
			String cl = request.getParameter("cl");
			cl = URLDecoder.decode(cl, "UTF-8");
			cl = URLDecoder.decode(cl, "UTF-8");

			if (cl.contains(";")) {
				String[] clist = cl.split(";");
				ClbDao clbDao = new ClbDao();
				for (int i = 0; i < clist.length; i++) {
					String[] clnr = clist[i].split(",");
					Clb clb = new Clb();
					ClbId clbid = new ClbId();
					clb.setId(clbid);
					clb.getId().setBh(bh);
					clb.getId().setFydm(fydm);
					clb.getId().setXh(i);
					clb.setClmc(clnr[0]);
					clb.setFs(Integer.valueOf(clnr[1]));

					clbDao.saveClb(clb);
				}
			}

			result.put("success", true);
			result.put("after", "1");

		} catch (Exception e) {
			e.printStackTrace();

			result.put("success", true);
			result.put("after", "0");
		}

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
