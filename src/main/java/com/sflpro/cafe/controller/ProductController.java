package com.sflpro.cafe.controller;

import java.util.List;

import com.sflpro.cafe.model.jpa.Product;
import com.sflpro.cafe.model.jpa.User;
import com.sflpro.cafe.service.ProductService;
import com.sflpro.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/add-product")
    public ModelAndView addProduct() {

        ModelAndView modelAndView = new ModelAndView("addProduct");
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }

    @PostMapping("/add-product")
    public ModelAndView addProduct(@ModelAttribute Product product) {

        ModelAndView modelAndView = new ModelAndView("redirect:/home");

        productService.save(product);

        return modelAndView;
    }


//    @GetMapping("/product-list")
//    public ModelAndView listProduct() {
//
//        ModelAndView modelAndView = new ModelAndView("managerHome");
//
//        List<Product> productList = productService.getProducts();
//        modelAndView.addObject("products",productList );
//
//        return modelAndView;
//    }
}
