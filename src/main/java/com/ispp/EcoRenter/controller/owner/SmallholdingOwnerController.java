package com.ispp.EcoRenter.controller.owner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ispp.EcoRenter.model.Owner;
import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.service.OwnerService;
import com.ispp.EcoRenter.service.SmallholdingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView list(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        ModelAndView result;
        Collection<Smallholding> smallholdings;
        Owner principal;
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        try {
            result = new ModelAndView("smallholding/list");

            principal = this.ownerService.findByPrincipal();
            smallholdings = this.smallholdingService.findSmallholdingsByOwnerId(principal.getId());
            Page<Smallholding> shPage = this.smallholdingService.findPaginated(PageRequest.of(currentPage - 1, pageSize),smallholdings);
            int totalPages = shPage.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                result.addObject("pageNumbers", pageNumbers);
            }

            result.addObject("smallholdingPage", shPage);
            result.addObject("requestURI", "owner/smallholding/listOwnSmallholdings");
        } catch (Exception e){
            result = new ModelAndView("redirect:miscellaneous/error");
        }
        

        return result;
    }

    // Create

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView result;
        Smallholding smallholding;

        smallholding = this.smallholdingService.create();

        result = this.createEditModelAndView(smallholding);

        return result;
    }

    // Edit

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam final int smallholdingId) {
		ModelAndView result;
		Smallholding smallholding;

		try {
			smallholding = this.smallholdingService.findOneToEdit(smallholdingId);

			result = this.createEditModelAndView(smallholding);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:miscellaneous/error");
		}

		return result;
	}

    // Save

    @PostMapping(value = "/edit", params = "save")
    public ModelAndView save(Smallholding smallholding, BindingResult binding){
        ModelAndView result;
		Smallholding smallholdingRec;

		smallholdingRec = this.smallholdingService.reconstruct(smallholding, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(smallholding);
		else
			try {
				this.smallholdingService.save(smallholdingRec);
				result = new ModelAndView("redirect:/owner/smallholding/listOwnSmallholdings");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(smallholdingRec, "smallholding.commit.error");
			}

		return result;
    }
    
    // Delete

    @GetMapping("/deactivate")
    public ModelAndView deactivate(@RequestParam int smallholdingId) {
		ModelAndView result;
		Smallholding sh;

		sh = this.smallholdingService.findOneToEdit(smallholdingId);

		try {
			this.smallholdingService.deactivate(sh);

			result = new ModelAndView("redirect:/owner/smallholding/listOwnSmallholdings");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:miscellaneous/error");
		}

		return result;
	}

    // Ancillary methods

    protected ModelAndView createEditModelAndView(final Smallholding smallholding) {
		ModelAndView result;

		result = this.createEditModelAndView(smallholding, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Smallholding smallholding, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("smallholding/edit");
		result.addObject("smallholding", smallholding);
		result.addObject("messageCode", messageCode);

		return result;
	}

    
}