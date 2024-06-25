package com.group.sshop.service;

import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.Order;
import com.group.sshop.models.enums.OrderStatus;

public interface OrderService {
    DataTableResponse<OrderResponse> findAll(DataTableRequest dataTableRequest);

    Order findById(Long id);

    void confirmPayment(Long id);

    void setStatus(Long id, OrderStatus status);
}
