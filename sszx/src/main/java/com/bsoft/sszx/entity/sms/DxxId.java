package com.bsoft.sszx.entity.sms;

/**
 * DxxId entity. @author MyEclipse Persistence Tools
 */

public class DxxId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fydm;

	// Constructors

	/** default constructor */
	public DxxId() {
	}

	/** full constructor */
	public DxxId(Integer id, String fydm) {
		this.id = id;
		this.fydm = fydm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(other instanceof DxxId))
			return false;
		DxxId castOther = (DxxId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
						&& castOther.getFydm() != null && this.getFydm()
						.equals(castOther.getFydm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		return result;
	}

}