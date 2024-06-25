package com.group.sshop.models.dto;

import com.group.sshop.models.enums.PaymentMethod;
import lombok.Data;

@Data
public class CheckoutForm {
    private String address;
    private String receiver;
    private String phoneNumber;
    private PaymentMethod paymentMethod;
}
