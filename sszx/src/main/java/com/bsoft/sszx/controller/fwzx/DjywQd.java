package com.bsoft.sszx.controller.fwzx;


import java.net.URLDecoder;
import java.util.Calendar;
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
import com.bsoft.sszx.dao.SmsDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.entity.sms.SmsBean;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.xfireclient.SMSClient;

import net.sf.json.JSONObject;
@Controller
public class DjywQd  {
	@ResponseBody
	@RequestMapping("djywQd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		String user=(String)session.getAttribute("user");
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		Zjqd.setZt(7);//状态设置等待当事人取件
		
		UserDao userDao=new UserDao();
		
		String dsrlxdh=Zjqd.getDjrlxdh();
		String dsrxm = Zjqd.getDjr();
		
		String dqcyrName=userDao.findUserById(user, fydm).getYhxm();
		
		Zjqd.setZjr(user);
		Zjqd.setZjrXm(dqcyrName);
		String lzjl=Zjqd.getLzjl()+"材料由转交人【"+dqcyrName+"】于【"
				+new GetTime().gettime()
				+"】确认接收;";
		Zjqd.setLzjl(lzjl);
		Zjqd.setZjrq(new GetTime().gettime());
		
		Zjqd.setDqcyr(user);		
		new ZjqdDao().saveZjqd(Zjqd);
		
		String cl=request.getParameter("cl");
		cl = URLDecoder.decode(cl, "UTF-8"); 
		cl = URLDecoder.decode(cl, "UTF-8"); 
		
        ClbDao clbDao=new ClbDao();
		
		List<Clb> listClb=clbDao.findByZjqd(String.valueOf(id), fydm);
		for(int i=0;i<listClb.size();i++){
			clbDao.delClb(listClb.get(i));
		}
		
		if(cl.contains(";")){
		String[] clist=cl.split(";");
		
		
		for(int i=0; i<clist.length;i++){
		  String[] clnr=clist[i].split(",");
		  Clb clb=new Clb();
		  ClbId clbid=new ClbId();
		  clb.setId(clbid);
		  clb.getId().setBh(Integer.valueOf(id));
		  clb.getId().setFydm(fydm);
		  clb.getId().setXh(i);
		  clb.setClmc(clnr[0]);
		  clb.setFs(Integer.valueOf(clnr[1]));
		  clb.setYs(Integer.valueOf(clnr[2]));
		  clbDao.saveClb(clb); 
		}}		
		
		String lx=request.getParameter("sffs");//存储短信给当事人
		if(lx.equals("0")&& dsrlxdh!=null&&!dsrlxdh.equals("")){
			String nr=request.getParameter("sms");
			nr = URLDecoder.decode(nr, "UTF-8"); 
			nr = URLDecoder.decode(nr, "UTF-8"); 
			
			int bh = Zjqd.getId().getBh();
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
			
			Sms sms=new Sms();
			sms.setSmszt(ret[0][1]);
			sms.setBh(bh);
			sms.setSmsid2(id2);
			sms.setFydm(fydm);
			sms.setLxdh(dsrlxdh);
			sms.setNr(nr);
			sms.setZt(0);
			new SmsDao().saveOrUpdate(sms);
		}		
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
