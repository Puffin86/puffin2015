package com.bsoft.sszx.entity.zjqd;

/**
 * ZjqdId entity. @author MyEclipse Persistence Tools
 */

public class ZjqdId implements java.io.Serializable {

	// Fields

	private Integer bh;
	private String fydm;

	// Constructors

	/** default constructor */
	public ZjqdId() {
	}

	/** full constructor */
	public ZjqdId(Integer bh, String fydm) {
		this.bh = bh;
		this.fydm = fydm;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZjqdId))
			return false;
		ZjqdId castOther = (ZjqdId) other;

		return ((this.getBh() == castOther.getBh()) || (this.getBh() != null
				&& castOther.getBh() != null && this.getBh().equals(
				castOther.getBh())))
				&& ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
						&& castOther.getFydm() != null && this.getFydm()
						.equals(castOther.getFydm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBh() == null ? 0 : this.getBh().hashCode());
		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		return result;
	}

}