package com.ispp.EcoRenter.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import com.ispp.EcoRenter.helper.PasswordHelper;
import com.ispp.EcoRenter.helper.StringHelper;
import com.ispp.EcoRenter.model.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity implements UserDetails {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	public UserAccount() {
		super();

		this.authorities = new ArrayList<Authority>();
	}
	
	// Attributes -------------------------------------------------------------

	private boolean					isBanned;
	
	// UserDetails interface -------------------------------------------------------------

	@NotBlank
	@Length(min = 5, max = 60)
	@Column(unique = true)
	private String username;

	@NotBlank
	@Length(min = 5, max = 60)
	private String password;

	@NotEmpty
	@Valid
	@ElementCollection
	private Collection<Authority>	authorities;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		assert password == null || password.equals("") || !PasswordHelper.isEncoded(password);

		if (!StringHelper.isBlank(password)) {
			this.password = PasswordHelper.encode(password);
		}
	}

	public Collection<Authority> getAuthorities() {
		// WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
		return this.authorities;
	}

	public void setAuthorities(final Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(final Authority authority) {
		assert !this.authorities.contains(authority);

		this.authorities.add(authority);
	}

	public void removeAuthority(final Authority authority) {
		assert authority != null;
		assert this.authorities.contains(authority);
		
		this.authorities.remove(authority);
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !this.isBanned;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	
}