package com.group.sshop.models.entities;

import com.group.sshop.models.domain.Summary;
import com.group.sshop.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Setter
@Getter
public class Order extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shipping_details_id")
    private ShippingDetails shippingDetails;
    @ManyToOne
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User orderBy;

    private String code;


    public Summary getSummary() {
        var subtotal = getSubtotal();
        var productDiscount = getProductDiscount();
        var voucherDiscount = getVoucherDiscount();
        var shippingCost = getShippingCost();
        return Summary.builder()
                .voucherDiscount(voucherDiscount)
                .subtotal(subtotal + shippingCost)
                .shippingCost(shippingCost)
                .productDiscount(productDiscount)
                .itemSubTotal(subtotal)
                .totalDiscount(productDiscount + voucherDiscount)
                .total(subtotal + shippingCost - productDiscount - voucherDiscount)
                .voucherDescription(voucher != null ? voucher.getDescription() : null)
                .build();
    }

    public double getTotal() {
        return getSubtotal() - getProductDiscount() - getVoucherDiscount() + getShippingCost();
    }

    public double getShippingCost() {
        if (shippingDetails == null)
            return 0;
        return shippingDetails.getCost();

    }

    public double getSubtotal() {
        return orderDetails.stream().map(OrderDetails::getSubTotal).reduce(0d, Double::sum);
    }


    public double getTotalDiscount() {
        return getProductDiscount() + getVoucherDiscount();
    }

    public double getProductDiscount() {
        return orderDetails.stream().map(OrderDetails::getDiscount).reduce(0d, Double::sum);
    }

    public int getQuantity(){
        return orderDetails.stream().map(OrderDetails::getQuantity).reduce(0, Integer::sum);
    }

    public double getVoucherDiscount() {
        if (voucher == null)
            return 0;
        return voucher.getDiscount(getTotal());
    }
}
