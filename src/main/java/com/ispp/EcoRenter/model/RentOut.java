package com.ispp.EcoRenter.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "rentOut")
@Access(AccessType.PROPERTY)
public class RentOut extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ----------------------------------
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	
	@Min(1)
	private Integer month;
	
	// Constructors -------------------------------
	
	public RentOut() {
		super();
	}

	
	// Getters and Setters ------------------------
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
	// Associations ------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false, targetEntity = Renter.class)
	private Renter renter;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false, targetEntity = Smallholding.class)
	private Smallholding smallholding;
	
	@NotNull
	@Valid
	@ManyToOne(optional = true)
	private Valuation valuation;


	public Renter getRenter() {
		return renter;
	}

	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	public Smallholding getSmallholding() {
		return smallholding;
	}

	public void setSmallholding(Smallholding smallholding) {
		this.smallholding = smallholding;
	}

	public Valuation getValuation() {
		return valuation;
	}

	public void setValuation(Valuation valuation) {
		this.valuation = valuation;
	}
		
}
