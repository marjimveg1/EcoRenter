package com.ispp.EcoRenter.service;

import java.util.Collection;


import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.model.Renter;
import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.repository.SmallholdingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SmallholdingService {

    // Repository

    @Autowired
    private SmallholdingRepository smallholdingRepository;

    // Supporting services

    @Autowired
    private ActorService actorService;

    // Constructor

    public SmallholdingService(){
        super();
    }

    // CRUD Methods

    public Collection<Smallholding> findAll() {
        Collection<Smallholding> result;

        result = this.smallholdingRepository.findAll();

        return result;
    }

    public Smallholding findOne(int smallholdingId) {
        Smallholding result;

        result = this.smallholdingRepository.getOne(smallholdingId);
        Assert.notNull(result, "La parcela no existe");

        return result;
    }

    public Smallholding findOneToDisplay(int smallholdingId){
        Smallholding result;
        Actor principal;

        result = this.findOne(smallholdingId);

        try {
            principal = this.actorService.findByPrincipal();
        } catch(Exception e){
            principal = null;
        }

        /*
            If principal is a renter or unauthenticated, he/she displays availables smallholdings only.

            For the other side, if principal is an owner, he/she displays availables smallholdings unless it's his/her own so
            displays unavailables too.
        */
        if((principal != null && principal instanceof Renter) || principal == null)
            Assert.isTrue(result.isAvailable(),"La parcela no se puede mostrar");
        else if(principal != null && principal instanceof Owner){
            Assert.isTrue(result.isAvailable() || result.getOwner().equals(principal),"La parcela no se puede mostrar");
        }

        return result;
        
        
    }

    // Other business methods

    public Collection<Smallholding> findSmallholdingsByOwnerId(int ownerId){
        Collection<Smallholding> result;

        result = this.smallholdingRepository.findSmallholdingsByOwnerId(ownerId);

        return result;
    }

    public Collection<Smallholding> findSmallholdingsAvailables(){
        Collection<Smallholding> result;

        result = this.smallholdingRepository.findSmallholdingsAvailables();

        return result;
    }


}