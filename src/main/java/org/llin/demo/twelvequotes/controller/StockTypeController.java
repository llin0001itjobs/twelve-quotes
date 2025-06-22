package org.llin.demo.twelvequotes.controller;

import org.llin.demo.twelvequotes.Constants;
import org.llin.demo.twelvequotes.model.AllQuotes;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/stockType")
public class StockTypeController<T extends SingleQuote> extends Constants {
	public static final String ALL_QUOTES = "ALL_QUOTES";

	@SuppressWarnings("unchecked")
	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("stockType");
		
		modelAndView.addObject(ALL_QUOTES, (AllQuotes<T>) session.getAttribute(ALL_QUOTES));	

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/submit")
	public String handleFormSubmission(@RequestParam("selectedType") String selectedType,
											 @RequestParam("submit") String submit,
												                   HttpSession session) {
		String redirect = "redirect:/country/list";

		if (submit.equals("submit")) {
			redirect = "redirect:/quotes/list";
		}
		
		AllQuotes<T> all_quotes = (AllQuotes<T>) session.getAttribute(ALL_QUOTES);

		all_quotes.setSelectedType(selectedType); 
		all_quotes.populateForOnlySelectedType();
		session.setAttribute(ALL_QUOTES, all_quotes);

		return redirect; // Redirect to a success page
	}
	
	

}