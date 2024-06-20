package com.group.sshop.service;

import com.group.sshop.models.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findCategories();
}
