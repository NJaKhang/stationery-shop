package com.group.sshop.repository;

import com.group.sshop.models.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Specification<Product> trackSpecification, Pageable pageable);

    Optional<Product> findByAlias(String alias);
}
