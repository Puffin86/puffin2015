package com.bsoft.sszx.entity.clb;

import java.sql.Timestamp;

/**
 * Clb entity. @author MyEclipse Persistence Tools
 */

/**
 * 材料表
 */
public class Clb implements java.io.Serializable {

	// Fields

	private ClbId id;
	private String clmc;
	private Integer fs;
	private Integer ys;
	private Timestamp sendtime;

	// Constructors

	/** default constructor */
	public Clb() {
	}

	/** minimal constructor */
	public Clb(ClbId id) {
		this.id = id;
	}

	/** full constructor */
	public Clb(ClbId id, String clmc, Integer fs, Integer ys, Timestamp sendtime) {
		this.id = id;
		this.clmc = clmc;
		this.fs = fs;
		this.ys = ys;
		this.sendtime = sendtime;
	}

	// Property accessors

	public ClbId getId() {
		return this.id;
	}

	public void setId(ClbId id) {
		this.id = id;
	}

	public String getClmc() {
		return this.clmc;
	}

	public void setClmc(String clmc) {
		this.clmc = clmc;
	}

	public Integer getFs() {
		return this.fs;
	}

	public void setFs(Integer fs) {
		this.fs = fs;
	}

	public Integer getYs() {
		return this.ys;
	}

	public void setYs(Integer ys) {
		this.ys = ys;
	}

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

}