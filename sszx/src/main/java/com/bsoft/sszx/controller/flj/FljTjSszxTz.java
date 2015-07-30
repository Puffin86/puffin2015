package com.bsoft.sszx.controller.flj;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.SmsDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.entity.sms.SmsBean;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.xfireclient.SMSClient;

import net.sf.json.JSONObject;

/**
 * 通知当事人提交材料 - 确认
 */
@Controller
public class FljTjSszxTz {

	@ResponseBody
	@RequestMapping("fljTjSszxTz")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String fydm = (String) session.getAttribute("fydm");
		String id = request.getParameter("bh");

		Zjqd zjqd = new ZjqdDao().findbyid(id, fydm);
		zjqd.setZt(10);// 状态设置为诉讼中心待收件

		String dqcyr = zjqd.getDqcyr();
		String dsrlxdh = zjqd.getDjrlxdh();
		String dsrxm = zjqd.getDjr();

		String lzjl = zjqd.getLzjl() + "材料由承办人【" + zjqd.getSjrXm() + "】于【"+ GetTime.gettime() + "】通知诉讼服务中心收取材料;";
		zjqd.setLzjl(lzjl);

		zjqd.setDqcyr("sszx");
		zjqd.setQscyr(dqcyr);
		zjqd.setHscyr("sszx");// 后手诉讼中心

		zjqd.setZjrq(GetTime.gettime());
		new ZjqdDao().saveZjqd(zjqd);

		String lx = request.getParameter("sffs");// 存储短信给当事人
		if (lx.equals("0") && dsrlxdh != null && !dsrlxdh.equals("")) {//发送短信
			String nr = request.getParameter("sms");
			nr = URLDecoder.decode(nr, "UTF-8");
			nr = URLDecoder.decode(nr, "UTF-8");
			
			int bh = zjqd.getId().getBh();
			Calendar cal = Calendar.getInstance();
			//案件编号+法院编码+系统编号+年月日时分
			String id2 = bh+fydm+"023"+cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+cal.get(Calendar.DATE)+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE);
			SmsBean smsbean = new SmsBean();
			smsbean.setJsrmc(dsrxm);
			smsbean.setJssjhm(dsrlxdh);
			smsbean.setFsnr(nr);
			smsbean.setId2(id2);
			SMSClient sc = new SMSClient();
			String[][] ret = sc.sendSMS(smsbean);
			
			Sms sms = new Sms();
			if(ret!=null && ret[0][1]!=null)
				sms.setSmszt(ret[0][1]);
			else
				sms.setSmszt("09");
			sms.setBh(bh);
			sms.setSmsid2(id2);
			sms.setFydm(fydm);
			sms.setLxdh(dsrlxdh);
			sms.setNr(nr);
			sms.setZt(0);
			new SmsDao().saveOrUpdate(sms);
			result.put("success", true);
			result.put("after", "1");
		}else{
			result.put("success", false);
			result.put("after", "0");
			result.put("msg", "error:联系电话为空");
		}

		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
