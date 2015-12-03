package com.bsoft.sszx.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.HomeDao;
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class Home {
	@ResponseBody
	@RequestMapping("home")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		
		String user = (String) session.getAttribute("user");
		String fydm = (String) session.getAttribute("fydm");
		UserDao userDao = new UserDao();
		//用户姓名
		User userBean = userDao.findUserById(user, fydm);
		String userXm = userBean.getYhxm();
		String userBm = userBean.getYhbm();
		String userJs = userBean.getJs();
		//根据状态获取
		HomeDao home = new HomeDao(); 
		Map<String,Integer> info = home.getHomeData(fydm,user,userBm,userJs);
//		int htN=info.get("htN");
//		int drN=info.get("drN");
//		int jsN=info.get("jsN");
//		int jjlqsx=info.get("jjlqsx");
//		int cglqsx=info.get("cglqsx");
//		int jjtjsx=info.get("jjtjsx");
//		int cgtjsx=info.get("cgtjsx");
		//联系电话是否设置
		int lxdh=0;
		if(userDao.findUserById(user, fydm).getLxdh()!=null)
			lxdh=1;
		info.put("lxdh", lxdh);
		Map result = new HashMap();
		result.putAll(info);
		result.put("success", true);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
