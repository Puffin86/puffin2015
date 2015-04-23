package com.bsoft.sszx.controller.fwzx;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.SmsDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class ZhJDsrZjqdZJ  {
	@RequestMapping("zhJiaoDsrZzSj")
	public String zhJiaoDsrZzSj(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {  
		String fydm=(String)session.getAttribute("fydm");
	    String id=request.getParameter("bh");
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		session.setAttribute("zhJDsrZzSj", Zjqd);
		return "fwzx/zhJdsrZzSj";
	}
	
	@RequestMapping("zhJiaoDsrZzSjZj")
	public void zhJiaoDsrZzSjZj(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception { 
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		Zjqd.setZt(1);//状态设置为待接收
	
		String dqcyr=Zjqd.getDqcyr(); 
		String hscyr=Zjqd.getHscyr();
		
		UserDao userDao=new UserDao();
		String zjrName=userDao.findUserById(dqcyr, fydm).getYhxm();
		String hscyrName=userDao.findUserById(hscyr, fydm).getYhxm();
		String cbrlxdh=userDao.findUserById(hscyr, fydm).getLxdh();
		
		String lzjl=Zjqd.getLzjl()+"材料由转交人【"+zjrName+"】于【"
				+new GetTime().gettime()
				+"】通知承办人【"+hscyrName+"】接收;";
		
		Zjqd.setZjr(dqcyr);
		Zjqd.setZjrXm(zjrName);
		
		Zjqd.setLzjl(lzjl);
		
		Zjqd.setDqcyr(hscyr);
		Zjqd.setQscyr(dqcyr);
		Zjqd.setHscyr("fg");//后手持有人为法官
		
		String time=new GetTime().gettime();
		Zjqd.setZjrq(time);
		
		new ZjqdDao().saveZjqd(Zjqd);
		
		
		String lx=request.getParameter("sffs");//存储短信给承办人
		if(lx.equals("0")&& cbrlxdh!=null&&!cbrlxdh.equals("")){
			String nr=request.getParameter("sms");
			nr = URLDecoder.decode(nr, "UTF-8"); 
			nr = URLDecoder.decode(nr, "UTF-8"); 
			Sms sms=new Sms();
			sms.setFydm(fydm);
			sms.setLxdh(cbrlxdh);
			sms.setNr(nr);
			sms.setZt(0);
			new SmsDao().save(sms);
		}		
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
	

}
