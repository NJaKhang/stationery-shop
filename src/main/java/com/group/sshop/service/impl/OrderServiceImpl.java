package com.group.sshop.service.impl;

import com.group.sshop.exception.ResourceNotFoundException;
import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.Order;
import com.group.sshop.models.entities.PaymentDetails;
import com.group.sshop.models.enums.PaymentStatus;
import com.group.sshop.repository.OrderRepository;
import com.group.sshop.repository.PaymentRepository;
import com.group.sshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public DataTableResponse<OrderResponse> findAll(DataTableRequest dataTableRequest) {
        Page<Order> page = orderRepository.findAll(getSerchSpecification(dataTableRequest.getSearch().getValue()), dataTableRequest.getPageable());
        List<OrderResponse> products = page.map(OrderResponse::map).stream().toList();
        return DataTableResponse.<OrderResponse>builder()
                .draw(dataTableRequest.getDraw())
                .recordsFiltered(page.getTotalElements())
                .recordsTotal(orderRepository.count())
                .data(products)
                .build();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ResourceNotFoundException"));
    }

    @Override
    public void confirmPayment(Long id) {
        Order order = findById(id);
        if(order.getPaymentDetails().getStatus() != PaymentStatus.WAITING){
            return;
        }

        PaymentDetails paymentDetails = order.getPaymentDetails();
        paymentDetails.setAmount(order.getTotal());
        paymentDetails.setStatus(PaymentStatus.DONE);
        paymentRepository.save(paymentDetails);
    }

    private Specification<Order> getSerchSpecification(String key) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(key))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("orderBy").get("fullName"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("code").as(String.class), "%" + key + "%")
            );
        };

    }
}
