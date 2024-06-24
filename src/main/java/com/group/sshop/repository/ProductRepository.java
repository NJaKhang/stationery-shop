package com.group.sshop.repository;

import com.group.sshop.models.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByAlias(String alias);
}
