package com.bsoft.sszx.controller.flq;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

/**
 * //修改当事人转接清单
 */
@Controller
public class EditDsrZjqdQd {

	@ResponseBody
	@RequestMapping("editDsrZjqdQd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");// 法院代码

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			String bh = request.getParameter("bh");
			ZjqdDao zjqdDao = new ZjqdDao();
			Zjqd Zjqd = zjqdDao.findbyid(bh, fydm);

			String ah = request.getParameter("ah");
			ah = URLDecoder.decode(ah, "UTF-8");
			ah = URLDecoder.decode(ah, "UTF-8");
			Zjqd.setAh(ah);
			
			String ahdm = request.getParameter("ahdm");
			ahdm = URLDecoder.decode(ahdm, "UTF-8");
			ahdm = URLDecoder.decode(ahdm, "UTF-8");
			Zjqd.setAhdm(ahdm);

			String sjrXm = request.getParameter("sjrXm");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			sjrXm = URLDecoder.decode(sjrXm, "UTF-8");
			Zjqd.setSjrXm(sjrXm);

			String sjrbmMc = request.getParameter("sjrbmMc");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8");
			Zjqd.setSjrBmmc(sjrbmMc);

			String sjr = request.getParameter("sjr");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			sjr = URLDecoder.decode(sjr, "UTF-8");
			Zjqd.setSjr(sjr);
			Zjqd.setHscyr(sjr);// 后手持有人为收件人

			String tjr = request.getParameter("tjr");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			tjr = URLDecoder.decode(tjr, "UTF-8");
			Zjqd.setDjr(tjr);

			String djrq = request.getParameter("djrq");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			djrq = URLDecoder.decode(djrq, "UTF-8");
			Zjqd.setDjrq(djrq);

			//时限
			String lqsx = request.getParameter("lqsx");
			if(lqsx!=null&& !"".equals(lqsx)){
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
			}
			
			
			
			String tjrlxdh = request.getParameter("tjrlxdh");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8");
			Zjqd.setDjrlxdh(tjrlxdh);

			String djrsfzhm = request.getParameter("djrsfzhm");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8");
			Zjqd.setDsrsfzhm(djrsfzhm);
			
			String dlr=request.getParameter("dlr");
			if(dlr!=null){
				dlr = URLDecoder.decode(dlr, "UTF-8"); 
				dlr = URLDecoder.decode(dlr, "UTF-8"); 
				Zjqd.setDlr(dlr);
			}
			
			String dlrdh=request.getParameter("dlrdh");
			if(dlrdh!=null){
				dlrdh = URLDecoder.decode(dlrdh, "UTF-8"); 
				dlrdh = URLDecoder.decode(dlrdh, "UTF-8"); 
				Zjqd.setDlrdh(dlrdh);
			}
			
			String zyzh=request.getParameter("zyzh");
			if(zyzh!=null){
				zyzh = URLDecoder.decode(zyzh, "UTF-8"); 
				zyzh = URLDecoder.decode(zyzh, "UTF-8"); 
				Zjqd.setZyzh(zyzh);
			}
			

			zjqdDao.saveZjqd(Zjqd);

			// 开始保存材料清单

			String cl = request.getParameter("cl");
			cl = URLDecoder.decode(cl, "UTF-8");
			cl = URLDecoder.decode(cl, "UTF-8");

			ClbDao clbDao = new ClbDao();
			List<Clb> listClb = clbDao.findByZjqd(String.valueOf(bh), fydm);
			for (int i = 0; i < listClb.size(); i++) {
				clbDao.delClb(listClb.get(i));
			}

			if (cl.contains(";")) {
				String[] clist = cl.split(";");

				for (int i = 0; i < clist.length; i++) {
					String[] clnr = clist[i].split(",");
					Clb clb = new Clb();
					ClbId clbid = new ClbId();
					clb.setId(clbid);
					clb.getId().setBh(Integer.valueOf(bh));
					clb.getId().setFydm(fydm);
					clb.getId().setXh(i);
					clb.setClmc(clnr[0]);
					clb.setFs(Integer.valueOf(clnr[1]));
					if (clnr[2] != null && !clnr[2].equals("")) {
						clb.setYs(Integer.valueOf(clnr[2]));
					}
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
