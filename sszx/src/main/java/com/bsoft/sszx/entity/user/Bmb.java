package com.bsoft.sszx.entity.user;

/**
 * Bmb entity. @author MyEclipse Persistence Tools
 */

//部门表
public class Bmb implements java.io.Serializable {

	// Fields

	private BmbId id;
	private String bmmc;

	// Constructors

	/** default constructor */
	public Bmb() {
	}

	/** minimal constructor */
	public Bmb(BmbId id) {
		this.id = id;
	}

	/** full constructor */
	public Bmb(BmbId id, String bmmc) {
		this.id = id;
		this.bmmc = bmmc;
	}

	// Property accessors

	public BmbId getId() {
		return this.id;
	}

	public void setId(BmbId id) {
		this.id = id;
	}

	public String getBmmc() {
		return this.bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

}