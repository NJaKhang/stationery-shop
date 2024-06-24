package com.group.sshop.models.dto;

import com.group.sshop.models.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductForm {
    @NotBlank
    private String name;
    @NotBlank
    private String alias;
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Double discount;
    @NotNull
    private Double cost;

    @NotNull
    private Long categoryId;
    private List<Long> tagIds = List.of();
    private String imageUrl = "https://t3.ftcdn.net/jpg/04/60/01/36/360_F_460013622_6xF8uN6ubMvLx0tAJECBHfKPoNOR5cRa.jpg";

    private Long providerId;

    @NotNull
    private ProductStatus status = ProductStatus.AVAILABLE;

    @NotBlank
    private String sku;

}
