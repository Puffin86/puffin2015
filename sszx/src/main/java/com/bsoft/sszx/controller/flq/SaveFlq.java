package com.bsoft.sszx.controller.flq;

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

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.entity.zjqd.ZjqdId;
import com.bsoft.sszx.hibernate.HibernateUtil;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

/**
 * 保存当事人转接清单
 */
@Controller
public class SaveFlq {

	@ResponseBody
	@RequestMapping("saveFlq")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			Zjqd Zjqd = new Zjqd();
			ZjqdDao zjqdDao = new ZjqdDao();
			int bh = zjqdDao.getMaxId(fydm);

			ZjqdId zjqdId = new ZjqdId();
			Zjqd.setId(zjqdId);
			Zjqd.getId().setBh(bh);
			Zjqd.getId().setFydm(fydm);

			String ah = request.getParameter("ah");
			ah = URLDecoder.decode(ah, "UTF-8");
			ah = URLDecoder.decode(ah, "UTF-8");
			Zjqd.setAh(ah);

			String sjrXm = request.getParameter("sjrXm");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			Zjqd.setSjrXm(sjrXm);

			String sjrbmMc = request.getParameter("sjrbmMc");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			Zjqd.setSjrBmmc(sjrbmMc);

			String sjrbm = request.getParameter("sjrbm");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			Zjqd.setSjrbm(sjrbm);

			
			// Zjqd.setZjr(zjr);//当前用户为转交人

			Zjqd.setLclx("flq");// 流程类型当事人自主提交

			Zjqd.setQscyr("fg");// 前手持有人法官

			Zjqd.setZt(5);// 状态为5

			String sjr = request.getParameter("sjr");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			sjr = URLDecoder.decode(sjr, "UTF-8");

			Zjqd.setDqcyr(sjr);// 当前持有人为承办人
			Zjqd.setSjr(sjr);// 承办人为承办人
			Zjqd.setHscyr("sszx");// 后手持有人诉讼中心

			String tjr = request.getParameter("tjr");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			Zjqd.setDjr(tjr);// 当事人

			String djrq = request.getParameter("djrq");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			Zjqd.setDjrq(djrq);
			//时限
			String lqsx = request.getParameter("lqsx");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			Zjqd.setSx(Integer.parseInt(lqsx));
			
