package com.ispp.EcoRenter.service;

import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.repository.OwnerRepository;
import com.ispp.EcoRenter.security.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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