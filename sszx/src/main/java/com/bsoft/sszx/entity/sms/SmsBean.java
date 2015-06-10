package com.bsoft.sszx.entity.sms;

public class SmsBean {
	private String xtbh="";//系统编号
	private String fsr_fybm = "";//法院编码
	private String fsr_userid="";//发送人ID 1300-linjd OA中获取
	private String jssjhm="";//接收手机号码；
	private String jsrmc="";//接收人名称
	private String fsnr="";//发送内容
	private Object dsfssj=null;//格式2013-11-07 20：02：02 null马上发送
	private Object fssm=null;//发送说明 可NULL
	private String id2="";//
	private String ah="";//案号
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
	public Object getDsfssj() {
		return dsfssj;
	}
	public void setDsfssj(Object dsfssj) {
		this.dsfssj = dsfssj;
	}
	public Object getFssm() {
		return fssm;
	}
	public void setFssm(Object fssm) {
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
