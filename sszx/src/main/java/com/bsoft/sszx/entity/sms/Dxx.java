package com.bsoft.sszx.entity.sms;

/**
 * Dxx entity. @author MyEclipse Persistence Tools
 */

public class Dxx implements java.io.Serializable {

	// Fields

	private DxxId id;
	private String zt;
	private String nr;
	private Integer fszd;
	private String mc;
	private Integer zdfs;

	// Constructors

	/** default constructor */
	public Dxx() {
	}

	/** minimal constructor */
	public Dxx(DxxId id) {
		this.id = id;
	}

	/** full constructor */
	public Dxx(DxxId id, String zt, String nr, Integer fszd, String mc,
			Integer zdfs) {
		this.id = id;
		this.zt = zt;
		this.nr = nr;
		this.fszd = fszd;
		this.mc = mc;
		this.zdfs = zdfs;
	}

	// Property accessors

	public DxxId getId() {
		return this.id;
	}

	public void setId(DxxId id) {
		this.id = id;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Integer getFszd() {
		return this.fszd;
	}

	public void setFszd(Integer fszd) {
		this.fszd = fszd;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Integer getZdfs() {
		return this.zdfs;
	}

	public void setZdfs(Integer zdfs) {
		this.zdfs = zdfs;
	}

}