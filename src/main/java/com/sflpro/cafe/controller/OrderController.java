package com.sflpro.cafe.controller;

import com.sflpro.cafe.config.CustomUserDetails;
import com.sflpro.cafe.model.dto.CafeOrderDTO;
import com.sflpro.cafe.model.enums.OrderStatus;
import com.sflpro.cafe.model.jpa.CafeOrder;
import com.sflpro.cafe.service.OrderService;
import com.sflpro.cafe.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final TableService tableService;

    @GetMapping("/add-order")
    public ModelAndView addOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        ModelAndView modelAndView = new ModelAndView("addOrder");
        modelAndView.addObject("order", new CafeOrderDTO());
        modelAndView.addObject("orderStatuses", OrderStatus.values());
        modelAndView.addObject("tables", tableService.getTablesByWaiterId(customUserDetails.getId()));

        return modelAndView;
    }

    @PostMapping("/add-order")
    public ModelAndView addOrder(@ModelAttribute CafeOrderDTO order) {

        ModelAndView modelAndView = new ModelAndView("redirect:/home");

        orderService.save(order);

        return modelAndView;
    }

    ///////////////////////

    @GetMapping("/edit-order/{id}")
    public ModelAndView editOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @PathVariable("id") long id) {

        ModelAndView modelAndView = new ModelAndView("editOrder");
        final CafeOrder order = orderService.getOrderById(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("orderStatuses", OrderStatus.values());

        return modelAndView;
    }

    @PostMapping("/edit-order/{id}")
    public ModelAndView editOrder(@ModelAttribute CafeOrder order, @PathVariable("id") long id) {



        orderService.save(order, id);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");


        return modelAndView;
    }

}
