package com.group.sshop.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class RateVoucher extends Voucher{

    private double rate = 0;
    @Override
    public Double getDiscount(double total) {
        if(min != -1 && total < min)
            return null;
        double discount = total * rate;
        if(max == -1 || discount <= max)
            return discount;
        else
            return max;
    }
}
