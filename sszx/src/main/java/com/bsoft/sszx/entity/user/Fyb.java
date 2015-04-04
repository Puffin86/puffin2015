package com.bsoft.sszx.entity.user;

/**
 * Fyb entity. @author MyEclipse Persistence Tools
 */
//法院表
public class Fyb implements java.io.Serializable {

	// Fields

	private String fydm;
	private String fymc;

	// Constructors

	/** default constructor */
	public Fyb() {
	}

	/** minimal constructor */
	public Fyb(String fydm) {
		this.fydm = fydm;
	}

	/** full constructor */
	public Fyb(String fydm, String fymc) {
		this.fydm = fydm;
		this.fymc = fymc;
	}

	// Property accessors

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public String getFymc() {
		return this.fymc;
	}

	public void setFymc(String fymc) {
		this.fymc = fymc;
	}

}