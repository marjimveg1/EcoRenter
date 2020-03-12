package com.ispp.EcoRenter.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.model.Renter;
import com.ispp.EcoRenter.service.ActorService;
import com.ispp.EcoRenter.service.OwnerService;
import com.ispp.EcoRenter.service.RenterService;

@Controller
@RequestMapping("/actor")
public class ActorController {

	private static final Log log = LogFactory.getLog(ActorController.class);
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private RenterService renterService;
	
	public ActorController() {
		super();
	}
	
	@GetMapping(value = "/display")
	public ModelAndView findOne(@RequestParam(required = false, defaultValue = "0") int actorId) {
		ModelAndView result;
		Actor actor, principal;
		boolean isMyProfile;
		Renter renter;
		Owner owner;
		int principalId;
		String iban;
		
		principal = this.actorService.findByPrincipal();
		principalId = principal.getId();
		
		isMyProfile = principalId == actorId;
		
		result = new ModelAndView("actor/display");
		
		if (isMyProfile) {
			renter = this.renterService.findOne(principalId);
			owner = this.ownerService.findOne(principalId);
			
			if (renter != null) {
				iban = this.actorService.getEncodedIban(renter.getIban());
			} else if (owner != null){
				iban = this.actorService.getEncodedIban(owner.getIban());
			} else {
				iban = "";
			}
			
			result.addObject("iban", iban);
		}
		
		actor = this.actorService.findOne(actorId);
		
		result.addObject("actor", actor);
		
		log.info("Actor enviado desde el controlador a la vista.");
		
		return result;
	}
	
}
