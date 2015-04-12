package com.bsoft.sszx.entity.zd;

import java.sql.Timestamp;

/**
 * Sms entity. @author MyEclipse Persistence Tools
 */

public class Zd implements java.io.Serializable {

	// Fields

	private String zdbm;
	private String zdmc;

	// Constructors

	/** default constructor */
	public Zd() {
	}

	/** minimal constructor */
	public Zd(String zdbm) {
		this.zdbm = zdbm;
	}

	/** full constructor */
	public Zd(String zdbm,String zdmc) {
		this.zdbm = zdbm;
		this.zdmc = zdmc;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

}