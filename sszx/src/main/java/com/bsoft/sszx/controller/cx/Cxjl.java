package com.bsoft.sszx.controller.cx;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZdDao;
import com.bsoft.sszx.entity.zd.ZdMx;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class Cxjl {

	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_cxjl")
	public String tuiHuiCL(){
		return "cx/cxjl2";
	}
	
	@RequestMapping("ywlxcx")
	public void ywlxcx(HttpServletRequest requst,HttpServletResponse response){
		ZdDao zdDao = new ZdDao();
		List<ZdMx> list = zdDao.findZdMx("ywlx");
		JSONArray jsonArr = JSONArray.fromObject(list);
		HttpHelper.renderJson(jsonArr.toString(), response);
	}
	
}
