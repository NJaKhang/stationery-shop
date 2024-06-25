package com.group.sshop.models.domain;

import com.group.sshop.models.entities.Voucher;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Summary {
    private double total;
    private double itemSubTotal;
    private double subtotal;
    private double totalDiscount;
    private double productDiscount;
    private double voucherDiscount;
    private double shippingCost;
    private String voucherDescription;
    private String shippingDescription;
}
