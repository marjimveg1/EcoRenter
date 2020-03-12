package com.ispp.EcoRenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.repository.ActorRepository;
import com.ispp.EcoRenter.security.Authority;
import com.ispp.EcoRenter.security.UserAccount;

@Service
public class ActorService {

    // Repository

    @Autowired
    private ActorRepository actorRepository;

    // Supporting services

    // Constructor

    public ActorService(){
        super();
    }

    // CRUD Methods
    public Actor findOne(int actorId) {
    	Actor principal, result;
    	Optional<Actor> optionalActor;
    	boolean isARenter, isRoleActor, isAOwner;
    	
    	principal = this.findByPrincipal();
    	
    	optionalActor = this.actorRepository.findById(actorId);
    	
    	result = (optionalActor.isPresent()) ? optionalActor.get() : principal;
    	
    	isAOwner = this.isASpecificRole(principal, Authority.OWNER);
    	isARenter = this.isASpecificRole(principal, Authority.RENTER);
    	
    	if (isAOwner) {
    		isRoleActor = this.isASpecificRole(result, Authority.RENTER);
    		
    		Assert.isTrue(isRoleActor, "Un arrendatario no puede acceder a info de otro arrendatario");
    	} else if (isARenter) {
    		isRoleActor = this.isASpecificRole(result, Authority.OWNER);
  
    		Assert.isTrue(isRoleActor, "Un propietario no puede acceder a info de otro propietario");
    	}
    	
    	return result;
    }
    
    // Other business methods

    public Actor findByPrincipal(){
        Actor result;
        UserDetails userAccount;
        Authentication authentication;

        authentication = SecurityContextHolder.getContext().getAuthentication();
        userAccount = (UserDetails) authentication.getPrincipal();
        
        result = this.actorRepository.findActorByUsername(userAccount.getUsername());
        Assert.notNull(result,"El actor no existe");

        return result;
    }
    
    public boolean isASpecificRole(Actor actor, String role) {
    	boolean result;
    	Authority authority;
    	
    	authority = new Authority();
    	authority.setAuthority(role);
    	
    	result = actor.getUserAccount().getAuthorities().contains(authority);
    	
    	return result;
    }
    
    public String getEncodedIban(String iban) {
    	String result;
    	
    	result = (iban != null && iban != "" && !iban.isEmpty())
    			? iban.substring(0, 4) + " **** **** **** **** " + iban.subSequence(19, 23)
    			: "";
    	
    	return result;
    }
    
}