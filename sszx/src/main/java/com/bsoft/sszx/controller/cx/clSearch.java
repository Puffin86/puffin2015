package com.bsoft.sszx.controller.cx;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.ExportExcel;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class clSearch { 
	
	@ResponseBody
	@RequestMapping("clSearch")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception
	{
		List<Zjqd> al =getZjqd(request,session);
		JSONArray json = JSONArray.fromObject(al);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	private List<Zjqd> getZjqd(HttpServletRequest request, HttpSession session){
		List<Zjqd> al = new ArrayList<Zjqd>();
		String fydm=(String)session.getAttribute("fydm");
		String user=(String)session.getAttribute("user");
		String ah=request.getParameter("ah");
		String dsr=request.getParameter("dsr");
		String sjrbm=request.getParameter("sjrbm");
		String lx=request.getParameter("lx");
		
		String cbr=request.getParameter("cbr");
		String cbbm=request.getParameter("cbbm");
		String jbr=request.getParameter("jbr");
		String jbsj=request.getParameter("jbsj");
		String ywlx=request.getParameter("ywlx");
		String sx=request.getParameter("sx");
		
		String sql = "from Zjqd ";
		StringBuffer sqlBuf = new StringBuffer("from Zjqd ");
		if (lx.equals("2")||"4".equals(lx)){//法官 内勤
			sqlBuf.append(" where sjr='").append(user).append("' ");
		}else if (lx.equals("1") || lx.equals("3")){//管理员 服务中心人员
			sqlBuf.append(" where 1=1 ");
		}
		if(ah!=null && !"".equals(ah)){
			sqlBuf.append(" and ah like '%").append(ah).append("%' ");
		}
		if(dsr!=null&&!"".equals(dsr)){
			sqlBuf.append(" and djr like '%").append(dsr).append("%' ");
		}
		if(cbr!=null&&!"".equals(cbr)){
			sqlBuf.append(" and sjr like '%").append(cbr).append("%' ");
		}
		if(cbbm!=null&&!"".equals(cbbm)){
//			sqlBuf.append(" and sjrbmbm like '%").append(cbbm).append("' ");
		}
		if(jbr!=null&&!"".equals(jbr)){
			sqlBuf.append(" and zjr like '%").append(jbr).append("%' ");
		}
		if(jbsj!=null&&!"".equals(jbsj)){
			sqlBuf.append(" AND STR_TO_DATE(zjrq,'%Y-%c-%d') = STR_TO_DATE('").append(jbsj).append("','%Y-%c-%d')");
		}
		if(ywlx!=null&&!"".equals(ywlx)){
			sqlBuf.append(" and lclx like '%").append(ywlx).append("%' ");
		}
		if(sx!=null&&!"".equals(sx)){
			sqlBuf.append(" and sx =").append(Integer.parseInt(sx));
		}
		
		al =(List<Zjqd>) new ZjqdDao().findCljlBySQL(sqlBuf.toString());
		
		return al;
	}
	
	@ResponseBody
	@RequestMapping("export_cxjl")
	public void exportjl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		//获取数据
		List<Zjqd> al =getZjqd(request,session);
		//获取表头
		String[] jsonArr = request.getParameterValues("columns[]"); 
	    String[] hearders = new String[] {"序号", "企业编号", "企业名称", "所属省市", "详细地址", "邮政编码","优先级", "登记时间","状态"};//表头数组  
	    ExportExcel<Zjqd> ex = new ExportExcel<Zjqd>(); 
	    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
	    String filename = timeFormat.format(new Date())+".xls";  
	    response.setContentType("application/ms-excel;charset=UTF-8");  
	    response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
	    OutputStream out = response.getOutputStream();  
        ex.exportExcel(hearders, al, out);  
        out.close();  
        System.out.println("excel导出成功！");
	}
	
}
