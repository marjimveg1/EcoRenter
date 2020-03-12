package com.ispp.EcoRenter.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.security.Authority;
import com.ispp.EcoRenter.service.ActorService;

@SpringBootTest
public class ActorServiceTest {

	@Autowired
	private ActorService actorService;
	
	@Test
	public void positiveTest_findOne_uno() {
		boolean result;
		int actorId;
		Actor actor;
		
		actorId = 100;
		actor = this.actorService.findOne(actorId);
		
		result = this.actorService.isASpecificRole(actor, Authority.ADMIN);
		
		assertNotNull(actor);
		assertTrue(result);
	}
	
	@Test
	public void positiveTest_findOne_dos() {
		boolean result;
		int actorId;
		Actor actor;
		
		actorId = 300;
		actor = this.actorService.findOne(actorId);
		
		result = this.actorService.isASpecificRole(actor, Authority.RENTER);
		
		assertNotNull(actor);
		assertTrue(result);
	}
	
	@Test
	public void positiveTest_findOne_tres() {		
		boolean result;
		int actorId;
		Actor actor;
		
		actorId = 200;
		actor = this.actorService.findOne(actorId);
		
		result = this.actorService.isASpecificRole(actor, Authority.OWNER);
		
		assertNotNull(actor);
		assertTrue(result);
	}
	
}
