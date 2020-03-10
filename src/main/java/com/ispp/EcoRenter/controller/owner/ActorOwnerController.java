package com.ispp.EcoRenter.controller.owner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ispp.EcoRenter.controller.ActorController;
import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.service.ActorService;
import com.ispp.EcoRenter.service.OwnerService;

@Controller
@RequestMapping("/actor/owner")
public class ActorOwnerController {

private static final Log log = LogFactory.getLog(ActorController.class);
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private OwnerService ownerService;
	
	
	public ActorOwnerController() {
		super();
	}
	
	
	@GetMapping(value = "/display")
	public ModelAndView findOne(@RequestParam(required = false, defaultValue = "0") int actorId) {
		ModelAndView result;
		Actor actor;
		Owner principal;
		boolean isMyProfile;
		String iban;
		
		principal = this.ownerService.findByPrincipal();
		
		isMyProfile = actorId == principal.getId();
		
		actor = this.actorService.findOne(principal.getId());
		
		result = new ModelAndView("actor/display");
		
		if (isMyProfile) {
			iban = this.actorService.getEncodedIban(principal.getIban());
		
			result.addObject("iban", iban);
			
			log.info("Es un propietario.");
		}
		
		result.addObject("actor", actor);
		
		return result;
	}
	
}
