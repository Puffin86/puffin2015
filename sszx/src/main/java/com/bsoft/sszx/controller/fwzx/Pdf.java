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

import com.bsoft.sszx.dao.ClbDao;
import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.dao.FyDao;
import com.bsoft.sszx.dao.ZjqdDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.entity.fjb.FjbId;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.DownloadUtil;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.PdfTemplateUtil;
@Controller
public class Pdf {

	@RequestMapping("downloadPdf")
	public void downloadPdf(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		String fydm=(String)session.getAttribute("fydm");
		String bh=request.getParameter("bh");
		
		FjDao fjDao=new FjDao();		
		Fjb fj = fjDao.findFjbByFjmc("送达回证", bh, fydm);
		if(fj==null){
			buildPdf(request,response,session);
		}
		
		String serverRealPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
		String fileName = fydm+"_"+bh+"_sdhz.pdf";
		
		DownloadUtil.download("送达回证_"+fileName, serverRealPath+"/"+fileName, request, response);
	}
	
	
	@RequestMapping("openPdf")
	public void openPdf(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		String fydm=(String)session.getAttribute("fydm");
		String bh=request.getParameter("bh");
		
		FjDao fjDao=new FjDao();		
		Fjb fj = fjDao.findFjbByFjmc("送达回证", bh, fydm);
		if(fj==null){
			buildPdf(request,response,session);
		}
		
		String fileName = fydm+"_"+bh+"_sdhz.pdf";
		session.setAttribute("fileName", fileName);
//		return "office/pdf";
		Map result = new HashMap();
//		result.put("success", true);
//		result.put("data", fileName);
		JSONObject json = JSONObject.fromObject(result);
		HttpHelper.renderJson(json.toString(), response);
	}
	
	
	public void buildPdf(HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		String fydm=(String)session.getAttribute("fydm");
		String fymc = new FyDao().fymc(fydm);
		String bh=request.getParameter("bh");
		Zjqd Zjqd=new ZjqdDao().findbyid(bh, fydm);
		
		String templatePath = request.getSession().getServletContext().getRealPath("/scan/template/")+"/sdhz.pdf";
		String serverRealPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
		String fileName = fydm+"_"+bh+"_sdhz.pdf";
		Map<String,String> valueMap = new HashMap<String, String>();
		valueMap.put("fymc", fymc);
		valueMap.put("ah", Zjqd.getAh());
		valueMap.put("ay", "");
		valueMap.put("ssdr", Zjqd.getDjr());
		valueMap.put("sddz", "");
		valueMap.put("clxx", Zjqd.getClqd());
		PdfTemplateUtil.fromPDFTempletToPdfWithValue(templatePath, serverRealPath+"/"+fileName,valueMap);
		
		FjDao fjDao=new FjDao();			
		int xh=fjDao.getMaxId(fydm,bh);
		Fjb fjb =new Fjb();
		FjbId FjbId=new FjbId();
		FjbId.setBh(Integer.valueOf(bh));
		FjbId.setFydm(fydm);
		FjbId.setXh(xh);
		fjb.setId(FjbId);
		fjb.setFjmc("送达回证");
		fjb.setFjdz(fileName);
		fjDao.saveFjb(fjb);
	}
	
}
