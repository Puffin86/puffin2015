package com.bsoft.sszx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 */
public class DownloadUtil {

	/**
	 * 根据源文件名和路径下载源文件
	 * 
	 * @param fileName
	 *            源文件名
	 * @param filePath
	 *            源文件路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void download(String fileName, String filePath,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType("application/x-download");

		File sourceFile = new File(filePath);

		// 兼容迅雷，需加上此设置
		response.setContentLength((int) sourceFile.length());

		OutputStream out = null;
		FileInputStream in = null;
		try {
			out = response.getOutputStream();
			in = new FileInputStream(filePath);

			byte[] b = new byte[1024];
			int i = 0;

			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}

}
