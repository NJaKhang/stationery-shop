package com.group.sshop.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {

    @NotBlank
    private String name;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String alias;
}
