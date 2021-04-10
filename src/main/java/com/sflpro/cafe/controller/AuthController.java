package com.sflpro.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @GetMapping("/sign-in")
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

    @GetMapping("/sign-out")
    public ModelAndView signOut(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return new ModelAndView("redirect:/home");
    }
}
