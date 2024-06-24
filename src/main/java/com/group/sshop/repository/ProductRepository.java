package com.group.sshop.repository;

import com.group.sshop.models.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;





public interface ProductRepository extends JpaRepository<Product, Long> {
}
