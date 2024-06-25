package com.group.sshop.controllers.web;

import com.group.sshop.models.entities.Category;
import com.group.sshop.models.entities.Product;
import com.group.sshop.service.CategoryService;
import com.group.sshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/")
    public ModelAndView index() {
        List<Category> categories = categoryService.findAll().subList(1, 6);
        Page<Product> products = productService.findPage(PageRequest.of(0, 10, Sort.Direction.DESC,"record.sold")); ;
        ModelAndView modelAndView = new ModelAndView("web/home");
        modelAndView.addObject("title", "Trang chủ");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
