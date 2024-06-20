package com.group.sshop.service.impl;

import com.group.sshop.models.entities.Category;
import com.group.sshop.repository.CategoryRepository;
import com.group.sshop.repository.ProductRepository;
import com.group.sshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
