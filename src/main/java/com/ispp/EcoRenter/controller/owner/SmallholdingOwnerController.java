package com.ispp.EcoRenter.controller.owner;

import java.util.Collection;

import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.service.OwnerService;
import com.ispp.EcoRenter.service.SmallholdingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owner/smallholding")
public class SmallholdingOwnerController {

    // Services

    @Autowired
    private SmallholdingService smallholdingService;

    @Autowired
    private OwnerService ownerService;

    // Constructor

    public SmallholdingOwnerController(){
        super();
    }

    // List

    @GetMapping("/listOwnSmallholdings")
    public ModelAndView list(){
        ModelAndView result;
        Collection<Smallholding> smallholdings;
        Owner principal;

        try {
            principal = this.ownerService.findByPrincipal();
            smallholdings = this.smallholdingService.findSmallholdingsByOwnerId(principal.getId());

            result = new ModelAndView("smallholding/list");
            result.addObject("smallholdings", smallholdings);
            result.addObject("requestURI", "owner/smallholding/listOwnSmallholdings.do");
        } catch (Exception e){
            result = new ModelAndView("redirect:miscellaneous/error");
        }
        

        return result;
    }

    
}