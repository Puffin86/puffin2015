package com.bsoft.sszx.controller.sys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;

@Controller
public class SysParam {
	private Logger logger = LoggerFactory.getLogger(SysParam.class);
	Properties props = null;
	@PostConstruct
	public void initM(){
//		logger.info("读取配置文件时出现异常",new FileNotFoundException("File not exists"));
//		logger.debug("WWWWWWWWWWWWWWWWWWWWWWWWWWW");
//		logger.error("AAAAAAAAAAAAAAAA");
//		try {
//			props = PropertiesLoaderUtils.loadAllProperties("sysParam.properties");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
	}
	
}
