package com.bsoft.sszx.controller.flq;

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

import com.bsoft.sszx.dao.ECourtDao;
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

		String ah = request.getParameter("ah");
		if(ah==null||"".equals(ah)){
			return;
		}
		ah = URLDecoder.decode(ah, "UTF-8");
		ah = URLDecoder.decode(ah, "UTF-8");
		
		
//		List<Edsr> al = (List<Edsr>) new ECourtDao().findEdsr(ah);
//		
//		if(al!=null || al.size()>0){
//			
//			for(Edsr dsr : al){
//				String lx = dsr.getLx();
//				if(!"09_01001-1".equals(lx)){
//					dsr.setSfzhm(dsr.getZzjgdm());
//				}
//			}
//			
//		}
		
		List<Edsr> al = new ArrayList<Edsr>();
		
		Edsr aa = new Edsr();
		aa.setMc("aa");
		aa.setLx("lx1");
		aa.setLxdh("1234");
		aa.setSfzhm("2352345");
		al.add(aa);
		
		Edsr bb = new Edsr();
		bb.setMc("bb");
		bb.setLx("lx2");
		bb.setLxdh("1234");
		bb.setSfzhm("2388885");
		al.add(bb);
		
		JSONArray resultObj = JSONArray.fromObject(al);
		HttpHelper.renderJson(resultObj.toString(), response);
	}
	
	
	
	

}
