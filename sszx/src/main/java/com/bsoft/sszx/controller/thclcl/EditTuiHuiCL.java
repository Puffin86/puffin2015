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
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.clb.ClbId;
import com.bsoft.sszx.entity.user.User;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.GetTime;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;
@Controller
public class EditTuiHuiCL  {
	@ResponseBody
	@RequestMapping("editTuiHuiCL")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception
	{  
		String fydm=(String)session.getAttribute("fydm");
		String zjr=(String)session.getAttribute("user");//转交人
		User user = new UserDao().findUserById(zjr, fydm);
	    try{
	    String bh=request.getParameter("bh");
	    ZjqdDao zjqdDao=new ZjqdDao();
	    Zjqd Zjqd=zjqdDao.findbyid(bh, fydm);
	    String oldAh = Zjqd.getAh();
	    
		String ah=request.getParameter("ah");
		ah = URLDecoder.decode(ah, "UTF-8"); 
		ah = URLDecoder.decode(ah, "UTF-8"); 
		
		if(!oldAh.equals(ah)){
			String lzjl = Zjqd.getLzjl()+"材料原相关案号"+oldAh+"号由转交人【"+user.getYhxm()+"】于【"+new GetTime().gettime()+"】调整为"+ah;
			Zjqd.setLzjl(lzjl);
		}
		
		Zjqd.setAh(ah);	
		
//		Zjqd.setZjr(zjr);
//		Zjqd.setZjrXm(user.getYhxm());
		
		
		String sjrXm=request.getParameter("sjrXm");
		sjrXm = URLDecoder.decode(sjrXm, "UTF-8"); 
		sjrXm = URLDecoder.decode(sjrXm, "UTF-8"); 
		Zjqd.setSjrXm(sjrXm);
		
		String sjrbmMc=request.getParameter("sjrbmMc");
		sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8"); 
		sjrbmMc = URLDecoder.decode(sjrbmMc, "UTF-8"); 
		Zjqd.setSjrBmmc(sjrbmMc);
		
		String sjr=request.getParameter("sjr");
		sjr = URLDecoder.decode(sjr, "UTF-8"); 
		sjr = URLDecoder.decode(sjr, "UTF-8"); 
		Zjqd.setSjr(sjr);
		
		String tjr=request.getParameter("tjr");
		tjr = URLDecoder.decode(tjr, "UTF-8"); 
		tjr = URLDecoder.decode(tjr, "UTF-8");
		Zjqd.setDjr(tjr);
		
		String djrq=request.getParameter("djrq");
		djrq = URLDecoder.decode(djrq, "UTF-8"); 
		djrq = URLDecoder.decode(djrq, "UTF-8"); 
		Zjqd.setDjrq(djrq);
		
		String tjrlxdh=request.getParameter("tjrlxdh");
		tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8"); 
		tjrlxdh = URLDecoder.decode(tjrlxdh, "UTF-8"); 
		Zjqd.setDjrlxdh(tjrlxdh);
		
		
		String djrsfzhm=request.getParameter("djrsfzhm");
		djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8"); 
		djrsfzhm = URLDecoder.decode(djrsfzhm, "UTF-8"); 
		Zjqd.setDsrsfzhm(djrsfzhm);
		
		zjqdDao.saveZjqd(Zjqd);
		
		//开始保存材料清单
		ClbDao clbDao=new ClbDao();	
		
		List<Clb> listClb=clbDao.findByZjqd(String.valueOf(bh), fydm);
		for(int i=0;i<listClb.size();i++){
			clbDao.delClb(listClb.get(i));
		}
		
		String cl=request.getParameter("cl");
		cl = URLDecoder.decode(cl, "UTF-8"); 
		cl = URLDecoder.decode(cl, "UTF-8"); 
		if(cl.contains(";")){
		String[] clist=cl.split(";");
			
		
		for(int i=0; i<clist.length;i++){
		  String[] clnr=clist[i].split(",");
		  Clb clb=new Clb();
		  ClbId clbid=new ClbId();
		  clb.setId(clbid);
		  clb.getId().setBh(Integer.valueOf(bh));
		  clb.getId().setFydm(fydm);
		  clb.getId().setXh(i);
		  clb.setClmc(clnr[0]);
		  clb.setFs(Integer.valueOf(clnr[1]));
		  clb.setYs(Integer.valueOf(clnr[2]));
		  clbDao.saveClb(clb); 
		}}		
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("after", "1");
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
        }catch (Exception e){	
        	e.printStackTrace();
        	Map result = new HashMap();
    		result.put("success", true);
    		result.put("after", "0");
    		JSONObject json = JSONObject.fromObject(result);
    		HttpHelper.renderJson(json.toString(), response);
        }		
	}

}
