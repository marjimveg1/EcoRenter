package com.ispp.EcoRenter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ispp.EcoRenter.configuration.MyUserDetails;
import com.ispp.EcoRenter.helper.Utils;
import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.repository.ActorRepository;
import com.ispp.EcoRenter.security.Authority;

@Service
public class ActorService {

	private static final Log log = LogFactory.getLog(ActorService.class);
	
    // Repository

    @Autowired
    private ActorRepository actorRepository;
    
    @Autowired
	private Utils utils;
    
    // Supporting services

    // Constructor

    public ActorService(){
        super();
    }

    // CRUD Methods
    public Actor findOne(int actorId) {
    	Actor principal, result;
    	Optional<Actor> optionalActor;
    	boolean isARenter, isRoleActor, isAOwner, isMyProfile;
    	
    	principal = this.findByPrincipal();
    	
    	optionalActor = this.actorRepository.findById(actorId);
    	
    	result = optionalActor.orElse(principal);
    	
    	isMyProfile = principal.getId() == result.getId();
    	
    	isAOwner = this.isASpecificRole(principal, Authority.OWNER);
    	isARenter = this.isASpecificRole(principal, Authority.RENTER);
    	
    	if (isAOwner && !isMyProfile) {
    		isRoleActor = this.isASpecificRole(result, Authority.RENTER);
    		
    		Assert.isTrue(isRoleActor, "Un arrendatario no puede acceder a info de otro arrendatario");
    	} else if (isARenter && !isMyProfile) {
    		isRoleActor = this.isASpecificRole(result, Authority.OWNER);
  
    		Assert.isTrue(isRoleActor, "Un propietario no puede acceder a info de otro propietario");
    	}
    	
    	return result;
    }
    
    // Other business methods

    public Actor findByPrincipal(){
    	Actor result;
    	String username;
    	MyUserDetails userDetails;
    	Object o;
    	
    	o = this.utils.findByPrincipal();
    	userDetails = (MyUserDetails) o;
    	
    	username = userDetails.getUsername().trim();
    	
    	result = this.findByUsername(username);
    	
    	log.info("Actor recuperado: " + result.toString());
    	
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
    
    public String getRole(Actor actor) {
    	String result;
    	List<Authority> authorities;
    	
    	authorities = new ArrayList<Authority>(actor.getUserAccount().getAuthorities());
    	result = "";
    	for (Authority a: authorities) {
    		result += a.getAuthority() + " ";
    	}
    	 	
    	return result;
    }
    
    private Actor findByUsername(String username) {
    	Actor result;
    	
    	result = this.actorRepository.findByUsername(username);
    	
    	return result;
    }
    
}