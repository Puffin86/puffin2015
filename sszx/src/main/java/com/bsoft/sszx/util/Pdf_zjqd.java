package com.bsoft.sszx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.bsoft.sszx.entity.clb.Clb;
import com.bsoft.sszx.entity.zjqd.Zjqd;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 转接清单PDF
 * @author shaobaitong
 *
 */
public class Pdf_zjqd {
	
	 /**
	  * 生成PDF的方法
	  * @return boolean
	  */
	 public boolean createPDF_zjqd(String mPdfPath,String fileName){
	    Document document = new Document();//建立一个Document对象
	    document.setPageSize(PageSize.A4);//设置页面大小
	    try{
	       File file = new File(mPdfPath+fileName);
	       if(file.exists()){
	    	   System.out.println("文件已经存在，无需生成...");
	    	   return true;
	       }
	    	
	       PdfWriter.getInstance(document,new FileOutputStream(file));//建立一个PdfWriter对象
	       document.open();  
	       BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
	       Font headFont = new Font(bfChinese, 10, Font.BOLD);//设置字体大小 
	       Font bodyFont = new Font(bfChinese, 8, Font.BOLD);//设置字体大小 
	       //Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);//设置字体大小 
	       float[] widths = {60f, 160f, 110f, 110f, 110f};//设置表格的列宽
	       PdfPTable table = new PdfPTable(widths);//建立一个pdf表格
	       table.setSpacingBefore(130f);//设置表格上面空白宽度
		   table.setTotalWidth(535);//设置表格的宽度             
		   table.setLockedWidth(true);//设置表格的宽度固定
		   //table.getDefaultCell().setBorder(0);//设置表格默认为无边框
		   PdfPCell cell = new PdfPCell(new Paragraph("诉讼材料转接清单",headFont));//建立一个单元格
		   //cell.setBorder(0);//设置单元格无边框
		   cell.setColspan(5);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setMinimumHeight(40f);//设置单元格最小高度
		   table.addCell(cell);//增加单元格
		   
		   cell = new PdfPCell(new Paragraph("编号",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   //cell.setMinimumHeight(60f);//设置单元格最小高度
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("案号",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("承办人",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("日期",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("当事人联系电话",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   //---------------------------------
		   
		   cell = new PdfPCell(new Paragraph("1",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("2",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("3",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("4",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("12345",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   
		   //--------------------------------------
		   cell = new PdfPCell(new Paragraph("材料名称",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   cell.setColspan(3);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("页数",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("份数",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   //-----------------------------------------
		   
		   cell = new PdfPCell(new Paragraph("材料1",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   cell.setColspan(3);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("1",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("2",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   //-----------------------------------------
		   
		   
		   cell = new PdfPCell(new Paragraph("转接人",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   cell.setMinimumHeight(40f);//设置单元格最小高度
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph("",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph("当事人签名",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph("",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   document.add(table);
	     }catch (DocumentException de) {
	    	 de.printStackTrace();
             return false;
	     }catch (IOException ioe) {
	    	 ioe.printStackTrace();
             return false;
	     }
	     document.close();
	     return true;
	 }

	 
	 /**
	  * 生成转接清单PDF的方法
	  * @return boolean
	  */
	 public boolean createPDF_zjqd(String mPdfPath,String fileName,Zjqd zjqd,List clbList){
	    Document document = new Document();//建立一个Document对象
	    document.setPageSize(PageSize.A4);//设置页面大小
	    try{
	       File file = new File(mPdfPath+fileName);
	       if(file.exists()){
	    	   System.out.println("文件已经存在，无需生成...");
	    	   return true;
	       }
	       
	       if(zjqd==null){
	    	   System.out.println("该转接清单有误，请检查后再重试...");
	    	   return false;
	       }
	       
	       
	       PdfWriter.getInstance(document,new FileOutputStream(file));//建立一个PdfWriter对象
	       document.open();  
	       BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
	       Font headFont = new Font(bfChinese, 10, Font.BOLD);//设置字体大小 
	       Font bodyFont = new Font(bfChinese, 8, Font.BOLD);//设置字体大小 
	       //Font headFont2 = new Font(bfChinese, 10, Font.NORMAL);//设置字体大小 
	       float[] widths = {60f, 160f, 110f, 110f, 110f};//设置表格的列宽
	       PdfPTable table = new PdfPTable(widths);//建立一个pdf表格
	       table.setSpacingBefore(130f);//设置表格上面空白宽度
		   table.setTotalWidth(535);//设置表格的宽度             
		   table.setLockedWidth(true);//设置表格的宽度固定
		   //table.getDefaultCell().setBorder(0);//设置表格默认为无边框
		   PdfPCell cell = new PdfPCell(new Paragraph("诉讼材料转接清单",headFont));//建立一个单元格
		   //cell.setBorder(0);//设置单元格无边框
		   cell.setColspan(5);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setMinimumHeight(40f);//设置单元格最小高度
		   table.addCell(cell);//增加单元格
		   
		   cell = new PdfPCell(new Paragraph("编号",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   //cell.setMinimumHeight(60f);//设置单元格最小高度
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("案号",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("承办人",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("日期",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("当事人联系电话",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   //---------------------------------
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getId().getBh().toString(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getAh(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getSjrXm(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getDjrq(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getDjrlxdh(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   
		   //--------------------------------------
		   cell = new PdfPCell(new Paragraph("材料名称",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   cell.setColspan(3);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("页数",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   cell = new PdfPCell(new Paragraph("份数",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   //-------------------循环材料信息----------------------
		   
		   if(clbList!=null && clbList.size()>0){
			   
			   for(int i=0;i<clbList.size();i++){
				   Clb m=(Clb)clbList.get(i);
					
				   cell = new PdfPCell(new Paragraph(m.getClmc(),bodyFont));
				   //cell.setBorder(0);
				   cell.setFixedHeight(20);
				   cell.setColspan(3);//设置合并单元格的列数
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph(m.getYs().toString(),bodyFont));
				   //cell.setBorder(0);
				   cell.setFixedHeight(20);
				   //cell.setColspan(2);//设置合并单元格的列数
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph(m.getFs().toString(),bodyFont));
				   //cell.setBorder(0);
				   cell.setFixedHeight(20);
				   //cell.setColspan(2);//设置合并单元格的列数
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
				   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				   table.addCell(cell);
			   }
			   
		   }else{
			   cell = new PdfPCell(new Paragraph("",bodyFont));
			   //cell.setBorder(0);
			   cell.setFixedHeight(20);
			   cell.setColspan(3);//设置合并单元格的列数
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   table.addCell(cell);
			   
			   cell = new PdfPCell(new Paragraph("",bodyFont));
			   //cell.setBorder(0);
			   cell.setFixedHeight(20);
			   //cell.setColspan(2);//设置合并单元格的列数
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   table.addCell(cell);
			   
			   cell = new PdfPCell(new Paragraph("",bodyFont));
			   //cell.setBorder(0);
			   cell.setFixedHeight(20);
			   //cell.setColspan(2);//设置合并单元格的列数
			   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
			   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			   table.addCell(cell);
		   }
		   
		   
		   
		   
		   //-----------------------------------------
		   
		   
		   cell = new PdfPCell(new Paragraph("转接人",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   cell.setMinimumHeight(40f);//设置单元格最小高度
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph(zjqd.getZjrXm(),bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph("当事人签名",headFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   //cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		   table.addCell(cell);
		   
		   
		   cell = new PdfPCell(new Paragraph("",bodyFont));
		   //cell.setBorder(0);
		   cell.setFixedHeight(20);
		   cell.setColspan(2);//设置合并单元格的列数
		   cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置内容水平居中显示
		   cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		   table.addCell(cell);
		   
		   
		   document.add(table);
	     }catch (DocumentException de) {
	    	 de.printStackTrace();
             return false;
	     }catch (IOException ioe) {
	    	 ioe.printStackTrace();
             return false;
	     }
	     document.close();
	     return true;
	 }
	 
	 
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  // TODO 自动生成方法存根
	  Pdf_zjqd mCreatPDF=new Pdf_zjqd();
	  mCreatPDF.createPDF_zjqd("e:/","test.pdf");

	 }
	
}
