package com.group.sshop.service.impl;

import com.group.sshop.models.dto.CategoryForm;
import com.group.sshop.models.entities.Category;
import com.group.sshop.models.entities.Image;
import com.group.sshop.repository.CategoryRepository;
import com.group.sshop.repository.ImageRepository;
import com.group.sshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void create(CategoryForm categoryForm) {
        Image image = new Image();
        image.setUrl(categoryForm.getImageUrl());
        image = imageRepository.saveAndFlush(image);

        Category category = new Category();
        category.setName(categoryForm.getName());
        category.setAlias(categoryForm.getAlias());
        category.setImage(image);
        categoryRepository.save(category);
    }
    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
