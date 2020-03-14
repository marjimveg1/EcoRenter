package com.ispp.EcoRenter.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.service.ActorService;

@SpringBootTest
public class ActorServiceTest {

	@Autowired
	private ActorService actorService;
	
	
	/*
	 * Un administrador puede visitar cualquier perfil
	 */
	@WithMockUser("admin2")
	@Transactional
	@Test
	public void positiveTest_findOne_one() {
		int actorId;
		Actor actor;
		
		// Id de un administrador (admin3)
		actorId = 102;
		actor = this.actorService.findOne(actorId);
		
		assertNotNull(actor);
	}
	
	/*
	 * Un administrador puede acceder a su perfil
	 */
	@WithMockUser("admin2")
	@Test
	@Transactional
	public void positiveTest_findOne_two() {
		int actorId;
		Actor actor;
		
		actorId = 101;
		actor = this.actorService.findOne(actorId);
		
		assertNotNull(actor);
	}
	
	/*
	 * Un administrador puede accede a un perfil
	 */
	@WithMockUser("admin2")
	@Test
	@Transactional
	public void positiveTest_findOne_three() {
		
		int actorId;
		Actor actor;
		
		// id de un propietario (owner2)
		actorId = 201;
		actor = this.actorService.findOne(actorId);
		
		assertNotNull(actor);
	}
	
	/*
	 * Un owner puede acceder al perfil de un arrendatario
	 */
	@WithMockUser("owner2")
	@Test
	@Transactional
	public void positiveTest_findOne_four() {
		int actorId;
		Actor actor;
		
		// id de un arrendatario (renter2)
		actorId = 301;
		actor = this.actorService.findOne(actorId);
		
		assertNotNull(actor);
	}
	
	
	/*
	 * Un renter puede acceder al perfil de un propietario
	 */
	@WithMockUser("renter2")
	@Test
	@Transactional
	public void positiveTest_findOne_five() {
		int actorId;
		Actor actor;
		
		// id de un arrendatario (owner2)
		actorId = 201;
		actor = this.actorService.findOne(actorId);
		
		assertNotNull(actor);
	}
	
	/*
	 * Un owner puede acceder al perfil de un arrendatario.
	 * Sin embargo, el owner está baneado.
	 */
	@WithMockUser("owner6")
	@Test
	@Transactional
	public void negativeTest_findOne_one() {
		int actorId;
		Actor actor;
		
		// id de un arrendatario (renter2)
		actorId = 301;
		
		try {
			actor = this.actorService.findOne(actorId);
		} catch (IllegalArgumentException e) {
			actor = null;
		}
		
		assertTrue(actor == null);
	}
	
	/*
	 * Un owner no puede acceder al perfil de otro propietario.
	 */
	@WithMockUser("owner3")
	@Test
	@Transactional
	public void negativeTest_findOne_two() {
		int actorId;
		Actor actor;
		
		// id de un propietario (owner2)
		actorId = 201;
		
		try {
			actor = this.actorService.findOne(actorId);
		} catch (IllegalArgumentException e) {
			actor = null;
		}
		
		assertTrue(actor == null);
	}
	
	/*
	 * Un arrendatario no puede acceder al perfil de otro arrendatario.
	 */
	@WithMockUser("renter2")
	@Test
	@Transactional
	public void negativeTest_findOne_three() {	
		int actorId;
		Actor actor;
		
		// id de un propietario (renter2)
		actorId = 302;
		
		try {
			actor = this.actorService.findOne(actorId);
		} catch (IllegalArgumentException e) {
			actor = null;
		}
		
		assertTrue(actor == null);
	}
	
}
