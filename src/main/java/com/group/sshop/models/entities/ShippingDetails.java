package com.group.sshop.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "shipping_details")
@Entity
@Getter
@Setter
public class ShippingDetails extends AbstractEntity{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private double cost;
    private String provider;
    private String phoneNumber;
    private String receiver;
}
