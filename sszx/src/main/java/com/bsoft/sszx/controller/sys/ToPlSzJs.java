package com.bsoft.sszx.controller.sys;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 批量设置角色
 */
@Controller
public class ToPlSzJs {

	@RequestMapping("to_plsz")
	public String toPlsz(String roleId,String roleText, Map<String, Object> model) throws UnsupportedEncodingException{
		model.put("roleId", roleId);
		String realRoleText = roleText;
		realRoleText = URLDecoder.decode(realRoleText, "UTF-8");
		realRoleText = URLDecoder.decode(realRoleText, "UTF-8");
		model.put("roleText", realRoleText);
//		System.out.println("roleText::::"+realRoleText);
		return "sys/plsz2";
	}

}
