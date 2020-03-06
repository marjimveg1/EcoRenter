package com.ispp.EcoRenter.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Pattern(regexp = "^[^0-9]$")
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^[^0-9]$")
	private String surname;
	
	@Transient
	private String fullname;
	
	@NotBlank
	@Column(unique = true)
	@Email
	private String email;
	
	@NotBlank
	private String telephoneNumber;

	
	public Actor() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullname() {
		return this.name + " " + this.surname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
}
