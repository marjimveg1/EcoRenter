package com.ispp.EcoRenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.repository.OwnerRepository;
import com.ispp.EcoRenter.security.UserAccount;

@Service
public class OwnerService {

    // Repository

    @Autowired
    private OwnerRepository ownerRepository;

    // Supporting services

    // Constructor

    public OwnerService(){
        super();
    }

    // CRUD Methods
    public Owner findOne(int ownerId) {
    	Assert.isTrue(ownerId > 0, "Invalid ownerId");
    	
    	Owner result;
    	
    	result = this.ownerRepository.findById(ownerId).get();
    	
    	return result;
    }

    // Other business methods

    public Owner findByPrincipal(){
        Owner result;
        UserAccount userAccount;
        Authentication authentication;

        authentication = SecurityContextHolder.getContext().getAuthentication();
        userAccount = (UserAccount) authentication.getPrincipal();
        
        result = this.ownerRepository.findOwnerByUserAccountId(userAccount.getId());
        Assert.notNull(result,"El propietario no existe");

        return result;
    }
}