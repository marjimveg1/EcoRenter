package com.ispp.EcoRenter.security;

import org.springframework.security.core.GrantedAuthority;

import com.ispp.EcoRenter.model.DomainEntity;

//@Entity
//@Access(AccessType.PROPERTY)
//public class Authority extends DomainEntity implements GrantedAuthority {
public class Authority {
//	// Constructors -----------------------------------------------------------
//	private static final long serialVersionUID = 1L;
//
//	public Authority() {
//		super();
//	}
//
//	// Values -----------------------------------------------------------------
//
//	public static final String ADMIN = "ADMIN";
//	public static final String OWNER = "OWNER";
//	public static final String RENTER = "RENTER";
//
//	// Attributes -------------------------------------------------------------
//
//	private String authority;
//	
//	@NotBlank
//	@Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.OWNER + "|" + Authority.RENTER + "$")
//	@Override
//	public String getAuthority() {
//		return this.authority;
//	}
//
//	public void setAuthority(final String authority) {
//		this.authority = authority;
//	}
//	
//	public static Collection<Authority> listAuthorities() {
//		Collection<Authority> result;
//		Authority authority;
//
//		result = new ArrayList<Authority>();
//
//		authority = new Authority();
//		authority.setAuthority(Authority.ADMIN);
//		result.add(authority);
//
//		authority = new Authority();
//		authority.setAuthority(Authority.OWNER);
//		result.add(authority);
//
//		authority = new Authority();
//		authority.setAuthority(Authority.RENTER);
//		result.add(authority);
//
//		return result;
//	}
//	
//	@Override
//	public int hashCode() {
//		return this.getAuthority().hashCode();
//	}
//
//	@Override
//	public boolean equals(final Object other) {
//		boolean result;
//
//		if (this == other)
//			result = true;
//		else if (other == null)
//			result = false;
//		else if (!this.getClass().isInstance(other))
//			result = false;
//		else
//			result = (this.getAuthority().equals(((Authority) other).getAuthority()));
//
//		return result;
//	}
//
//	@Override
//	public String toString() {
//		return this.authority;
//	}
	
}
