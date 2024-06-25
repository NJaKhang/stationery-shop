package com.group.sshop.api;

import com.group.sshop.models.dto.LineProductResponse;
import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderApi {
    private final OrderService orderService;

    @PostMapping
    private DataTableResponse<OrderResponse> findAll(@RequestBody DataTableRequest dataTableRequest){
        System.out.println(dataTableRequest);
        return orderService.findAll(dataTableRequest);
    }
}
