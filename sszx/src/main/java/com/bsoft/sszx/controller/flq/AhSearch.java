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
import com.bsoft.sszx.dao.UserDao;
import com.bsoft.sszx.entity.eaj.Eaj;
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.Tree;

import net.sf.json.JSONObject;

/**
 * 案号查询
 */
@Controller
public class AhSearch {

	@ResponseBody
	@RequestMapping("ahSearch")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {

		String fydm = (String) session.getAttribute("fydm");

		Map<String, Object> map = new HashMap<String, Object>();

		String ahN = request.getParameter("ahN");
		ahN = URLDecoder.decode(ahN, "UTF-8");
		ahN = URLDecoder.decode(ahN, "UTF-8");

		String ahG = request.getParameter("ahG");
		ahG = URLDecoder.decode(ahG, "UTF-8");
		ahG = URLDecoder.decode(ahG, "UTF-8");
		
		String ahDsr = request.getParameter("ahDsr");
		ahDsr = URLDecoder.decode(ahDsr, "UTF-8");
		ahDsr = URLDecoder.decode(ahDsr, "UTF-8");

		String user = fydm + (String) session.getAttribute("user");

		List<Eaj> al = null;
		String lx = request.getParameter("lx");
		if (lx.equals("1")) {
			al = (List<Eaj>) new ECourtDao().findAhByUser(ahN, ahG, ahDsr, user);
		} else {
			al = (List<Eaj>) new ECourtDao().findAh(ahN, ahG,ahDsr);
		}

		List<Tree> tree = new ArrayList<Tree>();
		for (int i = 0; i < al.size(); i++) {
			Tree leaf = new Tree();
			leaf.setId(i+"");
			leaf.setState("open");

			String ah = al.get(i).getAh();
			String cbr = al.get(i).getCbr();
			String cbbm = al.get(i).getCbbm1();
			String ahdm = al.get(i).getAhdm();

			UserDao userDao = new UserDao();

			
			if(cbr!=null)
				cbr = cbr.replace(fydm, "").replaceAll(" ", "");
			else
				cbr="";
			String cbrxm = "";
			String text = ah;
			if(cbr.length()>0){
				cbrxm = userDao.findUserById(cbr, fydm).getYhxm();
				text = text + " 承办人：" + cbrxm;
			}

			String bmmc = "";
			if(cbbm!=null)
				cbbm = cbbm.replaceAll(" ", "");
			else
				cbbm="";
			if(cbbm.length()>0){
				bmmc = userDao.findBm(cbbm, fydm).getBmmc();
			}
			
			leaf.setText(text);

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("leaf", "true");
			attributes.put("ah", ah);
			attributes.put("bmmc", bmmc);
			attributes.put("cbrxm", cbrxm);
			attributes.put("cbr", cbr);
			attributes.put("cbbm", cbbm);
			attributes.put("ahdm", ahdm);
			leaf.setAttributes(attributes);
			tree.add(leaf);
		}

		map.put("data", tree);

		JSONObject resultObj = JSONObject.fromObject(map);
		HttpHelper.renderJson(resultObj.toString(), response);
	}

}