			//时限日期
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(djrq);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, Integer.parseInt(lqsx));
			Timestamp sxsj = new Timestamp(cal.getTimeInMillis());
			Zjqd.setSxsj(sxsj);


			String tjrlxdh = request.getParameter("tjrlxdh");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			Zjqd.setDjrlxdh(tjrlxdh);

			String zjrq = request.getParameter("zjrq");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			Zjqd.setZjrq(zjrq);

			String djrsfzhm = request.getParameter("djrsfzhm");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			Zjqd.setDsrsfzhm(djrsfzhm);

			Zjqd.setLzjl("");
			zjqdDao.saveZjqd(Zjqd);

			// 开始保存材料清单

			String cl = request.getParameter("cl");
			cl = URLDecoder.decode(cl, "UTF-8");
			cl = URLDecoder.decode(cl, "UTF-8");
			if (cl.contains(";")) {
				ClbDao clbDao = new ClbDao();
				String[] clist = cl.split(";");
				
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
					clb.setYs(Integer.valueOf(clnr[2]));
					
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
	
	
	@ResponseBody
	@RequestMapping("saveFlqList")
	public void saveFlqList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String fydm = (String) session.getAttribute("fydm");
		Map<String, Object> result = new HashMap<String, Object>();
		Session hibsession = HibernateUtil.getSession(); // 获取Session
		try {
			String ah = request.getParameter("ah");
			ah = URLDecoder.decode(ah, "UTF-8");
			ah = URLDecoder.decode(ah, "UTF-8");
			
			String sjrXm = request.getParameter("sjrXm");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			
			String sjrbmMc = request.getParameter("sjrbmMc");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			
			String tjrStr = request.getParameter("tjrStr");
			tjrStr = URLDecoder.decode(tjrStr, "UTF-8");
			tjrStr = URLDecoder.decode(tjrStr, "UTF-8");
			
			String sjrbm = request.getParameter("sjrbm");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			sjrbm = URLDecoder.decode(sjrbm, "UTF-8");
			
			String djrq = request.getParameter("djrq");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			
			//时限
			String lqsx = request.getParameter("lqsx");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			lqsx = URLDecoder.decode(lqsx, "UTF-8");
			
			String zjrq = request.getParameter("zjrq");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			zjrq = URLDecoder.decode(zjrq, "UTF-8");
			
			String sjr = request.getParameter("sjr");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			
			// 开始保存材料清单
			String cl = request.getParameter("cl");
			cl = URLDecoder.decode(cl, "UTF-8");
			cl = URLDecoder.decode(cl, "UTF-8");
			
			//时限日期
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(djrq);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, Integer.parseInt(lqsx));
			Timestamp sxsj = new Timestamp(cal.getTimeInMillis());
			
			ZjqdDao zjqdDao = new ZjqdDao();
			int bh = zjqdDao.getMaxId(fydm);
			
			
			
			String[] tjrArr = tjrStr.split("@");
			
			
			hibsession.beginTransaction();
			
			for(String tjrItemStr : tjrArr){
				if("".equals(tjrItemStr)){
					continue;
				}
				JSONObject json = JSONObject.fromObject(tjrItemStr);
				String dsrmc = json.getString("dsrmc");
				String dsrlxdh = json.getString("dsrlxdh");
				String dsrsfzhm = json.getString("dsrsfzhm");
				System.out.println(dsrmc+" "+dsrlxdh+" "+dsrsfzhm);
				
				Zjqd Zjqd = new Zjqd();
				Zjqd.setAh(ah);
				Zjqd.setSjrXm(sjrXm);
				Zjqd.setSjrBmmc(sjrbmMc);
				Zjqd.setSjrbm(sjrbm);
				Zjqd.setLclx("flq");// 流程类型当事人自主提交
				Zjqd.setQscyr("fg");// 前手持有人法官
				Zjqd.setZt(5);// 状态为5
				Zjqd.setDqcyr(sjr);// 当前持有人为承办人
				Zjqd.setSjr(sjr);// 承办人为承办人
				Zjqd.setHscyr("sszx");// 后手持有人诉讼中心
				Zjqd.setDjrq(djrq);
				Zjqd.setSx(Integer.parseInt(lqsx));
				Zjqd.setSxsj(sxsj);
				Zjqd.setZjrq(zjrq);
				Zjqd.setLzjl("");
				
				
				ZjqdId zjqdId = new ZjqdId();
				zjqdId.setBh(bh);
				zjqdId.setFydm(fydm);
				Zjqd.setId(zjqdId);
				
				Zjqd.setDjr(dsrmc);
				Zjqd.setDjrlxdh(dsrlxdh);
				Zjqd.setDsrsfzhm(dsrsfzhm);
				
				if (cl.contains(";")) {
					ClbDao clbDao = new ClbDao();
					String[] clist = cl.split(";");
					
					for (int i = 0; i < clist.length; i++) {
						String[] clnr = clist[i].split(",");
						Clb clb = new Clb();
						ClbId clbid = new ClbId();
						clbid.setBh(bh);
						clbid.setFydm(fydm);
						clb.setId(clbid);
						clb.getId().setXh(i);
						clb.setClmc(clnr[0]);
						clb.setFs(Integer.valueOf(clnr[1]));
						clb.setYs(Integer.valueOf(clnr[2]));
						hibsession.saveOrUpdate(clb);// 应用save()方法将留言信息保存到数据
					}
				}
				
				hibsession.saveOrUpdate(Zjqd);
				++bh;
			}
			hibsession.getTransaction().commit();
			result.put("success", true);
			result.put("after", "1");
			JSONObject json = JSONObject.fromObject(result);
			HttpHelper.renderJson(json.toString(), response);
		} catch (Exception e) {
			e.printStackTrace();

			result.put("success", true);
			result.put("after", "0");
		}finally{
			hibsession.close();
		}
	}
	
	

}
