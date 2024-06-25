package com.group.sshop.service.impl;

import com.group.sshop.exception.ResourceNotFoundException;
import com.group.sshop.models.domain.Cart;
import com.group.sshop.models.dto.CheckoutForm;
import com.group.sshop.models.dto.OrderResponse;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.*;
import com.group.sshop.models.enums.OrderStatus;
import com.group.sshop.models.enums.PaymentMethod;
import com.group.sshop.models.enums.PaymentStatus;
import com.group.sshop.repository.OrderRepository;
import com.group.sshop.repository.PaymentRepository;
import com.group.sshop.repository.ProductRepository;
import com.group.sshop.repository.UserRepository;
import com.group.sshop.service.OrderService;
import com.group.sshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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

    @Override
    public void checkout(CheckoutForm checkoutForm, Cart cart, Long userId) {
        Address address = new Address();
        address.setDescription(checkoutForm.getAddress());
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setAddress(address);
        shippingDetails.setReceiver(checkoutForm.getReceiver());
        shippingDetails.setCost(45000);
        shippingDetails.setPhoneNumber(checkoutForm.getPhoneNumber());
        shippingDetails.setProvider("VN POST");
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCurrency("VND");
        paymentDetails.setMethod(checkoutForm.getPaymentMethod());
        paymentDetails.setStatus(PaymentStatus.WAITING);
        paymentDetails.setAmount(0);
        Order order = new Order();
        User user = userRepository.findById(userId).orElse(null);
        order.setOrderBy(user);
        order.setCode(Math.floor(Math.random() * 100000000) + "");
        order.setPaymentDetails(paymentDetails);
        order.setStatus(OrderStatus.WAIT);
        order.setShippingDetails(shippingDetails);
        order = orderRepository.save(order);

        for(var cartItem : cart.getItems()){
            OrderDetails o = new OrderDetails();
            o.setPrice(cartItem.getProduct().getPrice());
            o.setDiscount(cartItem.getProduct().getDiscount());
            o.setQuantity(cartItem.getQuantity());
            o.setStatus(OrderStatus.DONE);
            o.setOrder(order);
            orderRepository.save(order);
            int sold = cartItem.getProduct().getRecord().getSold() + cartItem.getQuantity();
            cartItem.getProduct().getRecord().setSold(sold);
            productRepository.save(cartItem.getProduct());
        }






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
