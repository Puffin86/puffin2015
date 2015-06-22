package com.bsoft.sszx.xfireclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

//import org.codehaus.xfire.client.Client;

import com.bsoft.sszx.entity.sms.SmsBean;

public class SMSClient {
	
	
	private String xtbh="";//系统编号
	private String fsr_fybm = "";//法院编码
	private String fsr_userid="";//发送人ID 1300-linjd OA中获取
	private String jssjhm="";//接收手机号码；
	
	/**
	 * 发送短信
	 * @return
	 */
//	public boolean sendSMS(List<SmsBean> smsList){
//		boolean serviceFlag = false;
//		
//		if(smsList==null || smsList.size()==0){
//			return serviceFlag;
//		}
//		
//		try {
////			http://203.0.64.68/dxpt/services/SendSMS?wsdl
//			Client client = new Client(new URL("http://dxpt.zj.pcc/dxpt/services/SendSMS?wsdl"));
//			
//			Object[][] smsArr = new Object[smsList.size()][10];
//			for(int i =0;i<smsList.size();i++){
//				SmsBean bean = smsList.get(i);
//				smsArr[i][0] = bean.getXtbh();
//				smsArr[i][1] = bean.getXtbh();
//				smsArr[i][2] = bean.getXtbh();
//				smsArr[i][3] = bean.getXtbh();
//				smsArr[i][4] = bean.getXtbh();
//				smsArr[i][5] = bean.getXtbh();
//				smsArr[i][6] = bean.getXtbh();
//				smsArr[i][7] = bean.getXtbh();
//				smsArr[i][8] = bean.getXtbh();
//				smsArr[i][9] = bean.getXtbh();
//			}
//			Object[] result1 = client.invoke("sendMessage", smsArr); 
//			System.out.println(result1[0]); 
//			serviceFlag = true;
//		} catch (MalformedURLException e) { 
//			e.printStackTrace(); 
//		} catch (Exception e) { 
//			e.printStackTrace(); 
//		}finally{
//			return true;
//		}
//		
//	}
//	
//	/**
//	 * 查询短信状态
//	 */
//	public void searchSMSZT(){
//		try {
////			http://203.0.64.68/dxpt/services/SendSMS?wsdl
//			Client client = new Client(new URL("http://dxpt.zj.pcc/dxpt/services/SearchSMS?wsdl")); 
//			Object[] result1 = client.invoke("getFszt", new Object[] {1, 2}); 
//			System.out.println(result1[0]); 
//		} catch (MalformedURLException e) { 
//			e.printStackTrace(); 
//		} catch (Exception e) { 
//			e.printStackTrace(); 
//		}
//		
//	}
//	
//	/**
//	 * 删除未发送短信
//	 * @return
//	 */
//	public boolean delSMS(){
//		boolean serviceFlag = false;
//		try {
//			Client client = new Client(new URL("http://dxpt.zj.pcc/dxpt/services/SendSMS?wsdl")); 
//			Object[] result1 = client.invoke("delSMS", new Object[] {1, 2}); 
//			System.out.println(result1[0]); 
//			serviceFlag = true;
//		} catch (MalformedURLException e) { 
//			e.printStackTrace(); 
//		} catch (Exception e) { 
//			e.printStackTrace(); 
//		}finally{
//			return true;
//		}
//	}
	
}
