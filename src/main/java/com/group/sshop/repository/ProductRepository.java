package com.group.sshop.repository;

import com.group.sshop.models.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByAlias(String alias);

    @Query("SELECT p from Product p where p.category.alias = :categoryAlias")
    Page<Product> findByCategory(Pageable pageable, String categoryAlias);
}
