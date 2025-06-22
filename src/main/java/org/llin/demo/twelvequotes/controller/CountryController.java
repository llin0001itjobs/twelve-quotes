package org.llin.demo.twelvequotes.controller;

import org.llin.demo.twelvequotes.Constants;
import org.llin.demo.twelvequotes.model.AllQuotes;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.llin.demo.twelvequotes.util.QuotesRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/country")
public class CountryController<T extends SingleQuote> extends Constants {
	
	@Autowired
	private QuotesRetriever<T> quotesRetriever;
	
	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("country");
		AllQuotes<T> all_quotes = quotesRetriever.retrieve();
		session.setAttribute(ALL_QUOTES, all_quotes);
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/submit")
	public String handleFormSubmission(@RequestParam("selectedCountry") String selectedCountry,
												@RequestParam("submit") String submit,
												                   HttpSession session) {
		String redirect = "redirect:/";

		if (submit.equals("stockType")) {
			redirect = "redirect:/stockType/list";
		}
		
		if (submit.equals("listQuotes")) {
			redirect = "redirect:/quotes/list";
		}
		
		AllQuotes<T> all_quotes = (AllQuotes<T>) session.getAttribute(ALL_QUOTES);

		all_quotes.setSelectedCountry(selectedCountry);
		all_quotes.setSelectedType("");
		all_quotes.populateForOnlySelectedCountry();
		all_quotes.populateTypeSet();
		all_quotes.populateForOnlySelectedType();
		
		session.setAttribute(ALL_QUOTES, all_quotes);

		return redirect; // Redirect to a success page
	}
	
	

}