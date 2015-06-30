package com.bsoft.sszx.controller.thclcl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class Clth {

	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_tuiHuiCL")
	public String tuiHuiCL(){
		return "clth/tuiHuiCL";
	}
	
	@RequestMapping("cxtjTz")
	public String cxtjTz(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		
		session.setAttribute("cxtjCL", Zjqd);
		return "clth/cxTj";
	}
	
	
	@ResponseBody
	@RequestMapping("tuiHuiCLTable")
	public void tuiHuiCLTable(Integer page, Integer rows,String sort,String order, String user,
			String fydm, HttpServletResponse response) {
		 //当前页  
       int intPage =(page == null || page == 0) ? 1:page;  
       //每页显示条数  
       int number = (rows == null || rows == 0) ? 10:rows;  
       //每页的开始记录  第一页为1  第二页为number +1   
       int start = (intPage-1)*number;         
	
		Map map = new HashMap();
		ZjqdDao zjqdDao= new ZjqdDao();
		List<Zjqd> al = zjqdDao.findDsrZzSJbyPage(start,number,user,3,fydm,sort,order);//每页的数据，放入list 
		List<Zjqd> all= zjqdDao.findDsrZzSJ(user, 3, fydm);
		map.put("total", all.size());
		map.put("rows", al);
		JSONObject resultObj=JSONObject.fromObject(map); //将map对象转换成为json对象
		HttpHelper.renderJson(resultObj.toString(), response);
		
	}
	
	
}
