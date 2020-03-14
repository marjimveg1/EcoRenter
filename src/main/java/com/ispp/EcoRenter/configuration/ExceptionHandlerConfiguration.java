package com.ispp.EcoRenter.configuration;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfiguration {
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ModelAndView notFound(NoHandlerFoundException oops)  {
		ModelAndView result;
		
		result = new ModelAndView("miscellaneous/error");
		result.addObject("name", "Illo que esto no eziste");
		result.addObject("exception", "Illo que esto no eziste");
		result.addObject("stackTrace", "Illo que esto no eziste");
		
		return result;
	}
	
	
//	@ExceptionHandler(Throwable.class)
//	public ModelAndView defaultErrorHandler(Throwable oops)  {
//		ModelAndView result;
//		
//		result = new ModelAndView("miscellaneous/error");
//		result.addObject("name", oops.getClass());
//		result.addObject("exception", oops.getMessage());
//		result.addObject("stackTrace", oops.getStackTrace());
//		
//		return result;
//	}

}