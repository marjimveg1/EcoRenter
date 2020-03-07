package com.ispp.EcoRenter.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "renter")
public class Renter extends DomainEntity {

	private static final long serialVersionUID = 1L;
	
	private String iban;


	public Renter() {
		super();
	}
	
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	
	
}
