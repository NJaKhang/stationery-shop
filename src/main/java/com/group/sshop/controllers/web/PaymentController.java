package com.group.sshop.controllers.web;

import com.group.sshop.models.entities.OrderDetails;
import com.group.sshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final CartService cartService;

    @GetMapping("/payment")
    public ModelAndView payment() {
        ModelAndView modelAndView = new ModelAndView("web/payment");
        modelAndView.addObject("cart", cartService.getCart());
        return modelAndView;
    }

    @PostMapping("/payment")
    public ModelAndView payment(@ModelAttribute OrderDetails orderDetails) {
        ModelAndView modelAndView = new ModelAndView("redirect:/payment");
        modelAndView.addObject("cart", cartService.getCart());
        return modelAndView;
    }
}
