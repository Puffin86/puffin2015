package com.bsoft.sszx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
