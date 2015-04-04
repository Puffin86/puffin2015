package com.bsoft.sszx.entity.eaj;

import java.sql.Timestamp;

/**
 * Eaj entity. @author MyEclipse Persistence Tools
 */

public class Eaj implements java.io.Serializable {

	// Fields

	private String ahdm;
	private String ah;
	private String lsah;
	private String ajsd;
	private String mqzz;
	private Short pqcs;
	private String ajlx;
	private Integer xla;
	private String ajmj;
	private String ajbs;
	private String fydm;
	private String nd;
	private String fyjc;
	private String dz;
	private Integer xh;
	private Integer ndxh;
	private String sarq;
	private String larq;
	private String jarq;
	private String sdrq;
	private String sxrq;
	private String tjgdrq;
	private String gdjsrq;
	private String laqxjmrq;
	private String sxqsrq;
	private String sxjmrq;
	private String ktrq;
	private String satj;
	private String jatj;
	private String sxtj;
	private String tjbh;
	private String ajly;
	private String sycx;
	private Integer saay;
	private String ayms;
	private Double bdje;
	private String yssx;
	private String jafs;
	private String jafssm;
	private String dsr;
	private String labm;
	private String sar;
	private String cbbm1;
	private String cbbm2;
	private String cbr;
	private String spz;
	private String sjy;
	private String hycy;
	private Timestamp lastupdate;
	private String scr;
	private String ztc;
	private String ajlb;
	private String xtajlx;
	private String spcx;
	private String fzcja;
	private String jzxh;
	private String pczt;
	private String ssfw;
	private String ajzt;
	private Integer ajdj;
	private String ajdjyj;
	private String dqfxdj;
	private String ajxz;
	private String wlcbs;
	private String wslabs;
	private String fgzl;
	private String ldzcbs;
	private String pbjl;
	private String fjm;
	private String zt;
	private String bcysftj;
	private String dqyqfxdj;
	private String sxzt;
	private String ajmc;
	private String lydq;
	private String mswfsdcx;
	private String ahcfxh;
	private String mswfsdcxlx;

	// Constructors

	/** default constructor */
	public Eaj() {
	}

	/** minimal constructor */
	public Eaj(String ahdm, String jzxh) {
		this.ahdm = ahdm;
		this.jzxh = jzxh;
	}

