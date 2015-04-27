package com.bsoft.sszx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.controller.fwzx.Fj;
import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.util.DownloadUtil;

/**
 * 下载附件
 */
@Controller
public class Xzfj {

	@RequestMapping("xzFj")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {

		String downloadPath = request.getParameter("downloadFile");

		String fileName = "";
		int position = downloadPath.lastIndexOf("\\");
		if (position > 0) {
			fileName = downloadPath.substring(position + 1);
		} else {
			fileName = downloadPath;
		}
		
		DownloadUtil.download(fileName, downloadPath, request, response);
	}
	
	
	@RequestMapping("xzFj2")
	public void xzFj2(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {

		String xh = request.getParameter("xh");
		String bh = request.getParameter("bh");
		String fydm = (String)session.getAttribute("fydm");
//		System.out.println(xh+"@"+bh+"fydm"+fydm);
		
		Fjb fj = new FjDao().findFjb(bh, xh, fydm); 
		String downloadPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
		String fileName = fj.getFjdz();
		DownloadUtil.download(fileName, downloadPath+"//"+fileName, request, response);
	}

}
