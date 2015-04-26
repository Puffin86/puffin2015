package com.bsoft.sszx.controller.fwzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

@Controller
public class Fwzx_djdsrlqcl {
	
	/**
	 * menu跳转
	 * @return
	 */
	@RequestMapping("to_djdsrLq")
	public String djdsrLq(){
		return "fwzx/djdsrLq";
	}
	
	
	@RequestMapping("djdsrLqTable")
	public void djdsrLqTable(Integer page, Integer rows, String user,
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
	List<Zjqd> al = zjqdDao.findDsrZzSJbyPage_2(start,number,7,fydm);//每页的数据，放入list 
	
	for(Zjqd bean : al){
		int len = GetTime.checkOutTime(bean);
		bean.setSfcs(len);
	}
	
	List<Zjqd> all= zjqdDao.findDsrZzSJ_2(7, fydm);
	map.put("total", all.size());
	map.put("rows", al);
	JSONObject resultObj=JSONObject.fromObject(map); //将map对象转换成为json对象
	HttpHelper.renderJson(resultObj.toString(), response);
	
	}
	
	
}
