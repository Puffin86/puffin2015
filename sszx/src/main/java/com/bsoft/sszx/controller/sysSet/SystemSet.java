package com.bsoft.sszx.controller.sysSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemSet {
	
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_user")
	public String to_user(){
		return "sysSet/user";
	}
	
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_dxx")
	public String to_dxx(){
		return "sysSet/dxx";
	}
	
}
