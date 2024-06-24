package com.group.sshop.models.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cart {
    private List<CartItem> items;
    private double total;

    public double getTotalWithShip() {
        return total + 45000.0;
    }
}