	/** full constructor */
	public Eaj(String ahdm, String ah, String lsah, String ajsd, String mqzz,
			Short pqcs, String ajlx, Integer xla, String ajmj, String ajbs,
			String fydm, String nd, String fyjc, String dz, Integer xh,
			Integer ndxh, String sarq, String larq, String jarq, String sdrq,
			String sxrq, String tjgdrq, String gdjsrq, String laqxjmrq,
			String sxqsrq, String sxjmrq, String ktrq, String satj,
			String jatj, String sxtj, String tjbh, String ajly, String sycx,
			Integer saay, String ayms, Double bdje, String yssx, String jafs,
			String jafssm, String dsr, String labm, String sar, String cbbm1,
			String cbbm2, String cbr, String spz, String sjy, String hycy,
			Timestamp lastupdate, String scr, String ztc, String ajlb,
			String xtajlx, String spcx, String fzcja, String jzxh, String pczt,
			String ssfw, String ajzt, Integer ajdj, String ajdjyj,
			String dqfxdj, String ajxz, String wlcbs, String wslabs,
			String fgzl, String ldzcbs, String pbjl, String fjm, String zt,
			String bcysftj, String dqyqfxdj, String sxzt, String ajmc,
			String lydq, String mswfsdcx, String ahcfxh, String mswfsdcxlx) {
		this.ahdm = ahdm;
		this.ah = ah;
		this.lsah = lsah;
		this.ajsd = ajsd;
		this.mqzz = mqzz;
		this.pqcs = pqcs;
		this.ajlx = ajlx;
		this.xla = xla;
		this.ajmj = ajmj;
		this.ajbs = ajbs;
		this.fydm = fydm;
		this.nd = nd;
		this.fyjc = fyjc;
		this.dz = dz;
		this.xh = xh;
		this.ndxh = ndxh;
		this.sarq = sarq;
		this.larq = larq;
		this.jarq = jarq;
		this.sdrq = sdrq;
		this.sxrq = sxrq;
		this.tjgdrq = tjgdrq;
		this.gdjsrq = gdjsrq;
		this.laqxjmrq = laqxjmrq;
		this.sxqsrq = sxqsrq;
		this.sxjmrq = sxjmrq;
		this.ktrq = ktrq;
		this.satj = satj;
		this.jatj = jatj;
		this.sxtj = sxtj;
		this.tjbh = tjbh;
		this.ajly = ajly;
		this.sycx = sycx;
		this.saay = saay;
		this.ayms = ayms;
		this.bdje = bdje;
		this.yssx = yssx;
		this.jafs = jafs;
		this.jafssm = jafssm;
		this.dsr = dsr;
		this.labm = labm;
		this.sar = sar;
		this.cbbm1 = cbbm1;
		this.cbbm2 = cbbm2;
		this.cbr = cbr;
		this.spz = spz;
		this.sjy = sjy;
		this.hycy = hycy;
		this.lastupdate = lastupdate;
		this.scr = scr;
		this.ztc = ztc;
		this.ajlb = ajlb;
		this.xtajlx = xtajlx;
		this.spcx = spcx;
		this.fzcja = fzcja;
		this.jzxh = jzxh;
		this.pczt = pczt;
		this.ssfw = ssfw;
		this.ajzt = ajzt;
		this.ajdj = ajdj;
		this.ajdjyj = ajdjyj;
		this.dqfxdj = dqfxdj;
		this.ajxz = ajxz;
		this.wlcbs = wlcbs;
		this.wslabs = wslabs;
		this.fgzl = fgzl;
		this.ldzcbs = ldzcbs;
		this.pbjl = pbjl;
		this.fjm = fjm;
		this.zt = zt;
		this.bcysftj = bcysftj;
		this.dqyqfxdj = dqyqfxdj;
		this.sxzt = sxzt;
		this.ajmc = ajmc;
		this.lydq = lydq;
		this.mswfsdcx = mswfsdcx;
		this.ahcfxh = ahcfxh;
		this.mswfsdcxlx = mswfsdcxlx;
	}

	// Property accessors

