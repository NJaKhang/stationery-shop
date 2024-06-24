package com.group.sshop.service;

import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;

public interface OrderService {
    DataTableResponse<OrderResponse> findAll(DataTableRequest dataTableRequest);
}
