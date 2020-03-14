package com.ispp.EcoRenter.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "owner")
public class Owner extends Actor {
 
	private static final long serialVersionUID = 1L;

	private String iban;
	
	@Min(0)
	private int accumulatedMonths;
	
	
	public Owner() {
		super();
	}
	
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getAccumulatedMonths() {
		return accumulatedMonths;
	}

	public void setAccumulatedMonths(int accumulatedMonths) {
		this.accumulatedMonths = accumulatedMonths;
	}
		
}
