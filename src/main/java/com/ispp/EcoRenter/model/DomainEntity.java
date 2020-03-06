package com.ispp.EcoRenter.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Access(value = AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DomainEntity implements Serializable {

	// Serialisation identifier -----------------------------------------------
	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Version
	private int version;

	// Getters and setters -----------------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	// Derived attributes ----------------------------------------------------

	@Transient
	public boolean isTransient() {
		boolean result;

		result = this.id == 0;

		return result;
	}

	// Object interface -------------------------------------------------------

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public boolean equals(final Object other) {
		boolean result;

		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (other instanceof Integer) {
			result = this.getId() == (Integer) other;
		} else if (!this.getClass().isInstance(other)) {
			result = false;
		} else {
			result = this.getId() == ((DomainEntity) other).getId();
		}

		return result;
	}

	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.getClass().getName());
		result.append("{");
		result.append("id=");
		result.append(this.getId());
		result.append(", version=");
		result.append(this.getVersion());
		result.append("}");

		return result.toString();
	}

}
