package com.bsoft.sszx.controller.thclcl;


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

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class CxTj  {
	@RequestMapping("cxTj")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");	      
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		
		if(Zjqd.getLclx().equals("dzz"))//流程类型为当事人自主提交
		Zjqd.setZt(1);//状态设置为待接收
		
		if(Zjqd.getLclx().equals("flq"))//流程类型为法官联系当事人取件
			Zjqd.setZt(6);//状态设置为待接收
		
		if(Zjqd.getLclx().equals("flj"))//流程类型为法官联系当事人取件
			Zjqd.setZt(10);//状态设置为待接收
	
		String dqcyr=Zjqd.getDqcyr();	
		
		String hscyr="";
		if(Zjqd.getLclx().equals("dzz"))
		  hscyr=Zjqd.getSjr();
		if(Zjqd.getLclx().equals("flq"))
		  hscyr="sszx";
		
		String lzjl="";
		
		if(Zjqd.getLclx().equals("dzz")){
			UserDao userDao=new UserDao();
			String zjrName=userDao.findUserById(dqcyr, fydm).getYhxm();
			String hscyrName=userDao.findUserById(hscyr, fydm).getYhxm();
			
			lzjl=Zjqd.getLzjl()+"材料由转交人【"+zjrName+"】于【"
					+new GetTime().gettime()
					+"】修改后重新提交承办人【"+hscyrName+"】接收;";
		}else if(Zjqd.getLclx().equals("flq")){
			lzjl=Zjqd.getLzjl()+"材料由承办人【"+Zjqd.getSjrXm()+"】于【"
						+new GetTime().gettime()
						+"】修改后重新提交【诉讼中心】接收;";
		}
		Zjqd.setLzjl(lzjl);
		
		Zjqd.setDjrq(new GetTime().gettime());//修改递交日期
		
		
		
		if(Zjqd.getLclx().equals("dzz")){
			Zjqd.setDqcyr(hscyr);
			Zjqd.setQscyr(dqcyr);
			Zjqd.setHscyr("fg");//后手持有人为法官
		}
		
		if(Zjqd.getLclx().equals("flq")){
			Zjqd.setDqcyr(hscyr);
			Zjqd.setQscyr(dqcyr);
			Zjqd.setHscyr("dsr");//后手持有人为法官
			
			int lqsx = Zjqd.getSx();
			//时限日期
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, lqsx);
			Timestamp sxsj = new Timestamp(cal.getTimeInMillis());
			Zjqd.setSxsj(sxsj);
		}
		
		if(Zjqd.getLclx().equals("flj")){
			Zjqd.setDqcyr("sszx");
			Zjqd.setQscyr(dqcyr);
			Zjqd.setHscyr("sszx");//后手持有人为法官
			
			int lqsx = Zjqd.getSx();
			//时限日期
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, lqsx);
			Timestamp sxsj = new Timestamp(cal.getTimeInMillis());
			Zjqd.setSxsj(sxsj);
		}
		
		Zjqd.setZjrq(new GetTime().gettime());
	
		new ZjqdDao().saveZjqd(Zjqd);
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
       }
}


