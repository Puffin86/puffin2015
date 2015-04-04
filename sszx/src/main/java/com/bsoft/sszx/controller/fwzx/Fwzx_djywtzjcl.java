package com.bsoft.sszx.controller.fwzx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Fwzx_djywtzjcl {
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_djywZj.do")
	public String djywtzjcl(){
		return "fwzx/djywZj";
	}
}
