package com.bsoft.sszx.controller.fwzx;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.SmsDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.entity.sms.Sms;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class DjdsrSjsjQd  {
	@RequestMapping("djdsrSjsjQd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		String user=(String)session.getAttribute("user");
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		Zjqd.setZt(1);//状态设置等待法官取件
		
		UserDao userDao=new UserDao();
		String sjr=Zjqd.getSjr();
		String cbrlxdh=userDao.findUserById(sjr, fydm).getLxdh();		
		
		String dqcyrName=userDao.findUserById(user, fydm).getYhxm();
		
		String lzjl=Zjqd.getLzjl()+"材料由当事人【"+Zjqd.getDjr()+"】递交并由"+"转交人【"+dqcyrName+"】于【"
				+new GetTime().gettime()
				+"】确认接收;";
		Zjqd.setLzjl(lzjl);
		
		Zjqd.setDqcyr(Zjqd.getSjr());
		Zjqd.setZjrq(new GetTime().gettime());
		
		new ZjqdDao().saveZjqd(Zjqd);
		
		String cl=request.getParameter("cl");
		cl = URLDecoder.decode(cl, "UTF-8"); 
		cl = URLDecoder.decode(cl, "UTF-8"); 
		
		if(cl.contains(";")){
		String[] clist=cl.split(";");
		ClbDao clbDao=new ClbDao();
		
		List<Clb> listClb=clbDao.findByZjqd(id, fydm);
		for(int i=0;i<listClb.size();i++){
			clbDao.delClb(listClb.get(i));
		}
		
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
