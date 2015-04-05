package com.bsoft.sszx.controller.cx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Cxjl {

	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_cxjl")
	public String tuiHuiCL(){
		return "cx/cxjl";
	}
}
