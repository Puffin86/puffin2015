package com.bsoft.sszx.entity.edsr;

import java.sql.Timestamp;

/**
 * Edsr entity. @author MyEclipse Persistence Tools
 */
/**
 * 当事人
 */
public class Edsr implements java.io.Serializable {

	// Fields

	private EdsrId id;
	private String lb;
	private String cxzh;
	private String cxmm;
	private String ssdw1;
	private String ssdw2;
	private String ssdw3;
	private String ly;
	private String bs;
	private String js;
	private String lx;
	private String mc;
	private String dz;
	private String yzbm;
	private String lxdh;
	private String dzyx;
	private String qtlxff;
	private String xwnl;
	private String ssdx;
	private String xbhr;
	private String swqk;
	private Double wzssje;
	private Double sqpcje;
	private String dwjssfjzyy;
	private String bgrlx;
	private Short bgrpxh;
	private String dwfzzjzrr;
	private String hyhlc;
	private String flyzyy;
	private String hxjskyq;
	private String cxhxjs;
	private String syzxz;
	private String hy;
	private String sbzqrq;
	private Double sbzqje;
	private Double zcze;
	private Double zwze;
	private Double dqzwze;
	private Double qyhdqzwze;
	private String fctzrq;
	private String qstzrq;
	private String sltzr;
	private String sltzrq;
	private String tcyyrq;
	private String tcyyjmrq;
	private String glrlx;
	private String drglrzw;
	private String dsrlx;
	private String yhzhlb;
	private String ygqsly;
	private String bgzyldct;
	private String bgldxtcl;
	private String pcywjglx;
	private String pcjdrq;
	private Double pcjdje;
	private String pcjdws;
	private String xfrsf;
	private String ydsrgx;
	private String xb;
	private String csrq;
	private Short nl;
	private String gj;
	private String mz;
	private String sf;
	private String sfzhm;
	private String xyjrsf;
	private String jgzwxz;
	private String jggbjb;
	private String qtzjzl;
	private String qtzjhm;
	private String zy;
	private String whcd;
	private String hyzk;
	private String zzmm;
	private String xzjb;
	private String szdw;
	private String zw;
	private String tssf;
	private String tsslhbl;
	private String hjszd;
	private String jl;
	private String ldry;
	private String wcnrjtbj;
	private String jtjjzk;
	private String gjdq;
	private String zzjgdm;
	private String dwxz;
	private String tshy;
	private String fddbr;
	private String dbrzjzl;
	private String dbrzjhm;
	private Timestamp lastupdate;
	private String ssdlr;
	private String ssdlrbh;
	private String yuanji;
	private String zibaosf;
	private String swhjl;
	private Integer zhrs;
	private String lxqj;
	private String zmms;
	private String laoj;
	private String leif;
	private String wfjs;
	private String wsd;
	private Double fzje;
	private String dsrjc;
	private String bm;
	private Double ffsdcwjz;
	private Double sjpcje;
	private Integer fznl;
	private String xzqh;
	private String cfz;
	private String ctzz;
	private String sddz;
	private String ylf;
	private String ydzdbh;
	private String sjhm;
	private String cym;
	private Double ssbdje;
	private Double ssajslf;
	private String xbgrjqs;
	private String sqmscczzsyqdx;
	private String ssdwms;
	private String sflg;
	private String rzcj;
	private String fzxt;
	private String gtfzdw;
	private String sfdzsd;

	// Constructors

	/** default constructor */
	public Edsr() {
	}

	/** minimal constructor */
	public Edsr(EdsrId id) {
		this.id = id;
	}

