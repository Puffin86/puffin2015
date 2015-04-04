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

	// Constructors

	/** default constructor */
	public Sms() {
	}

	/** minimal constructor */
	public Sms(String lxdh) {
		this.lxdh = lxdh;
	}

	/** full constructor */
	public Sms(String lxdh, String nr, Integer zt, Timestamp sendtime,
			String fydm) {
		this.lxdh = lxdh;
		this.nr = nr;
		this.zt = zt;
		this.sendtime = sendtime;
		this.fydm = fydm;
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

}