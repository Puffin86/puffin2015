package com.bsoft.sszx.xfireclient;

import dxpt.Client.SendSMSClient;

public class sendtest {
	public static void main(String[] args)
 {
    SendSMSClient client = new SendSMSClient();
   
    String sms[]={"你好","再见"};//短信内容
    String xtbh="023";
    String FSR_FYBM ="1303";
    String FSR_USERID="1303-zhanghy";//用户账户命名规则1303+姓全拼名首字母，此处张海瑛
    String JSSJHM="13858125674";
    String JSRMC="陈剑刚";
    String ID2[]={"130302320157241","130302320157242"};//法院代码+系统代码+日期+序号
    
    // String s = service.test("1", "2");
    String[][] sendArr=new String[2][10];//没有的位置是留下空字符串，不是null
    for(int i=0;i<2;i++){
    	String FSNR=sms[i]; 
    	String ID=ID2[i];
    	for(int j=0;j<10;j++){
    		if(j==0)
    		      sendArr[i][j]=xtbh;
    		if(j==1)
    		      sendArr[i][j]=FSR_FYBM;
    		if(j==2)
      		      sendArr[i][j]=FSR_USERID;
    		if(j==3)
        		  sendArr[i][j]=JSSJHM;
    		if(j==4)
      		  sendArr[i][j]=JSRMC;
    		if(j==5)
    		  sendArr[i][j]=FSNR;
    		if(j==6)
      		  sendArr[i][j]="";
    		if(j==7)
      		  sendArr[i][j]="";    		
    		if(j==8)
      		  sendArr[i][j]=ID;
    		if(j==9)
      		  sendArr[i][j]="";
    	}
    }
    
    for(int i=0;i<2;i++){
    	for(int j=0;j<9;j++){
        System.out.println(sendArr[i][j]);
      }
    }
    
    String[][] back=new String[2][2];
    back=client.SendMessage(sendArr, null);
    
    for(int i=0;i<2;i++){
    	for(int j=0;j<2;j++){
        System.out.println(back[i][j]);
      }
    }
 }
}
