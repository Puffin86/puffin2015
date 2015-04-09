package com.bsoft.sszx.entity.zd;

import java.sql.Timestamp;

/**
 * Sms entity. @author MyEclipse Persistence Tools
 */

public class ZdMx implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zdmxbm;
	private String zdmxmc;
	private String parent;
	private String zdbm;

	// Constructors

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	/** default constructor */
	public ZdMx() {
	}

	/** minimal constructor */
	public ZdMx(String zdmxbm) {
		this.zdmxbm = zdmxbm;
	}

	/** full constructor */
	public ZdMx(String zdmxbm,String zdmxmc,String parent,String zdbm) {
		this.zdmxbm = zdmxbm;
		this.zdmxmc = zdmxmc;
		this.parent = parent;
		this.zdbm = zdbm;
	}

	public String getZdmxbm() {
		return zdmxbm;
	}

	public void setZdmxbm(String zdmxbm) {
		this.zdmxbm = zdmxbm;
	}

	public String getZdmxmc() {
		return zdmxmc;
	}

	public void setZdmxmc(String zdmxmc) {
		this.zdmxmc = zdmxmc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}


}