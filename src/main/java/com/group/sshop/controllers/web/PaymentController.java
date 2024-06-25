package com.group.sshop.controllers.web;

import com.group.sshop.models.domain.Principal;
import com.group.sshop.models.dto.CheckoutForm;
import com.group.sshop.models.entities.OrderDetails;
import com.group.sshop.models.entities.User;
import com.group.sshop.service.CartService;
import com.group.sshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/payment")
    public ModelAndView payment() {
        ModelAndView modelAndView = new ModelAndView("web/payment");
        modelAndView.addObject("cart", cartService.getCart());
        return modelAndView;
    }

    @PostMapping("/payment")
    public ModelAndView payment(@ModelAttribute CheckoutForm checkoutForm, Authentication authentication) {
        Principal principal = (Principal) authentication.getPrincipal();
        orderService.checkout(checkoutForm, cartService.getCart(), principal.getId());
        cartService.clear();
        return new ModelAndView("redirect:/");
    }
}
