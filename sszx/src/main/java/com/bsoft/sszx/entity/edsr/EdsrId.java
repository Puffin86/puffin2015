package com.bsoft.sszx.entity.edsr;

/**
 * EdsrId entity. @author MyEclipse Persistence Tools
 */

public class EdsrId implements java.io.Serializable {

	// Fields

	private String ahdm;
	private String xh;

	// Constructors

	/** default constructor */
	public EdsrId() {
	}

	/** full constructor */
	public EdsrId(String ahdm, String xh) {
		this.ahdm = ahdm;
		this.xh = xh;
	}

	// Property accessors

	public String getAhdm() {
		return this.ahdm;
	}

	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EdsrId))
			return false;
		EdsrId castOther = (EdsrId) other;

		return ((this.getAhdm() == castOther.getAhdm()) || (this.getAhdm() != null
				&& castOther.getAhdm() != null && this.getAhdm().equals(
				castOther.getAhdm())))
				&& ((this.getXh() == castOther.getXh()) || (this.getXh() != null
						&& castOther.getXh() != null && this.getXh().equals(
						castOther.getXh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAhdm() == null ? 0 : this.getAhdm().hashCode());
		result = 37 * result + (getXh() == null ? 0 : this.getXh().hashCode());
		return result;
	}

}