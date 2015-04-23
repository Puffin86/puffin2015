package com.bsoft.sszx.entity.user;

public class BmBean {
	private String bmdm;
	private String dwdm;
	private String bmmc;
	
	/** default constructor */
	public BmBean() {
	}

	/** minimal constructor */
	public BmBean(String bmdm) {
		this.bmdm = bmdm;
	}

	/** full constructor */
	public BmBean(String bmdm,String dwdm,String bmmc) {
		this.bmdm = bmdm;
		this.dwdm = dwdm;
		this.bmmc = bmmc;
	}
	
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
}
