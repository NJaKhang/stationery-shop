package com.group.sshop.repository;

import com.group.sshop.models.entities.Order;
import com.group.sshop.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAll(Specification<Order> trackSpecification, Pageable pageable);

}
