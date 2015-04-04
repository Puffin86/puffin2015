package com.bsoft.sszx.entity.fjb;

/**
 * FjbId entity. @author MyEclipse Persistence Tools
 */

public class FjbId implements java.io.Serializable {

	// Fields

	private Integer bh;
	private String fydm;
	private Integer xh;

	// Constructors

	/** default constructor */
	public FjbId() {
	}

	/** full constructor */
	public FjbId(Integer bh, String fydm, Integer xh) {
		this.bh = bh;
		this.fydm = fydm;
		this.xh = xh;
	}

	// Property accessors

	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FjbId))
			return false;
		FjbId castOther = (FjbId) other;

		return ((this.getBh() == castOther.getBh()) || (this.getBh() != null
				&& castOther.getBh() != null && this.getBh().equals(
				castOther.getBh())))
				&& ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
						&& castOther.getFydm() != null && this.getFydm()
						.equals(castOther.getFydm())))
				&& ((this.getXh() == castOther.getXh()) || (this.getXh() != null
						&& castOther.getXh() != null && this.getXh().equals(
						castOther.getXh())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBh() == null ? 0 : this.getBh().hashCode());
		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		result = 37 * result + (getXh() == null ? 0 : this.getXh().hashCode());
		return result;
	}

}