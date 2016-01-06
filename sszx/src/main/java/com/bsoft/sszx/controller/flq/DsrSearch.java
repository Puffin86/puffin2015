package com.bsoft.sszx.controller.flq;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.ECourt4ZXDao;
import com.bsoft.sszx.dao.ECourtDao;
import com.bsoft.sszx.entity.eaj.Eaj4ZX;
import com.bsoft.sszx.entity.edsr.Edsr;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DsrSearch {

	@ResponseBody
	@RequestMapping("dsrSearch")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		Map<String, Object> map = new HashMap<String, Object>();

		String ah = request.getParameter("ah");
		ah = URLDecoder.decode(ah, "UTF-8");
		ah = URLDecoder.decode(ah, "UTF-8");

		String ahdm = new ECourtDao().findByAh(ah).getAhdm();
		List<Edsr> al = (List<Edsr>) new ECourtDao().findEdsr(ahdm);
		List<Tree> tree = (List<Tree>) new ArrayList();
		if(al!=null){
			for (int i = 0; i < al.size(); i++) {
				Tree leaf = new Tree();
				leaf.setId(i+"");
				leaf.setState("open");

				String dsrxm = al.get(i).getMc();
				String sfzhm = al.get(i).getSfzhm();
				if (sfzhm == null)
					sfzhm = "";
				String lxdh = al.get(i).getLxdh();
				if (lxdh == null)
					lxdh = "";

				String text = ah + " 姓名：" + dsrxm;
				leaf.setText(text);

				Map<String, String> attributes = new HashMap<String, String>();
				attributes.put("leaf", "true");
				attributes.put("ah", ah);
				attributes.put("dsrxm", dsrxm);
				attributes.put("lxdh", lxdh);
				attributes.put("sfzhm", sfzhm);
				leaf.setAttributes(attributes);
				tree.add(leaf);
			}
		}
		map.put("data", tree);
		JSONObject resultObj = JSONObject.fromObject(map);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	
	@ResponseBody
	@RequestMapping("dsrSearchList")
	public void dsrSearchList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String fydm = (String) session.getAttribute("fydm");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Edsr> al = null; 
		String ah = request.getParameter("ah");
		String cbbm = request.getParameter("cbbm");
		String ahText = request.getParameter("ahText");
		if(ah==null||"".equals(ah)){
			return;
		}else{
			ah = URLDecoder.decode(ah, "UTF-8");
			ah = URLDecoder.decode(ah, "UTF-8");
			if(cbbm!=null && "33010361".equals(cbbm)){//执行局
				al=	getDsrListFromZX(ah);
			}else{
				al=	getDsrListFromSP(ah);
			}
			
		}
		
		JSONArray resultObj = JSONArray.fromObject(al);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Edsr> getDsrListFromSP(String ah){
		List<Edsr> al = null; 
		al=	(List<Edsr>) new ECourtDao().findEdsr(ah);
	
		if(al!=null && al.size()>0){
			for(Edsr dsr : al){
				String lx = dsr.getLx();
				if(!"09_01001-1".equals(lx)){
					dsr.setSfzhm(dsr.getZzjgdm());
				}
			}
			
		}
		return al;
	}
	
	@SuppressWarnings("unchecked")
	private List<Edsr> getDsrListFromZX(String ah) {
		List<Edsr> al = new ArrayList<Edsr>(); 
		List<Eaj4ZX> list =	(List<Eaj4ZX>) new ECourt4ZXDao().findEdsr(ah);
	
		if(list!=null && list.size()>0){
			
			for(Eaj4ZX eaj : list){
				Edsr edsr = new Edsr();
				edsr.setMc(eaj.getMc());
				edsr.setDz(eaj.getDz());
				edsr.setLxdh(eaj.getDh());
				edsr.setLx(eaj.getSsdw());
				edsr.setSfzhm("");
				al.add(edsr);
			}
//			for(Edsr dsr : al){
//				String lx = dsr.getLx();
//				if(!"09_01001-1".equals(lx)){
//					dsr.setSfzhm(dsr.getZzjgdm());
//				}
//			}
			
		}
		return al;
	}

}
