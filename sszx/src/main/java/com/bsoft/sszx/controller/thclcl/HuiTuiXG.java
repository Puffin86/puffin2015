package com.bsoft.sszx.controller.thclcl;//保存当事人转接清单

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

@Controller
public class HuiTuiXG  {
	@RequestMapping("huiTuiXG")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		List<Clb> list=new ClbDao().findByZjqd(id, fydm);
		
		session.setAttribute("editHuiTuiZjqd", Zjqd);
		session.setAttribute("editHuiTuiZjqdClb", list);
		return "clth/huiTuiXG";
	}

	@ResponseBody
	@RequestMapping("searchHtyj")
	public void searchHtyj(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		
		String id=request.getParameter("id");
		
		String htyj= new ZjqdDao().findbyid(id, fydm).getHtyj();
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("htyj", htyj);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
       
	}
}
