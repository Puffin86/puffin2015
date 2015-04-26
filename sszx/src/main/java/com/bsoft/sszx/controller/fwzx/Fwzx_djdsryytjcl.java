package com.bsoft.sszx.controller.fwzx;

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

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class Fwzx_djdsryytjcl {
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_djdsrSj")
	public String djdsrSj(){
		return "fwzx/djdsrSj";
	}
	
	@ResponseBody
	@RequestMapping("djdsrSjTable")
	public void jsdsrzdsj(Integer page, Integer rows, String user,
			String fydm, HttpServletResponse response) throws Exception
	{
		 //当前页  
        int intPage = (page == null || page == 0) ? 1:page;  
        //每页显示条数  
        int number = (rows == null || rows == 0) ? 10:rows;  
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;         
	
	Map<String, Object> map = new HashMap<String, Object>();
	ZjqdDao zjqdDao= new ZjqdDao();
	List<Zjqd> al = zjqdDao.findDsrZzSJbyPage_2(start,number,10,fydm);//每页的数据，放入list 
	
	if(al!=null){
		for(Zjqd bean : al){
			int len = GetTime.checkOutTime(bean);
			bean.setSfcs(len);
		}
	}
	
	List<Zjqd> all= zjqdDao.findDsrZzSJ_2(10, fydm);
	map.put("total", all.size());
	map.put("rows", al);
	JSONObject resultObj=JSONObject.fromObject(map); //将map对象转换成为json对象
	HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	@RequestMapping("djywJs")
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		
	    String id=request.getParameter("bh");
		
		Zjqd Zjqd=new ZjqdDao().findbyid(id,fydm);
		List<Clb> list=new ClbDao().findByZjqd(id, fydm);
		
		session.setAttribute("djywZjqd", Zjqd);
		session.setAttribute("djywZjqdClb", list);
		return "fwzx/djywJs";
	}
	
	
	
}
