package com.group.sshop.service.impl;

import com.group.sshop.exception.ResourceNotFoundException;
import com.group.sshop.models.dto.LineProductResponse;
import com.group.sshop.models.dto.ProductForm;
import com.group.sshop.models.dto.datatable.DataTableRequest;
import com.group.sshop.models.dto.datatable.DataTableResponse;
import com.group.sshop.models.entities.*;
import com.group.sshop.repository.*;
import com.group.sshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ProducerRepository producerRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRecordRepository productRecordRepository;

    @Override
    public void create(ProductForm productForm) {

        Category category = categoryRepository.findById(productForm.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Not found category by id " + productForm.getCategoryId()));
        Producer producer = null;
        if (productForm.getProviderId() != null)
            producer = producerRepository.findById(productForm.getProviderId()).orElseThrow(() -> new ResourceNotFoundException("Not found category by id " + productForm.getCategoryId()));
        List<Tag> tags = tagRepository.findAllById(productForm.getTagIds());
        Image image = new Image();
        image.setUrl(productForm.getImageUrl());
        image.setAlt(productForm.getName());

        Product product = Product.builder()
                .shortDescription(productForm.getShortDescription())
                .description(productForm.getDescription())
                .discount(productForm.getDiscount())
                .status(productForm.getStatus())
                .alias(productForm.getAlias())
                .price(productForm.getPrice())
                .name(productForm.getName())
                .cost(productForm.getCost())
                .sku(productForm.getSku())
                .producer(producer)
                .category(category)
                .thumbnail(image)
                .tags(tags)
                .build();
        productRepository.save(product);

        ProductRecord productRecord = new ProductRecord();
        productRecord.setProduct(product);
        productRecord.setId(product.getId());
        productRecordRepository.save(productRecord);


    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public DataTableResponse<LineProductResponse> findAll(DataTableRequest dataTableRequest) {
        Page<Product> page = productRepository.findAll(getSerchSpecification(dataTableRequest.getSearch().getValue()), dataTableRequest.getPageable());
        List<LineProductResponse> products = page.map(LineProductResponse::map).stream().toList();


        return DataTableResponse.<LineProductResponse>builder()
                .draw(dataTableRequest.getDraw())
                .recordsFiltered(page.getTotalElements())
                .recordsTotal(productRepository.count())
                .data(products)
                .build();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not fount product"));
    }

    @Override
    public void update(Long id, ProductForm productForm) {
        Product product = findById(id);
        Image image = product.getThumbnail();
        if (!image.getUrl().equals(productForm.getImageUrl())) {
            image = new Image();
            image.setUrl(productForm.getImageUrl());
            image.setAlt(productForm.getName());
            product.setThumbnail(image);
        }

        Category category = categoryRepository.findById(productForm.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Not found category by id " + productForm.getCategoryId()));
        Producer producer = null;
        if (productForm.getProviderId() != null)
            producer = producerRepository.findById(productForm.getProviderId()).orElseThrow(() -> new ResourceNotFoundException("Not found category by id " + productForm.getCategoryId()));
        List<Tag> tags = tagRepository.findAllById(productForm.getTagIds());

        product.setCategory(category);
        product.setCost(productForm.getCost());
        product.setDescription(productForm.getDescription());
        product.setDiscount(productForm.getDiscount());
        product.setSku(productForm.getSku());
        product.setAlias(productForm.getAlias());
        product.setDiscount(product.getDiscount());
        product.setStatus(productForm.getStatus());
        product.setProducer(producer);
        product.setName(productForm.getName());
        product.setTags(tags);
        product.setPrice(productForm.getPrice());
        product.setShortDescription(product.getShortDescription());
        productRepository.save(product);

    }

    @Override
    public void deleted(Long id) {
        Product product = findById(id);
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public Page<Product> findPage(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    private Specification<Product> getSerchSpecification(String key) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isEmpty(key))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("sku"), "%" + key + "%"),
                    criteriaBuilder.like(root.<Long>get("id").as(String.class), "%" + key + "%"),
                    criteriaBuilder.like(root.get("category").get("name"), "%" + key + "%")
            );
        };

    }


}
