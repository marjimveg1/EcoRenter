package com.ispp.EcoRenter.service;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.repository.ActorRepository;
import com.ispp.EcoRenter.security.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    // Other business methods

    public Actor findByPrincipal(){
        Actor result;
        UserAccount userAccount;
        Authentication authentication;

        authentication = SecurityContextHolder.getContext().getAuthentication();
        userAccount = (UserAccount) authentication.getPrincipal();
        
        result = this.actorRepository.findActorByUserAccountId(userAccount.getId());
        Assert.notNull(result,"El actor no existe");

        return result;
    }
}