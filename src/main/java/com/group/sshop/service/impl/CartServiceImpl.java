package com.group.sshop.service.impl;

import com.group.sshop.models.domain.CartItem;
import com.group.sshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public List<CartItem> getCartItem() {
        return List.of();
    }

    @Override
    public void add(Long productId, int quantity) {

    }

    @Override
    public void remove(Long productId) {

    }
}
