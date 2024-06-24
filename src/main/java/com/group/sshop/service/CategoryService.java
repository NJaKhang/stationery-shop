package com.group.sshop.service;

import com.group.sshop.models.dto.CategoryForm;
import com.group.sshop.models.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    void create(CategoryForm categoryForm);

    List<Category> findCategories();
}