	public String getAhdm() {
		return this.ahdm;
	}

	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}

	public String getAh() {
		return this.ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getLsah() {
		return this.lsah;
	}

	public void setLsah(String lsah) {
		this.lsah = lsah;
	}

	public String getAjsd() {
		return this.ajsd;
	}

	public void setAjsd(String ajsd) {
		this.ajsd = ajsd;
	}

	public String getMqzz() {
		return this.mqzz;
	}

	public void setMqzz(String mqzz) {
		this.mqzz = mqzz;
	}

	public Short getPqcs() {
		return this.pqcs;
	}

	public void setPqcs(Short pqcs) {
		this.pqcs = pqcs;
	}

	public String getAjlx() {
		return this.ajlx;
	}

	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}

	public Integer getXla() {
		return this.xla;
	}

	public void setXla(Integer xla) {
		this.xla = xla;
	}

	public String getAjmj() {
		return this.ajmj;
	}

	public void setAjmj(String ajmj) {
		this.ajmj = ajmj;
	}

	public String getAjbs() {
		return this.ajbs;
	}

	public void setAjbs(String ajbs) {
		this.ajbs = ajbs;
	}

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public String getNd() {
		return this.nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getFyjc() {
		return this.fyjc;
	}

	public void setFyjc(String fyjc) {
		this.fyjc = fyjc;
	}

	public String getDz() {
		return this.dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getNdxh() {
		return this.ndxh;
	}

	public void setNdxh(Integer ndxh) {
		this.ndxh = ndxh;
	}

	public String getSarq() {
		return this.sarq;
	}

	public void setSarq(String sarq) {
		this.sarq = sarq;
	}

	public String getLarq() {
		return this.larq;
	}

	public void setLarq(String larq) {
		this.larq = larq;
	}

	public String getJarq() {
		return this.jarq;
	}

	public void setJarq(String jarq) {
		this.jarq = jarq;
	}

	public String getSdrq() {
		return this.sdrq;
	}

	public void setSdrq(String sdrq) {
		this.sdrq = sdrq;
	}

	public String getSxrq() {
		return this.sxrq;
	}

	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}

	public String getTjgdrq() {
		return this.tjgdrq;
	}

	public void setTjgdrq(String tjgdrq) {
		this.tjgdrq = tjgdrq;
	}

	public String getGdjsrq() {
		return this.gdjsrq;
	}

	public void setGdjsrq(String gdjsrq) {
		this.gdjsrq = gdjsrq;
	}

	public String getLaqxjmrq() {
		return this.laqxjmrq;
	}

	public void setLaqxjmrq(String laqxjmrq) {
		this.laqxjmrq = laqxjmrq;
	}

	public String getSxqsrq() {
		return this.sxqsrq;
	}

	public void setSxqsrq(String sxqsrq) {
		this.sxqsrq = sxqsrq;
	}

	public String getSxjmrq() {
		return this.sxjmrq;
	}

	public void setSxjmrq(String sxjmrq) {
		this.sxjmrq = sxjmrq;
	}

	public String getKtrq() {
		return this.ktrq;
	}

	public void setKtrq(String ktrq) {
		this.ktrq = ktrq;
	}

	public String getSatj() {
		return this.satj;
	}

	public void setSatj(String satj) {
		this.satj = satj;
	}

	public String getJatj() {
		return this.jatj;
	}

	public void setJatj(String jatj) {
		this.jatj = jatj;
	}

	public String getSxtj() {
		return this.sxtj;
	}

	public void setSxtj(String sxtj) {
		this.sxtj = sxtj;
	}

	public String getTjbh() {
		return this.tjbh;
	}

	public void setTjbh(String tjbh) {
		this.tjbh = tjbh;
	}

	public String getAjly() {
		return this.ajly;
	}

	public void setAjly(String ajly) {
		this.ajly = ajly;
	}

	public String getSycx() {
		return this.sycx;
	}

	public void setSycx(String sycx) {
		this.sycx = sycx;
	}

	public Integer getSaay() {
		return this.saay;
	}

	public void setSaay(Integer saay) {
		this.saay = saay;
	}

	public String getAyms() {
		return this.ayms;
	}

	public void setAyms(String ayms) {
		this.ayms = ayms;
	}

	public Double getBdje() {
		return this.bdje;
	}

	public void setBdje(Double bdje) {
		this.bdje = bdje;
	}

	public String getYssx() {
		return this.yssx;
	}

	public void setYssx(String yssx) {
		this.yssx = yssx;
	}

	public String getJafs() {
		return this.jafs;
	}

	public void setJafs(String jafs) {
		this.jafs = jafs;
	}

	public String getJafssm() {
		return this.jafssm;
	}

	public void setJafssm(String jafssm) {
		this.jafssm = jafssm;
	}

	public String getDsr() {
		return this.dsr;
	}

	public void setDsr(String dsr) {
		this.dsr = dsr;
	}

	public String getLabm() {
		return this.labm;
	}

	public void setLabm(String labm) {
		this.labm = labm;
	}

	public String getSar() {
		return this.sar;
	}

	public void setSar(String sar) {
		this.sar = sar;
	}

	public String getCbbm1() {
		return this.cbbm1;
	}

	public void setCbbm1(String cbbm1) {
		this.cbbm1 = cbbm1;
	}

	public String getCbbm2() {
		return this.cbbm2;
	}

	public void setCbbm2(String cbbm2) {
		this.cbbm2 = cbbm2;
	}

	public String getCbr() {
		return this.cbr;
	}

	public void setCbr(String cbr) {
		this.cbr = cbr;
	}

	public String getSpz() {
		return this.spz;
	}

	public void setSpz(String spz) {
		this.spz = spz;
	}

	public String getSjy() {
		return this.sjy;
	}

	public void setSjy(String sjy) {
		this.sjy = sjy;
	}

	public String getHycy() {
		return this.hycy;
	}

	public void setHycy(String hycy) {
		this.hycy = hycy;
	}

	public Timestamp getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getScr() {
		return this.scr;
	}

	public void setScr(String scr) {
		this.scr = scr;
	}

	public String getZtc() {
		return this.ztc;
	}

	public void setZtc(String ztc) {
		this.ztc = ztc;
	}

	public String getAjlb() {
		return this.ajlb;
	}

	public void setAjlb(String ajlb) {
		this.ajlb = ajlb;
	}

	public String getXtajlx() {
		return this.xtajlx;
	}

	public void setXtajlx(String xtajlx) {
		this.xtajlx = xtajlx;
	}

	public String getSpcx() {
		return this.spcx;
	}

	public void setSpcx(String spcx) {
		this.spcx = spcx;
	}

	public String getFzcja() {
		return this.fzcja;
	}

	public void setFzcja(String fzcja) {
		this.fzcja = fzcja;
	}

	public String getJzxh() {
		return this.jzxh;
	}

	public void setJzxh(String jzxh) {
		this.jzxh = jzxh;
	}

	public String getPczt() {
		return this.pczt;
	}

	public void setPczt(String pczt) {
		this.pczt = pczt;
	}

	public String getSsfw() {
		return this.ssfw;
	}

	public void setSsfw(String ssfw) {
		this.ssfw = ssfw;
	}

	public String getAjzt() {
		return this.ajzt;
	}

	public void setAjzt(String ajzt) {
		this.ajzt = ajzt;
	}

	public Integer getAjdj() {
		return this.ajdj;
	}

	public void setAjdj(Integer ajdj) {
		this.ajdj = ajdj;
	}

	public String getAjdjyj() {
		return this.ajdjyj;
	}

	public void setAjdjyj(String ajdjyj) {
		this.ajdjyj = ajdjyj;
	}

	public String getDqfxdj() {
		return this.dqfxdj;
	}

	public void setDqfxdj(String dqfxdj) {
		this.dqfxdj = dqfxdj;
	}

	public String getAjxz() {
		return this.ajxz;
	}

	public void setAjxz(String ajxz) {
		this.ajxz = ajxz;
	}

	public String getWlcbs() {
		return this.wlcbs;
	}

	public void setWlcbs(String wlcbs) {
		this.wlcbs = wlcbs;
	}

	public String getWslabs() {
		return this.wslabs;
	}

	public void setWslabs(String wslabs) {
		this.wslabs = wslabs;
	}

	public String getFgzl() {
		return this.fgzl;
	}

	public void setFgzl(String fgzl) {
		this.fgzl = fgzl;
	}

	public String getLdzcbs() {
		return this.ldzcbs;
	}

	public void setLdzcbs(String ldzcbs) {
		this.ldzcbs = ldzcbs;
	}

	public String getPbjl() {
		return this.pbjl;
	}

	public void setPbjl(String pbjl) {
		this.pbjl = pbjl;
	}

	public String getFjm() {
		return this.fjm;
	}

	public void setFjm(String fjm) {
		this.fjm = fjm;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBcysftj() {
		return this.bcysftj;
	}

	public void setBcysftj(String bcysftj) {
		this.bcysftj = bcysftj;
	}

	public String getDqyqfxdj() {
		return this.dqyqfxdj;
	}

	public void setDqyqfxdj(String dqyqfxdj) {
		this.dqyqfxdj = dqyqfxdj;
	}

	public String getSxzt() {
		return this.sxzt;
	}

	public void setSxzt(String sxzt) {
		this.sxzt = sxzt;
	}

	public String getAjmc() {
		return this.ajmc;
	}

	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}

	public String getLydq() {
		return this.lydq;
	}

	public void setLydq(String lydq) {
		this.lydq = lydq;
	}

	public String getMswfsdcx() {
		return this.mswfsdcx;
	}

	public void setMswfsdcx(String mswfsdcx) {
		this.mswfsdcx = mswfsdcx;
	}

	public String getAhcfxh() {
		return this.ahcfxh;
	}

	public void setAhcfxh(String ahcfxh) {
		this.ahcfxh = ahcfxh;
	}

	public String getMswfsdcxlx() {
		return this.mswfsdcxlx;
	}

	public void setMswfsdcxlx(String mswfsdcxlx) {
		this.mswfsdcxlx = mswfsdcxlx;
	}

}