package com.bsoft.sszx.xfireclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.bsoft.sszx.entity.sms.SmsBean;

import dxpt.Client.SearchSMSClient;
import dxpt.Client.SendSMSClient;

public class SMSClient {
	
	private String xtbh="";//系统编号
	private String fsr_fybm = "";//法院编码
	private String fsr_userid="";//发送人ID 1300-linjd OA中获取
	private String jssjhm="";//接收手机号码；
	
	
	/**
	 * 发送短信
	 * @return
	 */
	public String[][] sendSMS(SmsBean bean){
		String[][] sendRet = null;
		if(bean==null || bean.getFsr_userid()==null || bean.getJssjhm()==null 
				|| bean.getJsrmc()==null || bean.getFsnr()==null){
			//啥都不干
		}else{
			String[][] smsArr = new String[1][10];
			smsArr[0][0] = bean.getXtbh();
			smsArr[0][1] = bean.getFsr_fybm();
			smsArr[0][2] = bean.getFsr_userid();
			smsArr[0][3] = bean.getJssjhm();
			smsArr[0][4] = bean.getJsrmc();
			smsArr[0][5] = bean.getFsnr();
			smsArr[0][6] = bean.getDsfssj();
			smsArr[0][7] = bean.getFssm();
			smsArr[0][8] = bean.getId2();
			smsArr[0][9] = bean.getAh();
			SendSMSClient smsClient  = new SendSMSClient();
			sendRet = smsClient.SendMessage(smsArr, null);
		}
		return sendRet;
//		返回数据也为二维数组：
//		Arr[i][0] 业务系统短信息唯一id号
//		Arr[i][1] 发送结果编号（01 发送成功；02 发送人不存在；03 接收人手机号码长度不为11位；04 接收人手机号码没有以指定数字开头 ；05 发送内容超过指定数字（目前限定为600） ；06 定时发送时间格式不准确，格式为2013-11-07 20:02:00；07 必输项填写不完整；08 外省号码；09 其他错误;10 发送内容中有多个'#'，可能是短信模板；11 短信重复，无法发送；12  业务系统短信唯一编号大于45位）
	}
	
	
	/**
	 * 发送短信
	 * @return
	 */
	public String[][] sendSMSList(List<SmsBean> smsList){
		String[][] sendRet = null;
		if(smsList!=null && smsList.size()>0){
			String[][] smsArr = new String[smsList.size()][10];
			for(int i =0;i<smsList.size();i++){
				SmsBean bean = smsList.get(i);
				smsArr[i][0] = bean.getXtbh();
				smsArr[i][1] = bean.getFsr_fybm();
				smsArr[i][2] = bean.getFsr_userid();
				smsArr[i][3] = bean.getJssjhm();
				smsArr[i][4] = bean.getJsrmc();
				smsArr[i][5] = bean.getFsnr();
				smsArr[i][6] = bean.getDsfssj();
				smsArr[i][7] = bean.getFssm();
				smsArr[i][8] = bean.getId2();
				smsArr[i][9] = bean.getAh();
			}
			
			SendSMSClient smsClient  = new SendSMSClient();
			sendRet = smsClient.SendMessage(smsArr, null);
		}
		return sendRet;
//		返回数据也为二维数组：
//		Arr[i][0] 业务系统短信息唯一id号
//		Arr[i][1] 发送结果编号（01 发送成功；02 发送人不存在；03 接收人手机号码长度不为11位；04 接收人手机号码没有以指定数字开头 ；05 发送内容超过指定数字（目前限定为600） ；06 定时发送时间格式不准确，格式为2013-11-07 20:02:00；07 必输项填写不完整；08 外省号码；09 其他错误;10 发送内容中有多个'#'，可能是短信模板；11 短信重复，无法发送；12  业务系统短信唯一编号大于45位）
	}
	
	/**
	 * 查询短信状态
	 */
	public String[][] searchSMSZT(String[] smsIds){
		SearchSMSClient smsClient = new SearchSMSClient();
		String[][] sendRet = smsClient.getFszt("023", smsIds);
		return sendRet;
	}
	
	/**
	 * 删除未发送短信
	 * @return
	 */
	public String[][] delSMS(String[] smsIds){
		boolean serviceFlag = false;
		SendSMSClient smsClient  = new SendSMSClient();
		String[][] delRet = smsClient.delSMS("023", smsIds);
		return delRet;
	}
	
}
