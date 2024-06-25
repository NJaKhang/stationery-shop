package com.group.sshop.controllers.web;

import com.group.sshop.models.domain.Cart;
import com.group.sshop.models.dto.CartForm;
import com.group.sshop.models.dto.FullCartForm;
import com.group.sshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public ModelAndView cart(Cart cart) {
        ModelAndView modelAndView = new ModelAndView("/web/cart");
        modelAndView.addObject("title", "Giỏ hàng");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @PostMapping("/cart")
    public ModelAndView cart(CartForm cartForm) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        cartService.add(cartForm.getProductId(), cartForm.getQuantity());
        return modelAndView;
    }


    @PostMapping("/full-cart")
    public ModelAndView cart( FullCartForm fullCart) {
        System.out.println(fullCart);
        ModelAndView modelAndView = new ModelAndView("redirect:/cart");
        fullCart.getQuantities().forEach(cartService::set);
        return modelAndView;
    }

}
