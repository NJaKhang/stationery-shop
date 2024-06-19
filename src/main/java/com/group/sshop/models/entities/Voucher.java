package com.group.sshop.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Table(name = "vouchers")
@Inheritance(strategy = SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class Voucher extends AbstractEntity {
    protected String code;
    protected int quantity;
    protected Date expiryDate;
    protected double max = -1;
    protected double min = -1;
    private String description;

    public abstract Double getDiscount(double total);
}
