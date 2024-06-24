package com.group.sshop.models.domain;

import com.group.sshop.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;
}
