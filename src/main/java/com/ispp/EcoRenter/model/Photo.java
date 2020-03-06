package com.ispp.EcoRenter.model;

import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Photo extends DomainEntity {

	private static final long serialVersionUID = 1L;

	// Attributes ------------------------------------
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String format;
	
	@NotNull
	private Blob data;
	
	// Constructors ----------------------------------
	
	public Photo() {
		super();
	}

	// Getters and setters --------------------------
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}
	
}
