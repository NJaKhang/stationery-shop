package com.group.sshop.controllers.admin;

import com.group.sshop.models.dto.ProductForm;
import com.group.sshop.models.entities.Product;
import com.group.sshop.models.entities.Tag;
import com.group.sshop.service.CategoryService;
import com.group.sshop.service.ProducerService;
import com.group.sshop.service.ProductService;
import com.group.sshop.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final CategoryService categoryService;
    private final TagService tagService;
    private final ProductService productService;
    private final ProducerService producerService;


    @GetMapping("/form")
    public ModelAndView productForm(ProductForm productForm) {

        ModelAndView modelAndView = new ModelAndView("admin/product-form");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("tags", tagService.findAll());
        modelAndView.addObject("producers", producerService.findAll());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView submit(@Valid @ModelAttribute ProductForm productForm, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/product-form");
            modelAndView.addObject("categories", categoryService.findAll());
            modelAndView.addObject("tags", tagService.findAll());
            modelAndView.addObject("producers", producerService.findAll());
            modelAndView.addObject(productForm);
            return modelAndView;
        }
        productService.create(productForm);
        return new ModelAndView("redirect:/admin/products");
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("admin/products");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit( ProductForm productForm, @PathVariable Long id) {
        Product product = productService.findById(id);
        productForm.setAlias(product.getAlias());
        productForm.setName(product.getName());
        productForm.setCost(product.getCost());
        productForm.setDiscount(product.getDiscount());
        productForm.setSku(product.getSku());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setStatus(product.getStatus());
        productForm.setCategoryId(product.getCategory().getId());
        productForm.setImageUrl(product.getThumbnail().getUrl());
        productForm.setTagIds(product.getTags().stream().map(Tag::getId).toList());
        productForm.setStatus(product.getStatus());
        productForm.setShortDescription(product.getShortDescription());


        ModelAndView modelAndView = new ModelAndView("admin/product-edit");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("tags", tagService.findAll());
        modelAndView.addObject("producers", producerService.findAll());
        modelAndView.addObject(id);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@Valid @ModelAttribute ProductForm productForm, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/product-edit");
            modelAndView.addObject("categories", categoryService.findAll());
            modelAndView.addObject("tags", tagService.findAll());
            modelAndView.addObject("producers", producerService.findAll());
            return modelAndView;
        } else {
            productService.update(id, productForm);
            return new ModelAndView("redirect:/admin/products");
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        productService.deleted(id);
        return new ModelAndView("redirect:/admin/products");
    }

}