	/** full constructor */
	public Edsr(EdsrId id, String lb, String cxzh, String cxmm, String ssdw1,
			String ssdw2, String ssdw3, String ly, String bs, String js,
			String lx, String mc, String dz, String yzbm, String lxdh,
			String dzyx, String qtlxff, String xwnl, String ssdx, String xbhr,
			String swqk, Double wzssje, Double sqpcje, String dwjssfjzyy,
			String bgrlx, Short bgrpxh, String dwfzzjzrr, String hyhlc,
			String flyzyy, String hxjskyq, String cxhxjs, String syzxz,
			String hy, String sbzqrq, Double sbzqje, Double zcze, Double zwze,
			Double dqzwze, Double qyhdqzwze, String fctzrq, String qstzrq,
			String sltzr, String sltzrq, String tcyyrq, String tcyyjmrq,
			String glrlx, String drglrzw, String dsrlx, String yhzhlb,
			String ygqsly, String bgzyldct, String bgldxtcl, String pcywjglx,
			String pcjdrq, Double pcjdje, String pcjdws, String xfrsf,
			String ydsrgx, String xb, String csrq, Short nl, String gj,
			String mz, String sf, String sfzhm, String xyjrsf, String jgzwxz,
			String jggbjb, String qtzjzl, String qtzjhm, String zy,
			String whcd, String hyzk, String zzmm, String xzjb, String szdw,
			String zw, String tssf, String tsslhbl, String hjszd, String jl,
			String ldry, String wcnrjtbj, String jtjjzk, String gjdq,
			String zzjgdm, String dwxz, String tshy, String fddbr,
			String dbrzjzl, String dbrzjhm, Timestamp lastupdate, String ssdlr,
			String ssdlrbh, String yuanji, String zibaosf, String swhjl,
			Integer zhrs, String lxqj, String zmms, String laoj, String leif,
			String wfjs, String wsd, Double fzje, String dsrjc, String bm,
			Double ffsdcwjz, Double sjpcje, Integer fznl, String xzqh,
			String cfz, String ctzz, String sddz, String ylf, String ydzdbh,
			String sjhm, String cym, Double ssbdje, Double ssajslf,
			String xbgrjqs, String sqmscczzsyqdx, String ssdwms, String sflg,
			String rzcj, String fzxt, String gtfzdw, String sfdzsd) {
		this.id = id;
		this.lb = lb;
		this.cxzh = cxzh;
		this.cxmm = cxmm;
		this.ssdw1 = ssdw1;
		this.ssdw2 = ssdw2;
		this.ssdw3 = ssdw3;
		this.ly = ly;
		this.bs = bs;
		this.js = js;
		this.lx = lx;
		this.mc = mc;
		this.dz = dz;
		this.yzbm = yzbm;
		this.lxdh = lxdh;
		this.dzyx = dzyx;
		this.qtlxff = qtlxff;
		this.xwnl = xwnl;
		this.ssdx = ssdx;
		this.xbhr = xbhr;
		this.swqk = swqk;
		this.wzssje = wzssje;
		this.sqpcje = sqpcje;
		this.dwjssfjzyy = dwjssfjzyy;
		this.bgrlx = bgrlx;
		this.bgrpxh = bgrpxh;
		this.dwfzzjzrr = dwfzzjzrr;
		this.hyhlc = hyhlc;
		this.flyzyy = flyzyy;
		this.hxjskyq = hxjskyq;
		this.cxhxjs = cxhxjs;
		this.syzxz = syzxz;
		this.hy = hy;
		this.sbzqrq = sbzqrq;
		this.sbzqje = sbzqje;
		this.zcze = zcze;
		this.zwze = zwze;
		this.dqzwze = dqzwze;
		this.qyhdqzwze = qyhdqzwze;
		this.fctzrq = fctzrq;
		this.qstzrq = qstzrq;
		this.sltzr = sltzr;
		this.sltzrq = sltzrq;
		this.tcyyrq = tcyyrq;
		this.tcyyjmrq = tcyyjmrq;
		this.glrlx = glrlx;
		this.drglrzw = drglrzw;
		this.dsrlx = dsrlx;
		this.yhzhlb = yhzhlb;
		this.ygqsly = ygqsly;
		this.bgzyldct = bgzyldct;
		this.bgldxtcl = bgldxtcl;
		this.pcywjglx = pcywjglx;
		this.pcjdrq = pcjdrq;
		this.pcjdje = pcjdje;
		this.pcjdws = pcjdws;
		this.xfrsf = xfrsf;
		this.ydsrgx = ydsrgx;
		this.xb = xb;
		this.csrq = csrq;
		this.nl = nl;
		this.gj = gj;
		this.mz = mz;
		this.sf = sf;
		this.sfzhm = sfzhm;
		this.xyjrsf = xyjrsf;
		this.jgzwxz = jgzwxz;
		this.jggbjb = jggbjb;
		this.qtzjzl = qtzjzl;
		this.qtzjhm = qtzjhm;
		this.zy = zy;
		this.whcd = whcd;
		this.hyzk = hyzk;
		this.zzmm = zzmm;
		this.xzjb = xzjb;
		this.szdw = szdw;
		this.zw = zw;
		this.tssf = tssf;
		this.tsslhbl = tsslhbl;
		this.hjszd = hjszd;
		this.jl = jl;
		this.ldry = ldry;
		this.wcnrjtbj = wcnrjtbj;
		this.jtjjzk = jtjjzk;
		this.gjdq = gjdq;
		this.zzjgdm = zzjgdm;
		this.dwxz = dwxz;
		this.tshy = tshy;
		this.fddbr = fddbr;
		this.dbrzjzl = dbrzjzl;
		this.dbrzjhm = dbrzjhm;
		this.lastupdate = lastupdate;
		this.ssdlr = ssdlr;
		this.ssdlrbh = ssdlrbh;
		this.yuanji = yuanji;
		this.zibaosf = zibaosf;
		this.swhjl = swhjl;
		this.zhrs = zhrs;
		this.lxqj = lxqj;
		this.zmms = zmms;
		this.laoj = laoj;
		this.leif = leif;
		this.wfjs = wfjs;
		this.wsd = wsd;
		this.fzje = fzje;
		this.dsrjc = dsrjc;
		this.bm = bm;
		this.ffsdcwjz = ffsdcwjz;
		this.sjpcje = sjpcje;
		this.fznl = fznl;
		this.xzqh = xzqh;
		this.cfz = cfz;
		this.ctzz = ctzz;
		this.sddz = sddz;
		this.ylf = ylf;
		this.ydzdbh = ydzdbh;
		this.sjhm = sjhm;
		this.cym = cym;
		this.ssbdje = ssbdje;
		this.ssajslf = ssajslf;
		this.xbgrjqs = xbgrjqs;
		this.sqmscczzsyqdx = sqmscczzsyqdx;
		this.ssdwms = ssdwms;
		this.sflg = sflg;
		this.rzcj = rzcj;
		this.fzxt = fzxt;
		this.gtfzdw = gtfzdw;
		this.sfdzsd = sfdzsd;
	}

