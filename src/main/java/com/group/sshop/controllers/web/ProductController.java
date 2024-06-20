package com.group.sshop.controllers.web;

import com.group.sshop.models.entities.Category;
import com.group.sshop.models.entities.Product;
import com.group.sshop.service.CategoryService;
import com.group.sshop.service.ProductService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public ModelAndView showProduct(Pageable pageable) {
        Page<Product> products = productService.findPage(pageable);
        List<Category> categories = categoryService.findCategories();
        ModelAndView modelAndView = new ModelAndView("web/products-grid");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
