package com.group.sshop.models.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleVoucher extends Voucher {
    private double discount;

    @Override
    public Double getDiscount(double total) {
        if (total < min)
            return null;

        return discount;
    }
}