	// Property accessors

	public EdsrId getId() {
		return this.id;
	}

	public void setId(EdsrId id) {
		this.id = id;
	}

	public String getLb() {
		return this.lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getCxzh() {
		return this.cxzh;
	}

	public void setCxzh(String cxzh) {
		this.cxzh = cxzh;
	}

	public String getCxmm() {
		return this.cxmm;
	}

	public void setCxmm(String cxmm) {
		this.cxmm = cxmm;
	}

	public String getSsdw1() {
		return this.ssdw1;
	}

	public void setSsdw1(String ssdw1) {
		this.ssdw1 = ssdw1;
	}

	public String getSsdw2() {
		return this.ssdw2;
	}

	public void setSsdw2(String ssdw2) {
		this.ssdw2 = ssdw2;
	}

	public String getSsdw3() {
		return this.ssdw3;
	}

	public void setSsdw3(String ssdw3) {
		this.ssdw3 = ssdw3;
	}

	public String getLy() {
		return this.ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getBs() {
		return this.bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public String getJs() {
		return this.js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getLx() {
		return this.lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getDz() {
		return this.dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getQtlxff() {
		return this.qtlxff;
	}

	public void setQtlxff(String qtlxff) {
		this.qtlxff = qtlxff;
	}

	public String getXwnl() {
		return this.xwnl;
	}

	public void setXwnl(String xwnl) {
		this.xwnl = xwnl;
	}

	public String getSsdx() {
		return this.ssdx;
	}

	public void setSsdx(String ssdx) {
		this.ssdx = ssdx;
	}

	public String getXbhr() {
		return this.xbhr;
	}

	public void setXbhr(String xbhr) {
		this.xbhr = xbhr;
	}

	public String getSwqk() {
		return this.swqk;
	}

	public void setSwqk(String swqk) {
		this.swqk = swqk;
	}

	public Double getWzssje() {
		return this.wzssje;
	}

	public void setWzssje(Double wzssje) {
		this.wzssje = wzssje;
	}

	public Double getSqpcje() {
		return this.sqpcje;
	}

	public void setSqpcje(Double sqpcje) {
		this.sqpcje = sqpcje;
	}

	public String getDwjssfjzyy() {
		return this.dwjssfjzyy;
	}

	public void setDwjssfjzyy(String dwjssfjzyy) {
		this.dwjssfjzyy = dwjssfjzyy;
	}

	public String getBgrlx() {
		return this.bgrlx;
	}

	public void setBgrlx(String bgrlx) {
		this.bgrlx = bgrlx;
	}

	public Short getBgrpxh() {
		return this.bgrpxh;
	}

	public void setBgrpxh(Short bgrpxh) {
		this.bgrpxh = bgrpxh;
	}

	public String getDwfzzjzrr() {
		return this.dwfzzjzrr;
	}

	public void setDwfzzjzrr(String dwfzzjzrr) {
		this.dwfzzjzrr = dwfzzjzrr;
	}

	public String getHyhlc() {
		return this.hyhlc;
	}

	public void setHyhlc(String hyhlc) {
		this.hyhlc = hyhlc;
	}

	public String getFlyzyy() {
		return this.flyzyy;
	}

	public void setFlyzyy(String flyzyy) {
		this.flyzyy = flyzyy;
	}

	public String getHxjskyq() {
		return this.hxjskyq;
	}

	public void setHxjskyq(String hxjskyq) {
		this.hxjskyq = hxjskyq;
	}

	public String getCxhxjs() {
		return this.cxhxjs;
	}

	public void setCxhxjs(String cxhxjs) {
		this.cxhxjs = cxhxjs;
	}

	public String getSyzxz() {
		return this.syzxz;
	}

	public void setSyzxz(String syzxz) {
		this.syzxz = syzxz;
	}

	public String getHy() {
		return this.hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getSbzqrq() {
		return this.sbzqrq;
	}

	public void setSbzqrq(String sbzqrq) {
		this.sbzqrq = sbzqrq;
	}

	public Double getSbzqje() {
		return this.sbzqje;
	}

	public void setSbzqje(Double sbzqje) {
		this.sbzqje = sbzqje;
	}

	public Double getZcze() {
		return this.zcze;
	}

	public void setZcze(Double zcze) {
		this.zcze = zcze;
	}

	public Double getZwze() {
		return this.zwze;
	}

	public void setZwze(Double zwze) {
		this.zwze = zwze;
	}

	public Double getDqzwze() {
		return this.dqzwze;
	}

	public void setDqzwze(Double dqzwze) {
		this.dqzwze = dqzwze;
	}

	public Double getQyhdqzwze() {
		return this.qyhdqzwze;
	}

	public void setQyhdqzwze(Double qyhdqzwze) {
		this.qyhdqzwze = qyhdqzwze;
	}

	public String getFctzrq() {
		return this.fctzrq;
	}

	public void setFctzrq(String fctzrq) {
		this.fctzrq = fctzrq;
	}

	public String getQstzrq() {
		return this.qstzrq;
	}

	public void setQstzrq(String qstzrq) {
		this.qstzrq = qstzrq;
	}

	public String getSltzr() {
		return this.sltzr;
	}

	public void setSltzr(String sltzr) {
		this.sltzr = sltzr;
	}

	public String getSltzrq() {
		return this.sltzrq;
	}

	public void setSltzrq(String sltzrq) {
		this.sltzrq = sltzrq;
	}

	public String getTcyyrq() {
		return this.tcyyrq;
	}

	public void setTcyyrq(String tcyyrq) {
		this.tcyyrq = tcyyrq;
	}

	public String getTcyyjmrq() {
		return this.tcyyjmrq;
	}

	public void setTcyyjmrq(String tcyyjmrq) {
		this.tcyyjmrq = tcyyjmrq;
	}

	public String getGlrlx() {
		return this.glrlx;
	}

	public void setGlrlx(String glrlx) {
		this.glrlx = glrlx;
	}

	public String getDrglrzw() {
		return this.drglrzw;
	}

	public void setDrglrzw(String drglrzw) {
		this.drglrzw = drglrzw;
	}

	public String getDsrlx() {
		return this.dsrlx;
	}

	public void setDsrlx(String dsrlx) {
		this.dsrlx = dsrlx;
	}

	public String getYhzhlb() {
		return this.yhzhlb;
	}

	public void setYhzhlb(String yhzhlb) {
		this.yhzhlb = yhzhlb;
	}

	public String getYgqsly() {
		return this.ygqsly;
	}

	public void setYgqsly(String ygqsly) {
		this.ygqsly = ygqsly;
	}

	public String getBgzyldct() {
		return this.bgzyldct;
	}

	public void setBgzyldct(String bgzyldct) {
		this.bgzyldct = bgzyldct;
	}

	public String getBgldxtcl() {
		return this.bgldxtcl;
	}

	public void setBgldxtcl(String bgldxtcl) {
		this.bgldxtcl = bgldxtcl;
	}

	public String getPcywjglx() {
		return this.pcywjglx;
	}

	public void setPcywjglx(String pcywjglx) {
		this.pcywjglx = pcywjglx;
	}

	public String getPcjdrq() {
		return this.pcjdrq;
	}

	public void setPcjdrq(String pcjdrq) {
		this.pcjdrq = pcjdrq;
	}

	public Double getPcjdje() {
		return this.pcjdje;
	}

	public void setPcjdje(Double pcjdje) {
		this.pcjdje = pcjdje;
	}

	public String getPcjdws() {
		return this.pcjdws;
	}

	public void setPcjdws(String pcjdws) {
		this.pcjdws = pcjdws;
	}

	public String getXfrsf() {
		return this.xfrsf;
	}

	public void setXfrsf(String xfrsf) {
		this.xfrsf = xfrsf;
	}

	public String getYdsrgx() {
		return this.ydsrgx;
	}

	public void setYdsrgx(String ydsrgx) {
		this.ydsrgx = ydsrgx;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsrq() {
		return this.csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public Short getNl() {
		return this.nl;
	}

	public void setNl(Short nl) {
		this.nl = nl;
	}

	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getMz() {
		return this.mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getSf() {
		return this.sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

	public String getSfzhm() {
		return this.sfzhm;
	}

	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getXyjrsf() {
		return this.xyjrsf;
	}

	public void setXyjrsf(String xyjrsf) {
		this.xyjrsf = xyjrsf;
	}

	public String getJgzwxz() {
		return this.jgzwxz;
	}

	public void setJgzwxz(String jgzwxz) {
		this.jgzwxz = jgzwxz;
	}

	public String getJggbjb() {
		return this.jggbjb;
	}

	public void setJggbjb(String jggbjb) {
		this.jggbjb = jggbjb;
	}

	public String getQtzjzl() {
		return this.qtzjzl;
	}

	public void setQtzjzl(String qtzjzl) {
		this.qtzjzl = qtzjzl;
	}

	public String getQtzjhm() {
		return this.qtzjhm;
	}

	public void setQtzjhm(String qtzjhm) {
		this.qtzjhm = qtzjhm;
	}

	public String getZy() {
		return this.zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getWhcd() {
		return this.whcd;
	}

	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}

	public String getHyzk() {
		return this.hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getXzjb() {
		return this.xzjb;
	}

	public void setXzjb(String xzjb) {
		this.xzjb = xzjb;
	}

	public String getSzdw() {
		return this.szdw;
	}

	public void setSzdw(String szdw) {
		this.szdw = szdw;
	}

	public String getZw() {
		return this.zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getTssf() {
		return this.tssf;
	}

	public void setTssf(String tssf) {
		this.tssf = tssf;
	}

	public String getTsslhbl() {
		return this.tsslhbl;
	}

	public void setTsslhbl(String tsslhbl) {
		this.tsslhbl = tsslhbl;
	}

	public String getHjszd() {
		return this.hjszd;
	}

	public void setHjszd(String hjszd) {
		this.hjszd = hjszd;
	}

	public String getJl() {
		return this.jl;
	}

	public void setJl(String jl) {
		this.jl = jl;
	}

	public String getLdry() {
		return this.ldry;
	}

	public void setLdry(String ldry) {
		this.ldry = ldry;
	}

	public String getWcnrjtbj() {
		return this.wcnrjtbj;
	}

	public void setWcnrjtbj(String wcnrjtbj) {
		this.wcnrjtbj = wcnrjtbj;
	}

	public String getJtjjzk() {
		return this.jtjjzk;
	}

	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}

	public String getGjdq() {
		return this.gjdq;
	}

	public void setGjdq(String gjdq) {
		this.gjdq = gjdq;
	}

	public String getZzjgdm() {
		return this.zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getDwxz() {
		return this.dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public String getTshy() {
		return this.tshy;
	}

	public void setTshy(String tshy) {
		this.tshy = tshy;
	}

	public String getFddbr() {
		return this.fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}

	public String getDbrzjzl() {
		return this.dbrzjzl;
	}

	public void setDbrzjzl(String dbrzjzl) {
		this.dbrzjzl = dbrzjzl;
	}

	public String getDbrzjhm() {
		return this.dbrzjhm;
	}

	public void setDbrzjhm(String dbrzjhm) {
		this.dbrzjhm = dbrzjhm;
	}

	public Timestamp getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getSsdlr() {
		return this.ssdlr;
	}

	public void setSsdlr(String ssdlr) {
		this.ssdlr = ssdlr;
	}

	public String getSsdlrbh() {
		return this.ssdlrbh;
	}

	public void setSsdlrbh(String ssdlrbh) {
		this.ssdlrbh = ssdlrbh;
	}

	public String getYuanji() {
		return this.yuanji;
	}

	public void setYuanji(String yuanji) {
		this.yuanji = yuanji;
	}

	public String getZibaosf() {
		return this.zibaosf;
	}

	public void setZibaosf(String zibaosf) {
		this.zibaosf = zibaosf;
	}

	public String getSwhjl() {
		return this.swhjl;
	}

	public void setSwhjl(String swhjl) {
		this.swhjl = swhjl;
	}

	public Integer getZhrs() {
		return this.zhrs;
	}

	public void setZhrs(Integer zhrs) {
		this.zhrs = zhrs;
	}

	public String getLxqj() {
		return this.lxqj;
	}

	public void setLxqj(String lxqj) {
		this.lxqj = lxqj;
	}

	public String getZmms() {
		return this.zmms;
	}

	public void setZmms(String zmms) {
		this.zmms = zmms;
	}

	public String getLaoj() {
		return this.laoj;
	}

	public void setLaoj(String laoj) {
		this.laoj = laoj;
	}

	public String getLeif() {
		return this.leif;
	}

	public void setLeif(String leif) {
		this.leif = leif;
	}

	public String getWfjs() {
		return this.wfjs;
	}

	public void setWfjs(String wfjs) {
		this.wfjs = wfjs;
	}

	public String getWsd() {
		return this.wsd;
	}

	public void setWsd(String wsd) {
		this.wsd = wsd;
	}

	public Double getFzje() {
		return this.fzje;
	}

	public void setFzje(Double fzje) {
		this.fzje = fzje;
	}

	public String getDsrjc() {
		return this.dsrjc;
	}

	public void setDsrjc(String dsrjc) {
		this.dsrjc = dsrjc;
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public Double getFfsdcwjz() {
		return this.ffsdcwjz;
	}

	public void setFfsdcwjz(Double ffsdcwjz) {
		this.ffsdcwjz = ffsdcwjz;
	}

	public Double getSjpcje() {
		return this.sjpcje;
	}

	public void setSjpcje(Double sjpcje) {
		this.sjpcje = sjpcje;
	}

	public Integer getFznl() {
		return this.fznl;
	}

	public void setFznl(Integer fznl) {
		this.fznl = fznl;
	}

	public String getXzqh() {
		return this.xzqh;
	}

	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}

	public String getCfz() {
		return this.cfz;
	}

	public void setCfz(String cfz) {
		this.cfz = cfz;
	}

	public String getCtzz() {
		return this.ctzz;
	}

	public void setCtzz(String ctzz) {
		this.ctzz = ctzz;
	}

	public String getSddz() {
		return this.sddz;
	}

	public void setSddz(String sddz) {
		this.sddz = sddz;
	}

	public String getYlf() {
		return this.ylf;
	}

	public void setYlf(String ylf) {
		this.ylf = ylf;
	}

	public String getYdzdbh() {
		return this.ydzdbh;
	}

	public void setYdzdbh(String ydzdbh) {
		this.ydzdbh = ydzdbh;
	}

	public String getSjhm() {
		return this.sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getCym() {
		return this.cym;
	}

	public void setCym(String cym) {
		this.cym = cym;
	}

	public Double getSsbdje() {
		return this.ssbdje;
	}

	public void setSsbdje(Double ssbdje) {
		this.ssbdje = ssbdje;
	}

	public Double getSsajslf() {
		return this.ssajslf;
	}

	public void setSsajslf(Double ssajslf) {
		this.ssajslf = ssajslf;
	}

	public String getXbgrjqs() {
		return this.xbgrjqs;
	}

	public void setXbgrjqs(String xbgrjqs) {
		this.xbgrjqs = xbgrjqs;
	}

	public String getSqmscczzsyqdx() {
		return this.sqmscczzsyqdx;
	}

	public void setSqmscczzsyqdx(String sqmscczzsyqdx) {
		this.sqmscczzsyqdx = sqmscczzsyqdx;
	}

	public String getSsdwms() {
		return this.ssdwms;
	}

	public void setSsdwms(String ssdwms) {
		this.ssdwms = ssdwms;
	}

	public String getSflg() {
		return this.sflg;
	}

	public void setSflg(String sflg) {
		this.sflg = sflg;
	}

	public String getRzcj() {
		return this.rzcj;
	}

	public void setRzcj(String rzcj) {
		this.rzcj = rzcj;
	}

	public String getFzxt() {
		return this.fzxt;
	}

	public void setFzxt(String fzxt) {
		this.fzxt = fzxt;
	}

	public String getGtfzdw() {
		return this.gtfzdw;
	}

	public void setGtfzdw(String gtfzdw) {
		this.gtfzdw = gtfzdw;
	}

	public String getSfdzsd() {
		return this.sfdzsd;
	}

	public void setSfdzsd(String sfdzsd) {
		this.sfdzsd = sfdzsd;
	}

}