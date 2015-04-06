package com.bsoft.sszx.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设置权限
 */
@Controller
public class ToSzqx {

	@RequestMapping("to_user")
	public String toUser() {
		return "sys/user";
	}

}
