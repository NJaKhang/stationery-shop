package com.group.sshop.service.impl;

import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.Order;
import com.group.sshop.repository.OrderRepository;
import com.group.sshop.repository.OrderRepository;
import com.group.sshop.service.OrderService;
import com.group.sshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public DataTableResponse<OrderResponse> findAll(DataTableRequest dataTableRequest) {
        Page<Order> page = orderRepository.findAll(dataTableRequest.getPageable());
        List<OrderResponse> products = page.map(OrderResponse::map).stream().toList();
        return DataTableResponse.<OrderResponse>builder()
                .draw(dataTableRequest.getDraw())
                .recordsFiltered(page.getTotalElements())
                .recordsTotal(orderRepository.count())
                .data(products)
                .build();
    }
}
