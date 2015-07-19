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

import net.sf.json.JSONObject;

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
import com.bsoft.sszx.util.HttpHelper;
import com.bsoft.sszx.util.StringUtil;

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
     		    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
     		   SaveFileFromInputStream(uploadFile.getInputStream(),serverRealPath,targetFileName);  
     		  System.out.println("bbbbbbbbbbbbb");
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
       			System.out.println("cccccccccccc");
       			return flag;
        	}catch (IOException e) {   
        		flag="error";
                e.printStackTrace();  
            }  
         }
        return flag;
    }
	
//	map.put("total", all.size());
//	map.put("rows", al);
//	JSONObject resultObj=JSONObject.fromObject(map); //将map对象转换成为json对象
//	HttpHelper.renderJson(resultObj.toString(), response);
	
	
	@ResponseBody  
    @RequestMapping("tempfileUpload")  
    public String pdfUpload(HttpServletRequest request,HttpSession session) {
		String flag="success";
        // 转型为MultipartHttpRequest：  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
        Map<String, MultipartFile> fiels =  multipartRequest.getFileMap();
        String uploadParameters = multipartRequest.getParameter("uploadParameters");
        System.out.println(uploadParameters);
        Map<String,Object> parameters = StringUtil.parseStr(uploadParameters);
//        String fydm=(String)parameters.get("fydm");
//		String bh = (String)parameters.get("bh");
		String fileName = (String)parameters.get("fileName");
        
        System.out.println(fiels.size());
        // 获得文件：  
        MultipartFile uploadFile = multipartRequest.getFile("tempfile");  
        // 获得文件名：  
//        String filename = uploadFile.getOriginalFilename();  
//        System.out.println(filename);  
        // 获得输入流：  
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssFFF");//设置日期格式
//		String fileNameNew=df.format(new Date());		 
		String serverRealPath = request.getSession().getServletContext().getRealPath("/scan/jpg/");
        if (!uploadFile.isEmpty()) {
        	try {   
     		   SaveFileFromInputStream(uploadFile.getInputStream(),serverRealPath,fileName);   
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
