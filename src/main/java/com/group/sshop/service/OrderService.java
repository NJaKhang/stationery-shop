package com.group.sshop.service;

import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.Order;

public interface OrderService {
    DataTableResponse<OrderResponse> findAll(DataTableRequest dataTableRequest);

    Order findById(Long id);

    void confirmPayment(Long id);
}
