package com.ispp.EcoRenter.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes -----------------------------------
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date writtenMoment;
	
	@NotBlank
	@Column(length = 30000)
	private String text;
	
	// Constructors ---------------------------------
	
	public Comment() {
		super();
	}

	// Getters and setters --------------------------
	
	public Date getWrittenMoment() {
		return writtenMoment;
	}

	public void setWrittenMoment(Date writtenMoment) {
		this.writtenMoment = writtenMoment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	// Associations ---------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private RentOut rentOut;

	public RentOut getRentOut() {
		return rentOut;
	}

	public void setRentOut(RentOut rentOut) {
		this.rentOut = rentOut;
	}
	
}
