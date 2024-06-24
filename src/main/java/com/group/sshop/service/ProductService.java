package com.group.sshop.service;


import com.group.sshop.models.dto.LineProductResponse;
import com.group.sshop.models.dto.ProductForm;

import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    void create(ProductForm productForm);

    Page<Product> findAll(Pageable pageable);


    DataTableResponse<LineProductResponse> findAll(DataTableRequest dataTableRequest);

    Product findById(Long id);

    void update(Long id, ProductForm productForm);
}
