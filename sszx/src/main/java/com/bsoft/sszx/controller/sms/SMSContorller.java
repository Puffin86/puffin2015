package com.bsoft.sszx.controller.sms;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.entity.sms.SmsBean;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.xfireclient.SMSClient;

import dxpt.Client.SendSMSClient;
import dxpt.Client.SendSMSPortType;

@Controller
public class SMSContorller {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("sendsms")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		
		String fsr_userid = request.getParameter("fsr_userid");
		String jssjhm = request.getParameter("jssjhm");
		String jsrmc = request.getParameter("jsrmc");
		String fsnr = request.getParameter("fsnr");
		
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		ip = ip.replace(".", "");
		System.out.println("IP:"+ip);
		Calendar cal = Calendar.getInstance();
		String date = cal.get(Calendar.YEAR)+"";
		if((cal.get(Calendar.MONTH)+1)<10){
			date = date+"0"+(cal.get(Calendar.MONTH)+1);
		}else{
			date = date+(cal.get(Calendar.MONTH)+1);
		}
		
		if(cal.get(Calendar.DATE)<10){
			date = date+"0"+(cal.get(Calendar.DATE));
		}else{
			date = date+(cal.get(Calendar.DATE));
		}
		
		if(cal.get(Calendar.HOUR_OF_DAY)<10){
			date = date+"0"+(cal.get(Calendar.HOUR_OF_DAY));
		}else{
			date = date+(cal.get(Calendar.HOUR_OF_DAY));
		}
		
		if(cal.get(Calendar.MINUTE)<10){
			date = date+"0"+(cal.get(Calendar.MINUTE));
		}else{
			date = date+(cal.get(Calendar.MINUTE));
		}
		
		if(cal.get(Calendar.SECOND)<10){
			date = date+"0"+(cal.get(Calendar.SECOND));
		}else{
			date = date+(cal.get(Calendar.SECOND));
		}
		System.out.println("Date:"+date);
		String id2 =ip+date+"00001";
		System.out.println("ID2:"+id2);
		SMSClient smsc = new SMSClient();
		SmsBean bean = new SmsBean();
		bean.setFsr_userid(fsr_userid);
		bean.setJssjhm(jssjhm);
		bean.setJsrmc(jsrmc);
		bean.setFsnr(fsnr);
		bean.setId2(id2);
		
		String[][] sarr = smsc.sendSMS(bean);
		System.out.println(sarr[0][0]+"@"+sarr[0][1]);
		
		Map result = new HashMap();
		result.put("msg", sarr[0][0]);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("sendsmsdemo")
	@ResponseBody
	public void execute2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		SMSClient smsc = new SMSClient();
		String str = smsc.smsTest();
		Map result = new HashMap();
		result.put("msg", str);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	
}
