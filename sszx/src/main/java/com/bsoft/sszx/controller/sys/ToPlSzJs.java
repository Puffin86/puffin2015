package com.bsoft.sszx.controller.sys;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 批量设置角色
 */
@Controller
public class ToPlSzJs {

	@RequestMapping("to_plsz")
	public String toPlsz(String roleId, Map<String, Object> model){
		model.put("roleId", roleId);
		return "sys/plsz2";
	}

}
