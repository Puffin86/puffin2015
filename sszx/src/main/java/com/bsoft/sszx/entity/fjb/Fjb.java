package com.bsoft.sszx.entity.fjb;

/**
 * Fjb entity. @author MyEclipse Persistence Tools
 */
/**
 * 附件表
 */
public class Fjb implements java.io.Serializable {

	// Fields

	private FjbId id;
	private String fjmc;
	private String fjdz;

	// Constructors

	/** default constructor */
	public Fjb() {
	}

	/** minimal constructor */
	public Fjb(FjbId id) {
		this.id = id;
	}

	/** full constructor */
	public Fjb(FjbId id, String fjmc, String fjdz) {
		this.id = id;
		this.fjmc = fjmc;
		this.fjdz = fjdz;
	}

	// Property accessors

	public FjbId getId() {
		return this.id;
	}

	public void setId(FjbId id) {
		this.id = id;
	}

	public String getFjmc() {
		return this.fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getFjdz() {
		return this.fjdz;
	}

	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}

}