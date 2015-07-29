package com.bsoft.sszx.entity.sms;

import java.sql.Timestamp;

/**
 * Sms entity. @author MyEclipse Persistence Tools
 */

public class Sms implements java.io.Serializable {

	// Fields

	private Integer id;
	private String lxdh;
	private String nr;
	private Integer zt;
	private Timestamp sendtime;
	private String fydm;

	private Integer bh;
	private String smsid2;
	private String smszt;
	// Constructors

	/** default constructor */
	public Sms() {
	}

	/** minimal constructor */
	public Sms(String lxdh) {
		this.lxdh = lxdh;
	}

	/** full constructor */
	public Sms(String lxdh, String nr, Integer zt, Timestamp sendtime,String fydm,Integer bh,String smsid2,String smszt) {
		this.lxdh = lxdh;
		this.nr = nr;
		this.zt = zt;
		this.sendtime = sendtime;
		this.fydm = fydm;
		this.bh = bh;
		this.smsid2 = smsid2;
		this.smszt = smszt;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Integer getZt() {
		return this.zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public String getSmsid2() {
		return smsid2;
	}

	public void setSmsid2(String smsid2) {
		this.smsid2 = smsid2;
	}

	public String getSmszt() {
		return smszt;
	}

	public void setSmszt(String smszt) {
		this.smszt = smszt;
	}

}