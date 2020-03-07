package com.ispp.EcoRenter.model;

import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "photo")
public class Photo extends DomainEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	private String suffix;
	private String content;
	private Blob structure;
	
	public Photo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blob getStructure() {
		return structure;
	}

	public void setStructure(Blob structure) {
		this.structure = structure;
	}
	
	
}
