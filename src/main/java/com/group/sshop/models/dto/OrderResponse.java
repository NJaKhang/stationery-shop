package com.group.sshop.models.dto;

import com.group.sshop.models.domain.Summary;
import com.group.sshop.models.entities.Order;
import com.group.sshop.models.entities.OrderDetails;
import com.group.sshop.models.enums.OrderStatus;
import com.group.sshop.models.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import org.hibernate.Hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String orderDate;

    public static OrderResponse map(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .code(order.getCode())
                .numberOfProduct(Hibernate.size(order.getOrderDetails()))
                .quantity(order.getOrderDetails().stream().map(OrderDetails::getQuantity).reduce(0, Integer::sum))
                .summary(order.getSummary())
                .status(order.getStatus())
                .customerName(order.getOrderBy().getFullName())
                .orderDate(new SimpleDateFormat("dd/MM/yyy").format(new Date(order.getCreatedAt().getTime())))
                .email(order.getOrderBy().getEmail())
                .paymentStatus(order.getPaymentDetails().getStatus())
                .build();
    }

}
