package com.bsoft.sszx.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClqdDao;
import com.bsoft.sszx.entity.clb.Clqd;
import com.bsoft.sszx.util.HttpHelper;

/**
 * 常用材料清单
 */
@Controller
public class ToClqd {

	@RequestMapping("to_clqd")
	public String toClqd() {
		return "user/clqd";
	}

	@ResponseBody
	@RequestMapping("clqd/list")
	public void list(HttpServletResponse response) {
		List<Clqd> list = new ClqdDao().findAll();
		HttpHelper.renderJson(JSONArray.fromObject(list).toString(), response);
	}
	
	@ResponseBody
	@RequestMapping("clqd/save")
	public void save(Clqd bean, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			new ClqdDao().save(bean);
			result.put("success", 1);
		} catch (Exception e) {
			result.put("success", 0);
			e.printStackTrace();
		}
		
		HttpHelper.renderJson(JSONObject.fromObject(result).toString(), response);
	}
	
	@ResponseBody
	@RequestMapping("clqd/update")
	public void update(Clqd bean, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			new ClqdDao().update(bean);
			result.put("success", 1);
		} catch (Exception e) {
			result.put("success", 0);
			e.printStackTrace();
		}
		
		HttpHelper.renderJson(JSONObject.fromObject(result).toString(), response);
	}
	
	@ResponseBody
	@RequestMapping("clqd/delete")
	public void save(Integer id, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			Clqd bean = new Clqd();
			bean.setClbh(id);
			new ClqdDao().delete(bean);
			result.put("success", 1);
		} catch (Exception e) {
			result.put("success", 0);
			e.printStackTrace();
		}
		
		HttpHelper.renderJson(JSONObject.fromObject(result).toString(), response);
	}

}
