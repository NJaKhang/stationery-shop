package com.group.sshop.models.entities;

import com.group.sshop.models.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
public class OrderDetails extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double price;
    private double discount;
    private int quantity;
    private OrderStatus status;


    public double getTotal() {
        return (price - discount) * quantity;
    }

    public double getSubTotal(){
        return price * quantity;
    }



}
