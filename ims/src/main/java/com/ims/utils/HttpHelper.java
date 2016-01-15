package com.ims.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * @description TODO
 * 
 */
public class HttpHelper {

	/**
	 * 发送文本
	 */
	public static void renderText(String text, HttpServletResponse response) {
		render(response, "text/plain;charset=utf-8", text);
	}

	/**
	 * 发送html
	 */
	public static void renderHtml(String html, HttpServletResponse response) {
		render(response, "text/html;charset=utf-8", html);
	}

	/**
	 * 发送json
	 */
	public static void renderJson(String json, HttpServletResponse response) {
		render(response, "text/html;charset=utf-8", json);
		// render(response, "text/json;charset=utf-8", json);
		// render(response, "application/json;charset=utf-8", json);
	}

	/**
	 * 发送xml
	 */
	public static void renderXml(String xml, HttpServletResponse response) {
		render(response, "text/xml;charset=utf-8", xml);
	}

	/**
	 * 使用UTF-8编码发送内容
	 */
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
