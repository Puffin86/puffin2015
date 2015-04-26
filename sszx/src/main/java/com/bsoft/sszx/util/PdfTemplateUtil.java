package com.bsoft.sszx.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfTemplateUtil {
	
	/**
	  * 根据pdf模板填充相应的值： 1，如果是根据excel填充的话，在用Acrobat生成PDF模板前，
	  * Excel单元格格式最好设置成文本，否则pdf填充值时可能中文无法显示
	  */
	 public static void fromPDFTempletToPdfWithValue(String templatePath,String path,Map<String,String> fieldValueMap) {
	  String fileName = templatePath; 
	  try {
	   PdfReader reader = new PdfReader(fileName);
	   ByteArrayOutputStream bos = new ByteArrayOutputStream();
	   PdfStamper ps = new PdfStamper(reader, bos);
	   /**
	    * 使用中文字体 如果是利用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体就行了
	    */
//	   BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//	   Font font = new Font(bf, 12, Font.NORMAL);
	   AcroFields s = ps.getAcroFields();
//	   System.out.println("s: " + s);
//	   System.out.println("AcroFields: " + s.getFields());
//	   System.out.println("AcroFields.class: " + s.getFields().getClass());
//	   System.out.println("getSignatureNames: " + s.getSignatureNames());
//	   System.out.println("getSignatureNames: " + s.getTotalRevisions());
//	   System.out.println("s: " + s.getBlankSignatureNames());
//	   System.out.println("s: " + s.getFieldCache());
//	   System.out.println("s: " + s.getSubstitutionFonts());
	   /*
	    * int i = 1; for (Iterator it = s.getFields().keySet().iterator();
	    * it.hasNext(); i++) { String name = (String) it.next(); String
	    * value = s.getField(name); System.out.println("[" + i + "- name:" +
	    * name + ", value: "+value+"]"); s.setField(""+name.trim(),
	    * "aaa一二三"); }
	    * 
	    * s.setField("Text1", "NOHI"); s.setField("Text2", "2011-04-05");//
	    * 注意pdf中域的大小，这里设置的值太长，pdf中会显示不全
	    */
	   // 设置为true/false在点击生成的pdf文档的填充域时有区别，
	   
	   s.setField("fymc", fieldValueMap.get("fymc"));
	   s.setField("ah", fieldValueMap.get("ah"));
	   s.setField("ay", fieldValueMap.get("ay"));
	   s.setField("ssdr",fieldValueMap.get("ssdr"));
	   s.setField("sddz", fieldValueMap.get("sddz"));
	   s.setField("clxx", fieldValueMap.get("clxx"));
	   
	   //Document document = new Document();
	   //document.open();
//	   Image gif = Image.getInstance("d:\\图片.jpg");
//	   gif.setDpi(100, 100);
//	   gif.setBorderWidth(200);
//	   gif.scaleAbsolute(80, 100); 
//	   gif.setAbsolutePosition(400, 700);
//	   PdfContentByte over = ps.getOverContent(1);
//	   over.addImage(gif);
	   
	   ps.setFormFlattening(true);
	   ps.close();
	   FileOutputStream fos = new FileOutputStream(path);
	   fos.write(bos.toByteArray());
	  } catch (FileNotFoundException e) {
	   e.printStackTrace();
	  } catch (Exception e) {
	   e.printStackTrace();
	  } finally {
	   // doc.close();
	  }
	 }
	
	
}
