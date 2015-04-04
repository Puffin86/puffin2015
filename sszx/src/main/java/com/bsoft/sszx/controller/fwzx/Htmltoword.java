package com.bsoft.sszx.controller.fwzx;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.entity.fjb.FjbId;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.bsoft.sszx.util.HttpHelper;

import net.sf.json.JSONObject;

@Controller
public class Htmltoword  {
	@ResponseBody
	@RequestMapping("htmltoword")
	public void execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		
    String body = request.getParameter("nr");

    String content = "<html><head></head><body>" + body + "</body></html>";
    InputStream is = new ByteArrayInputStream(content.getBytes("GBK"));
//    String path = ServletActionContext.getServletContext().getRealPath("/scan/jpg/");
    String path = "afd";
    File dirPath = new File(path);
    if(!dirPath.exists()) dirPath.mkdirs();
    
    Zjqd zjqd=(Zjqd)session.getAttribute("wordZjqd");
    String FileName=zjqd.getId().getFydm()+"_"+zjqd.getId().getBh()+"_"+zjqd.getLclx()+".doc";
    String FileNameFull=path+"/"+FileName;
    String  fydm=(String)session.getAttribute("fydm"); 
    
    File file=new File(FileNameFull);
    int newFileBz=1;
    if(file.exists()) {
    	Fjb Fjb= new FjDao().findFjbBymc(FileName, String.valueOf(zjqd.getId().getBh()), fydm);
    	if(Fjb!=null)
    	newFileBz=0;
    }
    
    OutputStream os = new FileOutputStream(FileNameFull);
    this.inputStreamToWord(is, os);
    
    if(newFileBz==1){
    FjDao fjDao=new FjDao();
    String fjmc="诉讼材料转接清单";
    String bh=String.valueOf(zjqd.getId().getBh());  
   
	
    int xh=fjDao.getMaxId(fydm,bh);
	Fjb fjb =new Fjb();
	FjbId FjbId=new FjbId();
	FjbId.setBh(Integer.valueOf(bh));
	FjbId.setFydm(fydm);
	FjbId.setXh(xh);
	
	fjb.setId(FjbId);
	fjb.setFjmc(fjmc);
	fjb.setFjdz(FileName);
	
	fjDao.saveFjb(fjb);}
    
	Map result = new HashMap();
	result.put("success", true);
	result.put("after", "1");
	JSONObject json = JSONObject.fromObject(result);
	HttpHelper.renderJson(json.toString(), response);
	
 }
 
 /**
  * 把is写入到对应的word输出流os中
  * 不考虑异常的捕获，直接抛出
  * @param is
  * @param os
  * @throws IOException
  */
 private void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
    POIFSFileSystem fs = new POIFSFileSystem();
    //对应于org.apache.poi.hdf.extractor.WordDocument
    fs.createDocument(is, "WordDocument");
    fs.writeFilesystem(os);
    os.close();
    is.close();
 }


}
