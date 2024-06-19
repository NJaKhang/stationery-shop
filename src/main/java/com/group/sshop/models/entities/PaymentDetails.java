package com.group.sshop.models.entities;

import com.group.sshop.models.enums.PaymentStatus;
import com.group.sshop.models.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
public class PaymentDetails extends AbstractEntity {
    protected PaymentStatus status;
    protected double amount;
    protected String currency;
    protected String provider;
    protected String transactionId;
    protected PaymentMethod method;
    protected Timestamp paymentAt;
}
