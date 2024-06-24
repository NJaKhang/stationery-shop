package com.group.sshop.models.dto;

import com.group.sshop.models.entities.Product;
import com.group.sshop.models.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class LineProductResponse {
    private String name;
    private Long id;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private Double cost;
    private ProductStatus status;
    private int quantity;
    private int sold;
    private String sku;
    private String createdAt;
    private String thumbnail;

    public static LineProductResponse map(Product product) {
        return LineProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sold(product.getRecord().getSale())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .finalPrice(product.getPrice() - product.getDiscount())
                .cost(product.getCost())
                .status(product.getStatus())
                .sku(product.getSku())
                .quantity(product.getRecord().getStock())
                .createdAt(new SimpleDateFormat("dd/MM/yyyy").format(new Date(product.getCreatedAt().getTime())))
                .thumbnail(product.getThumbnail().getUrl())
                .build();
    }
}
