package com.bsoft.sszx.entity.clb;

/**
 * ClbId entity. @author MyEclipse Persistence Tools
 */

public class ClbId implements java.io.Serializable {

	// Fields

	private Integer bh;
	private Integer xh;
	private String fydm;

	// Constructors

	/** default constructor */
	public ClbId() {
	}

	/** full constructor */
	public ClbId(Integer bh, Integer xh, String fydm) {
		this.bh = bh;
		this.xh = xh;
		this.fydm = fydm;
	}

	// Property accessors

	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClbId))
			return false;
		ClbId castOther = (ClbId) other;

		return ((this.getBh() == castOther.getBh()) || (this.getBh() != null
				&& castOther.getBh() != null && this.getBh().equals(
				castOther.getBh())))
				&& ((this.getXh() == castOther.getXh()) || (this.getXh() != null
						&& castOther.getXh() != null && this.getXh().equals(
						castOther.getXh())))
				&& ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
						&& castOther.getFydm() != null && this.getFydm()
						.equals(castOther.getFydm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBh() == null ? 0 : this.getBh().hashCode());
		result = 37 * result + (getXh() == null ? 0 : this.getXh().hashCode());
		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		return result;
	}

}