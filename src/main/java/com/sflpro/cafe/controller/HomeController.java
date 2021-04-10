package com.sflpro.cafe.controller;

import com.sflpro.cafe.config.CustomUserDetails;
import com.sflpro.cafe.model.enums.UserRole;
import com.sflpro.cafe.service.OrderService;
import com.sflpro.cafe.service.ProductService;
import com.sflpro.cafe.service.TableService;
import com.sflpro.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProductService productService;
    private final TableService tableService;
    private final OrderService orderService;

    @GetMapping("/home")
    public ModelAndView home(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails.getRole() == UserRole.MANAGER) {
            return managerHome(customUserDetails);
        } else {
            return waiterHome(customUserDetails);
        }

    }

    private ModelAndView managerHome(CustomUserDetails customUserDetails) {
        ModelAndView modelAndView = new ModelAndView("managerHome");

        modelAndView.addObject("role", customUserDetails.getRole());
        modelAndView.addObject("username", customUserDetails.getUsername());
        modelAndView.addObject("products", productService.getProducts());
        modelAndView.addObject("waiters", userService.getAllWaiters());
        modelAndView.addObject("tables", tableService.getAllTables());

        return modelAndView;
    }

    private ModelAndView waiterHome(CustomUserDetails customUserDetails) {
        ModelAndView modelAndView = new ModelAndView("waiterHome");

        modelAndView.addObject("role", customUserDetails.getRole());
        modelAndView.addObject("username", customUserDetails.getUsername());

        long waiterId = customUserDetails.getId();
        modelAndView.addObject("tables", tableService.getTablesByWaiterId(waiterId));
        modelAndView.addObject("orders", orderService.getOrdersByWaiterId(waiterId));
        return modelAndView;
    }

}
