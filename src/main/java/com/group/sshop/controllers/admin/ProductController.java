package com.group.sshop.controllers.admin;

import com.group.sshop.models.dto.ProductForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    @GetMapping("/form")
    public ModelAndView productForm(ProductForm productForm){
        return new ModelAndView("admin/product-form");
    }

    @PostMapping
    public ModelAndView submit(@Valid ModelAttribute productForm, BindingResult result){
        return new ModelAndView("admin/products");
    }
}
