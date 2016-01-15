package com.ims.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ims.services.IPartService;
import com.ims.utils.HttpHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/part")
public class PartController {
	@Resource
	private IPartService partService;
	private Logger logger = LoggerFactory.getLogger(PartController.class);
	
	
//	/**
//	 * 调整页面
//	 * @param request
//	 * @param model
//	 * @return
//	 */
	@RequestMapping("/getPartRedirect")
	public String getPartRedirect(HttpServletRequest request,Model model){
		int i = partService.selectNum();
		logger.info("i==================="+i);
		return "showMessage";
	}
	
	/**
	 * 返回json数据
	 * @param response
	 * @param session
	 */
	@RequestMapping("/getPartByJson")
	public void getPartByJson(HttpServletResponse response, HttpSession session){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("test1", "tomcat");
		result.put("test2", "jetty");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	/**
	 * 跳转其他controller
	 * @param response
	 * @param session
	 */
	@RequestMapping("/getPartToUser")
	public void getPartToUser(HttpServletResponse response, HttpSession session){
		try {
//			response.sendRedirect("../user/redirectFromPart");
			response.sendRedirect("../user/getUsers");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转本controll其他方法
	 * @param response
	 * @param session
	 */
	@RequestMapping("/getPartToPart")
	public void getPartToPart(HttpServletResponse response, HttpSession session){
		try {
			response.sendRedirect("getPartRedirect");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
