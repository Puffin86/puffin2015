package com.bsoft.sszx.entity.user;

/**
 * UserId entity. @author MyEclipse Persistence Tools
 */

public class UserId implements java.io.Serializable {

	// Fields

	private String dwdm;
	private String yhid;

	// Constructors

	/** default constructor */
	public UserId() {
	}

	/** full constructor */
	public UserId(String dwdm, String yhid) {
		this.dwdm = dwdm;
		this.yhid = yhid;
	}

	// Property accessors

	public String getDwdm() {
		return this.dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getYhid() {
		return this.yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserId))
			return false;
		UserId castOther = (UserId) other;

		return ((this.getDwdm() == castOther.getDwdm()) || (this.getDwdm() != null
				&& castOther.getDwdm() != null && this.getDwdm().equals(
				castOther.getDwdm())))
				&& ((this.getYhid() == castOther.getYhid()) || (this.getYhid() != null
						&& castOther.getYhid() != null && this.getYhid()
						.equals(castOther.getYhid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDwdm() == null ? 0 : this.getDwdm().hashCode());
		result = 37 * result
				+ (getYhid() == null ? 0 : this.getYhid().hashCode());
		return result;
	}

}