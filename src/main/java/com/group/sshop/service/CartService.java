package com.group.sshop.service;

import com.group.sshop.models.domain.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItem();

    void add(Long productId, int quantity);

    void remove(Long productId);

}
