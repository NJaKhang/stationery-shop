package com.group.sshop.models.dto;

import com.group.sshop.models.domain.Summary;
import com.group.sshop.models.entities.Order;
import com.group.sshop.models.entities.OrderDetails;
import com.group.sshop.models.enums.OrderStatus;
import com.group.sshop.models.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import org.hibernate.Hibernate;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Summary summary;
    private int quantity;
    private int numberOfProduct;
    private String customerName;
    private PaymentStatus paymentStatus;
    private String email;
    private String code;
    private OrderStatus status;

    public static OrderResponse map(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .code(order.getCode())
                .numberOfProduct(Hibernate.size(order.getOrderDetails()))
                .quantity(order.getOrderDetails().stream().map(OrderDetails::getQuantity).reduce(0, Integer::sum))
                .summary(order.getSummary())
                .status(order.getStatus())
                .customerName(order.getOrderBy().getFullName())
                .email(order.getOrderBy().getEmail())
                .paymentStatus(order.getPaymentDetails().getStatus())
                .build();
    }

}
