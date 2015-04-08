package com.bsoft.sszx.controller.fwzx;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bsoft.sszx.dao.FjDao;
import com.bsoft.sszx.entity.fjb.Fjb;
import com.bsoft.sszx.entity.fjb.FjbId;

@Controller
public class FileUploadAction   {
	
	@ResponseBody  
    @RequestMapping("fileUpload")  
    public String xlsUpload(HttpServletRequest request,HttpSession session) {
		String flag="success";
		String fydm=(String)session.getAttribute("fydm");
		String bh = (String)request.getParameter("bh");
		String fjmc2 = (String)request.getParameter("fjmc2");
            // 转型为MultipartHttpRequest：  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        // 获得文件：  
        MultipartFile uploadFile = multipartRequest.getFile("fileToUpload");  
        // 获得文件名：  
        String filename = uploadFile.getOriginalFilename();  
//        System.out.println(filename);  
        // 获得输入流：  
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssFFF");//设置日期格式
		String fileNameNew=df.format(new Date());		 
		String serverRealPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
        if (!uploadFile.isEmpty()) {
        	try {   
     		    int las=filename.lastIndexOf(".");
     		    String ext="";
     		    if(las>0){
     		    	ext=filename.substring(las);
     		    }
     		    String targetFileName=fydm+"_"+fileNameNew+ext;
     		   SaveFileFromInputStream(uploadFile.getInputStream(),serverRealPath,targetFileName);   
               FjDao fjDao=new FjDao();			
       			int xh=fjDao.getMaxId(fydm,bh);
       			Fjb fjb =new Fjb();
       			FjbId FjbId=new FjbId();
       			FjbId.setBh(Integer.valueOf(bh));
       			FjbId.setFydm(fydm);
       			FjbId.setXh(xh);
       			
       			fjb.setId(FjbId);
       			fjb.setFjmc(fjmc2);
       			fjb.setFjdz(targetFileName);
       			
       			fjDao.saveFjb(fjb);
        	}catch (IOException e) {   
        		flag="error";
                e.printStackTrace();  
            }  
         }
        return flag;
    }
	
	
	
	/**保存文件  
     * @param stream  
     * @param path  
     * @param filename  
     * @throws IOException  
     */  
    public void SaveFileFromInputStream(InputStream stream,String path,String filename) throws IOException   
    {
    	File file = new File(path + "/"+ filename);
		    if(!file.exists()){
//		    	dir.mkdir();
		    	file.createNewFile();
		    }
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);   
        byte[] buffer =new byte[1024*1024];   
        int bytesum = 0;   
        int byteread = 0;    
        while ((byteread=stream.read(buffer))!=-1)   
        {   
           bytesum+=byteread;   
           fs.write(buffer,0,byteread);   
           fs.flush();   
        }    
        fs.close();   
        stream.close();         
    } 
	
}
