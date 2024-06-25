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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.StringJoiner;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public ModelAndView showProducts(Pageable pageable) {
        Page<Product> products = productService.findPage(pageable);
        List<Category> categories = categoryService.findCategories();
        StringJoiner joiner = new StringJoiner(",");
        products.getSort().get().forEach(order -> {
            joiner.add(order.getProperty());
            joiner.add(order.getDirection().toString());

        });

        ModelAndView modelAndView = new ModelAndView("web/products-grid");
        modelAndView.addObject("title", "Sản phẩm");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        modelAndView.addObject("sortParam", joiner.toString());
        return modelAndView;
    }

    @GetMapping("/products/category/{category}")
    public ModelAndView showProductsByCategory(Pageable pageable, @PathVariable String category) {
        Page<Product> products = productService.findPageByCategory(pageable, category);
        List<Category> categories = categoryService.findCategories();
        StringJoiner joiner = new StringJoiner(",");
        products.getSort().get().forEach(order -> {
            joiner.add(order.getProperty());
            joiner.add(order.getDirection().toString());

        });

        ModelAndView modelAndView = new ModelAndView("web/products-grid");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        modelAndView.addObject("sortParam", joiner.toString());
        modelAndView.addObject("title", "Sản phẩm");
        return modelAndView;
    }


    @GetMapping("/products/{alias}")
    public ModelAndView showDetail(@PathVariable String alias) {
        ModelAndView productDetail = new ModelAndView("web/product-details");
        Product product = productService.findByAlias(alias);
        productDetail.addObject("title", product.getName());
        productDetail.addObject("product", product);

        return productDetail;
    }

}
