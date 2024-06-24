package com.group.sshop.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    @GetMapping("/cart")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/cart");
        return modelAndView;
    }
}
