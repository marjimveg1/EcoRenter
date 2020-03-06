package com.ispp.EcoRenter.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Smallholding extends DomainEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String size;
	
	@NotBlank
	private String description;
	
	@NotBlank
	@Pattern(regexp = "^[^0-9]$")
	private String farmingType;
	
	@DecimalMin(value = "0.0")
	@Digits(integer = 9, fraction = 2)
	private double price;
	
	@NotBlank
	@Pattern(regexp = "^[^0-9]$")
	private String province;
	
	@NotBlank
	@Pattern(regexp = "^[^0-9]$")
	private String locality;
	
	@NotBlank
	private String address;
	
	@NotBlank
	@Pattern(regexp = "\\d{5}$")
	private String postalCode;
	
	@NotBlank
	@Pattern(regexp = "^RENTED|NOTRENTED|ARGUMENT$")
	private String status;
	
	@Range(min = 0, max = 90)
	private String latitude;
	
	@Range(min = -180, max = 180)
	private String longitude;
	
	@Min(1)
	private Integer maxDuration;
	
	private boolean isAvailable;
	
	
	public Smallholding() {
		super();
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFarmingType() {
		return farmingType;
	}

	public void setFarmingType(String farmingType) {
		this.farmingType = farmingType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Integer maxDuration) {
		this.maxDuration = maxDuration;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
}
