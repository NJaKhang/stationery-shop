package com.group.sshop.models.domain;

import com.group.sshop.models.entities.Product;
import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private int quantity;


}
