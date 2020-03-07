package com.ispp.EcoRenter.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Constructors ----------------------------------
	
	public Administrator() {
		super();
	}
	
}
