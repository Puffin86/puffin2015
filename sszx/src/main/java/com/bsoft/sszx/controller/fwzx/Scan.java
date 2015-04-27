package com.bsoft.sszx.controller.fwzx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.entity.fjb.FjbId;

@Controller
public class Scan {

	@RequestMapping("saveScan")
	public void saveScan(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
            String savePath = request.getSession().getServletContext().getRealPath("/scan/jpg/")+"//";
		    String pic_base_64_data=request.getParameter("picData");
		    String fileFormat=request.getParameter("picExt");
		    String bh = request.getParameter("bh");
		    String fydm = request.getParameter("fydm");
		    String fjmc = request.getParameter("fjmc");
		    
		    FjDao fjDao=new FjDao();			
			int xh=fjDao.getMaxId(fydm,bh);
			String fileName = fydm+"_"+bh+"_"+xh+fileFormat;
		    
		    sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
		    byte[] datas=decode.decodeBuffer(pic_base_64_data.substring(1, pic_base_64_data.length()-2));
		    File file=new File(savePath+fileName+fileFormat);
		    OutputStream fos=new FileOutputStream(file);
		    fos.write(datas);
		    fos.close();
		    
			Fjb fjb =new Fjb();
			FjbId FjbId=new FjbId();
			FjbId.setBh(Integer.valueOf(bh));
			FjbId.setFydm(fydm);
			FjbId.setXh(xh);
			fjb.setId(FjbId);
			fjb.setFjmc(fjmc);
			fjb.setFjdz(fileName);
			fjDao.saveFjb(fjb);
		    
	}
	
}
