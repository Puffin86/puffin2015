package com.bsoft.sszx.entity.zjqd;

import java.sql.Timestamp;

/**
 * Zjqd entity. @author MyEclipse Persistence Tools
 */

public class Zjqd implements java.io.Serializable {

	// Fields

	private ZjqdId id;
	private String ah;
	private String sjr;
	private String djr;
	private String djrq;
	private String djrlxdh;
	private String zjr;
	private String zjrq;
	private String djrbm;
	private String sjrbm;
	private Timestamp sendtime;
	private Integer zt;
	private String htyj;
	private String sjrlxdh;
	private String lclx;
	private String dqcyr;
	private String qscyr;
	private String hscyr;
	private String dsrsfzhm;
	private String lzjl;
	private String sjrXm;
	private String sjrBmmc;

	// Constructors

	/** default constructor */
	public Zjqd() {
	}

	/** minimal constructor */
	public Zjqd(ZjqdId id, String lclx) {
		this.id = id;
		this.lclx = lclx;
	}

	/** full constructor */
	public Zjqd(ZjqdId id, String ah, String sjr, String djr, String djrq,
			String djrlxdh, String zjr, String zjrq, String djrbm,
			String sjrbm, Timestamp sendtime, Integer zt, String htyj,
			String sjrlxdh, String lclx, String dqcyr, String qscyr,
			String hscyr, String dsrsfzhm, String lzjl, String sjrXm,
			String sjrBmmc) {
		this.id = id;
		this.ah = ah;
		this.sjr = sjr;
		this.djr = djr;
		this.djrq = djrq;
		this.djrlxdh = djrlxdh;
		this.zjr = zjr;
		this.zjrq = zjrq;
		this.djrbm = djrbm;
		this.sjrbm = sjrbm;
		this.sendtime = sendtime;
		this.zt = zt;
		this.htyj = htyj;
		this.sjrlxdh = sjrlxdh;
		this.lclx = lclx;
		this.dqcyr = dqcyr;
		this.qscyr = qscyr;
		this.hscyr = hscyr;
		this.dsrsfzhm = dsrsfzhm;
		this.lzjl = lzjl;
		this.sjrXm = sjrXm;
		this.sjrBmmc = sjrBmmc;
	}

	// Property accessors

	public ZjqdId getId() {
		return this.id;
	}

	public void setId(ZjqdId id) {
		this.id = id;
	}

	public String getAh() {
		return this.ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getSjr() {
		return this.sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}

	public String getDjr() {
		return this.djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getDjrq() {
		return this.djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getDjrlxdh() {
		return this.djrlxdh;
	}

	public void setDjrlxdh(String djrlxdh) {
		this.djrlxdh = djrlxdh;
	}

	public String getZjr() {
		return this.zjr;
	}

	public void setZjr(String zjr) {
		this.zjr = zjr;
	}

	public String getZjrq() {
		return this.zjrq;
	}

	public void setZjrq(String zjrq) {
		this.zjrq = zjrq;
	}

	public String getDjrbm() {
		return this.djrbm;
	}

	public void setDjrbm(String djrbm) {
		this.djrbm = djrbm;
	}

	public String getSjrbm() {
		return this.sjrbm;
	}

	public void setSjrbm(String sjrbm) {
		this.sjrbm = sjrbm;
	}

	public Timestamp getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getZt() {
		return this.zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public String getHtyj() {
		return this.htyj;
	}

	public void setHtyj(String htyj) {
		this.htyj = htyj;
	}

	public String getSjrlxdh() {
		return this.sjrlxdh;
	}

	public void setSjrlxdh(String sjrlxdh) {
		this.sjrlxdh = sjrlxdh;
	}

	public String getLclx() {
		return this.lclx;
	}

	public void setLclx(String lclx) {
		this.lclx = lclx;
	}

	public String getDqcyr() {
		return this.dqcyr;
	}

	public void setDqcyr(String dqcyr) {
		this.dqcyr = dqcyr;
	}

	public String getQscyr() {
		return this.qscyr;
	}

	public void setQscyr(String qscyr) {
		this.qscyr = qscyr;
	}

	public String getHscyr() {
		return this.hscyr;
	}

	public void setHscyr(String hscyr) {
		this.hscyr = hscyr;
	}

	public String getDsrsfzhm() {
		return this.dsrsfzhm;
	}

	public void setDsrsfzhm(String dsrsfzhm) {
		this.dsrsfzhm = dsrsfzhm;
	}

	public String getLzjl() {
		return this.lzjl;
	}

	public void setLzjl(String lzjl) {
		this.lzjl = lzjl;
	}

	public String getSjrXm() {
		return this.sjrXm;
	}

	public void setSjrXm(String sjrXm) {
		this.sjrXm = sjrXm;
	}

	public String getSjrBmmc() {
		return this.sjrBmmc;
	}

	public void setSjrBmmc(String sjrBmmc) {
		this.sjrBmmc = sjrBmmc;
	}

}