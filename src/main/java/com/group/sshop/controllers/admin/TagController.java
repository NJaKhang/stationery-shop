package com.group.sshop.controllers.admin;

import com.group.sshop.models.dto.TagForm;
import com.group.sshop.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product-tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ModelAndView index(TagForm tagForm){
        ModelAndView modelAndView = new ModelAndView("admin/product-tags");
        modelAndView.addObject("tags", tagService.findAll());
        return modelAndView;
    }


    @PostMapping
    public ModelAndView submit(TagForm tagForm, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("admin/product-tags");
        } else {
            tagService.create(tagForm);
            return new ModelAndView("redirect:/admin/product-tags");
        }
    }
}
