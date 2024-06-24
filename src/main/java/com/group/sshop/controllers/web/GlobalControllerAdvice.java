package com.group.sshop.controllers.web;

import com.group.sshop.models.domain.Cart;
import com.group.sshop.models.domain.CartItem;
import com.group.sshop.models.entities.Category;
import com.group.sshop.service.CartService;
import com.group.sshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final CategoryService categoryService;
    private final CartService cartService;

    @ModelAttribute("globalCategories")
    public List<Category> globalCategories() {
        return categoryService.findCategories();
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return cartService.getCart();
    }
}
