package com.bsoft.sszx.entity.eaj;

import java.sql.Timestamp;

/**
 * Eaj4ZX entity. @author MyEclipse Persistence Tools
 */

public class Eaj4ZX implements java.io.Serializable {

	// Fields

	private String ajbs;
	private String ajnh;
	private String cbr;
	private String ah;
	private Timestamp larq;
	private String mc;
	private String ssdw;
	private String dh;
	private String dz;
	
	
//	/** default constructor */
	public Eaj4ZX() {
	}
//
//	/** minimal constructor */
//	public Eaj4ZX(String ahbs) {
//		this.ajbs = ajbs;
//	}
	
	/** full constructor */
	public Eaj4ZX(String ajbs,String ajnh,String cbr,
			String ah,Timestamp larq,String mc,String ssdw,String dh,String dz){
		this.ajbs = ajbs;
		this.ah = ah;
		this.larq = larq;
		this.ajnh = ajnh;
		this.ssdw = ssdw;
		this.cbr = cbr;
		this.mc = mc;
		this.dh = dh;
		this.dz = dz;
	}

	public String getAjbs() {
		return ajbs;
	}

	public void setAjbs(String ajbs) {
		this.ajbs = ajbs;
	}

	public String getAjnh() {
		return ajnh;
	}

	public void setAjnh(String ajnh) {
		this.ajnh = ajnh;
	}

	public String getCbr() {
		return cbr;
	}

	public void setCbr(String cbr) {
		this.cbr = cbr;
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

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSsdw() {
		return ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}


}