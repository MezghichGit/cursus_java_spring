package com.sip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.sip.entities.User;
import com.sip.services.UserService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
    private UserService userService;

    @GetMapping("/home")
    public String  home(Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("User authenticated : "+auth.getName());
   
        User user = userService.findUserByEmail(auth.getName());
    	System.out.println("User roles : "+user.getAuthorities());
    	System.out.println("isAccountNonLocked : "+user.isAccountNonLocked());
    	
        model.addAttribute("userName","Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "home";


    }

}