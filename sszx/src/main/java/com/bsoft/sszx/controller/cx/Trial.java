package com.bsoft.sszx.controller.cx;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.FyDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.TrialTool;
@Controller
public class Trial {
	@ResponseBody
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String fymc = request.getParameter("fymc"); // 法院名称
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		// 法院代码
		String fydm = new FyDao().fydm(fymc);
		
		if(fydm==null){
			result.put("success", false);
			result.put("after", "3");
			JSONObject json = JSONObject.fromObject(result);
			HttpHelper.renderJson(json.toString(), response);
			return;
		}
		
		// 用户名首字母大写
		String userOne = user.substring(0, 1).toUpperCase();
		String userLa = user.substring(1);
		user = userOne + userLa;

		String fystr=TrialTool.decode("MzMwMTAz");
		if(!fystr.equals(fydm)){
			result.put("success", false);
			result.put("after", "2");
			JSONObject json = JSONObject.fromObject(result);
			HttpHelper.renderJson(json.toString(), response);
			return;
		}
		
		User u = new UserDao().findUserById(user, fydm);
		if (u != null) {
			if (u.getPass().equals(pass)) {
				String str=TrialTool.decode("MjAxNS0wOS0xNQ==");
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				Date date =sdf.parse(str);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				Calendar today = Calendar.getInstance();
				int s = today.compareTo(calendar);
				
				if(s==-1){
						session.setAttribute("user", user);
						session.setAttribute("fydm", fydm);
						
						//把用户名和密码保存在Cookie对象里面  
						String username = URLEncoder.encode(user,"utf-8");  
						//使用URLEncoder解决无法在Cookie当中保存中文字符串问题  
						Cookie usernameCookie = new Cookie("username",username);  
						usernameCookie.setMaxAge(864000);  //设置最大生存期限为10天  
						response.addCookie(usernameCookie);  

						result.put("success", true);
						result.put("after", "1");
				}else{
					result.put("success", false);
					result.put("after", "2");
				}
			} else {
				result.put("success", false);
				result.put("after", "0");
			}
		} else {
			result.put("success", false);
			result.put("after", "0");
		}
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
}
