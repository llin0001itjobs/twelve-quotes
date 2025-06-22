package org.llin.demo.twelvequotes.controller;

import org.llin.demo.twelvequotes.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController extends Constants {

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("home/instruction"); 
    }
    
}