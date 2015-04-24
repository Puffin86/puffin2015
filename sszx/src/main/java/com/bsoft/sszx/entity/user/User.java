package com.bsoft.sszx.entity.user;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private UserId id;
	private String yhxm;
	private String yhid;
	private String yhbm;
	private String bmmc;
	private String pass;
	private String js;
	private String jsmc;
	private String lxdh;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(UserId id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	/** full constructor */
	public User(UserId id, String yhxm, String yhbm, String pass, String js,
			String lxdh) {
		this.id = id;
		this.yhxm = yhxm;
		this.yhbm = yhbm;
		this.pass = pass;
		this.js = js;
		this.lxdh = lxdh;
	}

	// Property accessors

	public UserId getId() {
		return this.id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public String getYhxm() {
		return this.yhxm;
	}

	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}

	public String getYhbm() {
		return this.yhbm;
	}

	public void setYhbm(String yhbm) {
		this.yhbm = yhbm;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getJs() {
		return this.js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

}