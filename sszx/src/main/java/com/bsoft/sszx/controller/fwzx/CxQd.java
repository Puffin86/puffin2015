package com.bsoft.sszx.controller.fwzx;

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

import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class CxQd {
	@ResponseBody
	@RequestMapping("cxQd")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		Zjqd.setZt(20);//状态设置为撤销
	
		String dqcyr=Zjqd.getDqcyr(); 
		String hscyr=Zjqd.getHscyr();
		
		UserDao userDao=new UserDao();
		String zjrName=userDao.findUserById(dqcyr, fydm).getYhxm();
		
		String lzjl=Zjqd.getLzjl()+"任务由创建人【"+zjrName+"】于【"
				+new GetTime().gettime()
				+"】撤销;";
		Zjqd.setLzjl(lzjl);
		
		Zjqd.setZjrq(new GetTime().gettime());
		
		new ZjqdDao().saveZjqd(Zjqd);
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}

}
