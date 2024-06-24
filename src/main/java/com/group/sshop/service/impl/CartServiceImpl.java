package com.group.sshop.service.impl;

import com.group.sshop.models.domain.Cart;
import com.group.sshop.models.domain.CartItem;
import com.group.sshop.models.entities.Product;
import com.group.sshop.repository.ProductRepository;
import com.group.sshop.service.CartService;
import com.group.sshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final List<CartItem> cartItems = new ArrayList<>();
    private final ProductService productService;

    @Override
    public Cart getCart() {
        List<CartItem> cartItems = this.cartItems;
        double total = cartItems.stream().map(cartItem -> cartItem.getProduct().getDropPrice()*cartItem.getQuantity()).reduce(0D, Double::sum);
        return new Cart(cartItems, total);
    }

    @Override
    public List<CartItem> getCartItem() {
        return List.of();
    }

    @Override
    public void add(Long productId, int quantity) {
        if (cartItems.stream().map(cartItem -> cartItem.getProduct().getId()).noneMatch(p -> p.equals(productId))) {
            Product product = productService.findById(productId);
            CartItem cartItem = new CartItem(product, quantity);
            cartItems.add(cartItem);
        } else {

            CartItem cartItem = cartItems.stream().filter(cartItem1 -> cartItem1.getProduct().getId().equals(productId)).findFirst().get();
            cartItem.setQuantity(quantity + cartItem.getQuantity());
        }
    }

    @Override
    public void remove(Long productId) {

    }

    @Override
    public void set(Long aLong, Integer integer) {

            CartItem cartItem = cartItems.stream().filter(cartItem1 -> cartItem1.getProduct().getId().equals(aLong)).findFirst().get();
            cartItem.setQuantity(integer);
    }
}
