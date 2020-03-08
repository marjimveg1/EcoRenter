package com.ispp.EcoRenter.controller;

import java.util.Collection;

import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.service.SmallholdingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/smallholding")
public class SmallholdingController {

    // Services

    @Autowired
    private SmallholdingService smallholdingService;

    // Constructor

    public SmallholdingController(){
        super();
    }

    // List

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView result;
        Collection<Smallholding> smallholdings;

        try {
            smallholdings = this.smallholdingService.findSmallholdingsAvailables();

            result = new ModelAndView("smallholding/list");
            result.addObject("smallholdings", smallholdings);
            result.addObject("requestURI", "smallholding/list.do");
        } catch (Exception e){
            result = new ModelAndView("redirect:miscellaneous/error");
        }
        

        return result;
    }

    @GetMapping("/display")
    public ModelAndView display(@RequestParam int smallholdingId){
        ModelAndView result;
        Smallholding smallholding;

        try {
            smallholding = this.smallholdingService.findOneToDisplay(smallholdingId);

            result = new ModelAndView("smallholding/display");
            result.addObject("smallholding", smallholding);
        } catch(Exception e) {
            result = new ModelAndView("redirect:miscellaneous/error");
        }

        return result;
    }

}