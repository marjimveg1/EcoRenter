package com.ispp.EcoRenter.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ispp.EcoRenter.model.Actor;
import com.ispp.EcoRenter.model.Comment;
import com.ispp.EcoRenter.model.Renter;
import com.ispp.EcoRenter.model.Smallholding;
import com.ispp.EcoRenter.service.ActorService;
import com.ispp.EcoRenter.service.CommentService;
import com.ispp.EcoRenter.service.SmallholdingService;

@Controller
@RequestMapping("/smallholding")
public class SmallholdingController {

	// Services

	@Autowired
	private SmallholdingService smallholdingService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ActorService actorService;

	// Constructor

	public SmallholdingController() {
		super();
	}

	// List

	@GetMapping("/list")
	public ModelAndView list(@RequestParam("page") final Optional<Integer> page,
			@RequestParam("size") final Optional<Integer> size) {
		ModelAndView result;
		Collection<Smallholding> smallholdings;
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(4);

		try {
			result = new ModelAndView("smallholding/list");

			smallholdings = this.smallholdingService.findSmallholdingsAvailables();
			Page<Smallholding> shPage = this.smallholdingService
					.findPaginated(PageRequest.of(currentPage - 1, pageSize), smallholdings);
			int totalPages = shPage.getTotalPages();

			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				result.addObject("pageNumbers", pageNumbers);
			}

			result.addObject("smallholdingPage", shPage);
			result.addObject("requestURI", "smallholding/list");
		} catch (Exception e) {
			result = new ModelAndView("redirect:miscellaneous/error");
		}

		return result;
	}

	@GetMapping("/display")
	public ModelAndView display(@RequestParam final int smallholdingId) {
		ModelAndView result;
		Smallholding smallholding;
		Collection<Comment> comments;
		Actor principal;
		Boolean isRentedByRenter;

		isRentedByRenter = null;
		try {
			principal = this.actorService.findByPrincipal();
			if (principal instanceof Renter) {
				isRentedByRenter = this.smallholdingService.isSmallholdingRentedByRenter(principal.getId(),
						smallholdingId);
			}
		} catch (Exception e) {
			isRentedByRenter = null;
		}

		try {
			smallholding = this.smallholdingService.findOneToDisplay(smallholdingId);
			comments = this.commentService.findCommentsBySmallholdingId(smallholdingId);

			result = new ModelAndView("smallholding/display");
			result.addObject("smallholding", smallholding);
			result.addObject("comments", comments);
			result.addObject("isRentedByRenter", isRentedByRenter);
		} catch (Exception e) {
			result = new ModelAndView("redirect:miscellaneous/error");
		}

		return result;
	}

}