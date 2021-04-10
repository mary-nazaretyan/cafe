package com.sflpro.cafe.controller;

import com.sflpro.cafe.model.jpa.User;
import com.sflpro.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/add-user")
    public ModelAndView addUser() {

        ModelAndView modelAndView = new ModelAndView("addUser");
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/add-user")
    public ModelAndView addUser(User user) {

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        User userFromDB = userService.getUser(user.getUsername());
        if (userFromDB != null) {
            modelAndView.setViewName("error");
        }
        userService.addUser(user);

        return modelAndView;
    }
}
