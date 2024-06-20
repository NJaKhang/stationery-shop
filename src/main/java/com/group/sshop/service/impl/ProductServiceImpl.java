package com.group.sshop.service.impl;

import com.group.sshop.models.entities.Product;
import com.group.sshop.repository.ProductRepository;
import com.group.sshop.repository.UserRepository;
import com.group.sshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> findPage(Pageable pageable) {
        System.out.println(pageable);
        return productRepository.findAll(pageable);
    }
}
