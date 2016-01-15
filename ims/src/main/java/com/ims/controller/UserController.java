package com.ims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ims.pojos.User;
import com.ims.services.impl.UserServiceImpl;
import com.ims.utils.HttpHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserServiceImpl userService;
	
	@RequestMapping("/redirectFromPart")
	public void redirectFromPart(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("user-test1", "tomcat");
		result.put("user-test2", "jetty");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	@RequestMapping("/getUsers")
	public String getUsers(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		User user = userService.getUserById(2);
		logger.info("userName:========"+user.getUserName());
		return "showUser";
	}
	
}
