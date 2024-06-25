package com.group.sshop.controllers.admin;

import com.group.sshop.models.dto.CategoryForm;
import com.group.sshop.models.entities.Category;
import com.group.sshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@AllArgsConstructor
@RequestMapping("/admin/product-categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ModelAndView index(CategoryForm categoryForm){
        List<Category> categories = categoryService.findAll() ;
        ModelAndView modelAndView = new ModelAndView("admin/product-categories");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject(categoryForm);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView submit(CategoryForm categoryForm, BindingResult bindingResult){
        System.out.println(categoryForm);
        if(bindingResult.hasErrors()){
            return new ModelAndView("admin/product-categories");
        } else {
            categoryService.create(categoryForm);
            return new ModelAndView("redirect:/admin/product-categories");
        }
    }


}
