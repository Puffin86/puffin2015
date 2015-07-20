package com.bsoft.sszx.entity.sms;

public class SmsBean {
	
//	private String xtbh="023";//系统编号 （必输项）
//	private String fsr_fybm = "1303";//发送人法院编码（必输项）
//	private String fsr_userid="1303-zuh";//发送人Userid（必输项，格式为1300-linjd,为从OA系统获取过来的值）
//	private String jssjhm="13655710068";//接收人手机号码（必输项）
//	private String jsrmc="AAA";//接收人名称（必输项）
//	private String fsnr="nrnrnrnr";//发送内容（必输项）
//	private String dsfssj=null;//定时发送时间（传大于当前的时间，需立即发送时传空即可），格式为2013-11-07 20:02:00
//	private String fssm=null;//发送说明（可传空）
//	private String id2="1234";//业务系统短信息唯一ID号(建议设为Ip+含有时分秒的时间+批量发送时的递增号，例如：2031331302242013102907283979239；必输项；最长不能超过45位)
//	private String ah="";//案号（审判、执行等系统需传，没有涉及到“案号”的系统在传参时直接传空即可）
	
	private String xtbh="023";//系统编号 （必输项）
	private String fsr_fybm = "1303";//发送人法院编码（必输项）
	private String fsr_userid="";//发送人Userid（必输项，格式为1300-linjd,为从OA系统获取过来的值）
	private String jssjhm="";//接收人手机号码（必输项）
	private String jsrmc="";//接收人名称（必输项）
	private String fsnr="";//发送内容（必输项）
	private String id2="";//业务系统短信息唯一ID号(建议设为Ip+含有时分秒的时间+批量发送时的递增号，例如：2031331302242013102907283979239；必输项；最长不能超过45位)
	
	private String dsfssj=null;//定时发送时间（传大于当前的时间，需立即发送时传空即可），格式为2013-11-07 20:02:00
	private String fssm=null;//发送说明（可传空）
	private String ah=null;//案号（审判、执行等系统需传，没有涉及到“案号”的系统在传参时直接传空即可）
	public String getXtbh() {
		return xtbh;
	}
	public void setXtbh(String xtbh) {
		this.xtbh = xtbh;
	}
	public String getFsr_fybm() {
		return fsr_fybm;
	}
	public void setFsr_fybm(String fsr_fybm) {
		this.fsr_fybm = fsr_fybm;
	}
	public String getFsr_userid() {
		return fsr_userid;
	}
	public void setFsr_userid(String fsr_userid) {
		this.fsr_userid = fsr_userid;
	}
	public String getJssjhm() {
		return jssjhm;
	}
	public void setJssjhm(String jssjhm) {
		this.jssjhm = jssjhm;
	}
	public String getJsrmc() {
		return jsrmc;
	}
	public void setJsrmc(String jsrmc) {
		this.jsrmc = jsrmc;
	}
	public String getFsnr() {
		return fsnr;
	}
	public void setFsnr(String fsnr) {
		this.fsnr = fsnr;
	}
	public String getDsfssj() {
		return dsfssj;
	}
	public void setDsfssj(String dsfssj) {
		this.dsfssj = dsfssj;
	}
	public String getFssm() {
		return fssm;
	}
	public void setFssm(String fssm) {
		this.fssm = fssm;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	
	
	
}
