package com.bsoft.sszx.entity.eaj;

import java.sql.Timestamp;

/**
 * Eaj entity. @author MyEclipse Persistence Tools
 */

public class Eaj4ZX implements java.io.Serializable {

	// Fields

	private String ahdm;
	private String ah;
	private Timestamp larq;
	private String dsrmc;
	private String ssdw;
	private String lxdh;
	
	
	/** default constructor */
	public Eaj4ZX() {
	}

	/** minimal constructor */
	public Eaj4ZX(String ahdm) {
		this.ahdm = ahdm;
	}
	
	/** full constructor */
	public Eaj4ZX(String ahdm,String ah,Timestamp larq,String dsrmc,String ssdw,String lxdh){
		this.ahdm = ahdm;
		this.ah = ah;
		this.larq = larq;
		this.dsrmc = dsrmc;
		this.ssdw = ssdw;
		this.lxdh = lxdh;
	}
	
	public String getAhdm() {
		return ahdm;
	}
	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	public Timestamp getLarq() {
		return larq;
	}
	public void setLarq(Timestamp larq) {
		this.larq = larq;
	}
	public String getDsrmc() {
		return dsrmc;
	}
	public void setDsrmc(String dsrmc) {
		this.dsrmc = dsrmc;
	}
	public String getSsdw() {
		return ssdw;
	}
	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

}