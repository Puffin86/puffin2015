package com.bsoft.sszx.entity.user;

/**
 * BmbId entity. @author MyEclipse Persistence Tools
 */

public class BmbId implements java.io.Serializable {

	// Fields

	private String bmdm;
	private String dwdm;

	// Constructors

	/** default constructor */
	public BmbId() {
	}

	/** full constructor */
	public BmbId(String bmdm, String dwdm) {
		this.bmdm = bmdm;
		this.dwdm = dwdm;
	}

	// Property accessors

	public String getBmdm() {
		return this.bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getDwdm() {
		return this.dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BmbId))
			return false;
		BmbId castOther = (BmbId) other;

		return ((this.getBmdm() == castOther.getBmdm()) || (this.getBmdm() != null
				&& castOther.getBmdm() != null && this.getBmdm().equals(
				castOther.getBmdm())))
				&& ((this.getDwdm() == castOther.getDwdm()) || (this.getDwdm() != null
						&& castOther.getDwdm() != null && this.getDwdm()
						.equals(castOther.getDwdm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBmdm() == null ? 0 : this.getBmdm().hashCode());
		result = 37 * result
				+ (getDwdm() == null ? 0 : this.getDwdm().hashCode());
		return result;
	}

}