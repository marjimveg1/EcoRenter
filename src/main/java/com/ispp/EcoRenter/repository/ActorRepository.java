package com.ispp.EcoRenter.repository;

import com.ispp.EcoRenter.model.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {
    
    @Query("select a from Actor a where a.userAccount.username = ?1")
    Actor findActorByUsername(String username);
}