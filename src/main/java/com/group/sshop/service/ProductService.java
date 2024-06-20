package com.group.sshop.service;


import com.group.sshop.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findPage(Pageable pageable);
}
