package com.ispp.EcoRenter.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.model.Renter;
import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.repository.SmallholdingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Smallholding> findPaginated(Pageable pageable, Collection<Smallholding> smallholdings) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Smallholding> list;
        ArrayList<Smallholding> smaList= new ArrayList<>(smallholdings);
 
        if (smaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, smaList.size());
            list = smaList.subList(startItem, toIndex);
        }
 
        Page<Smallholding> smPage
          = new PageImpl<Smallholding>(list, PageRequest.of(currentPage, pageSize), smaList.size());
 
        return smPage;
    }

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

    public Boolean isSmallholdingRentedByRenter(int renterId, int smallholdingId){
        Boolean result;

        result = this.smallholdingRepository.isSmallholdingRentedByRenter(renterId, smallholdingId);

        return result;
    }


}