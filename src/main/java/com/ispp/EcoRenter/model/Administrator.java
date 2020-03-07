package com.ispp.EcoRenter.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@Access(AccessType.PROPERTY)
public class Administrator extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Constructors ----------------------------------
	
	public Administrator() {
		super();
	}
	
}
