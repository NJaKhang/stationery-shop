package com.group.sshop.models.dto;

import lombok.Data;

@Data
public class CartForm {
    private Long productId;
    private int quantity;
}
