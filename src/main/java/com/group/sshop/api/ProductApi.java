package com.group.sshop.api;

import com.group.sshop.models.dto.LineProductResponse;

import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @PostMapping
    private DataTableResponse<LineProductResponse> findAll(@RequestBody DataTableRequest dataTableRequest){
        System.out.println(dataTableRequest);
        return productService.findAll(dataTableRequest);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        productService.deleted(id);
        return ResponseEntity.noContent().build();
    }
}
