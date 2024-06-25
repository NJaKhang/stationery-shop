package com.group.sshop.controllers.web;

import com.group.sshop.models.dto.RegisterFrom;
import com.group.sshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView loginView = new ModelAndView("web/login");
        loginView.addObject("title", "Login");
        return loginView;
    }

    @GetMapping("/register")
    public ModelAndView register(RegisterFrom registerFrom) {
        ModelAndView registerView = new ModelAndView("web/register");
        registerView.addObject("title", "Register");
        return registerView;
    }


    @PostMapping("/register")
    public ModelAndView handleRegister(@ModelAttribute @Valid RegisterFrom registerFrom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("web/register");
        } else {
            userService.registerNewUser(registerFrom);
        }

        ModelAndView homeView = new ModelAndView("redirect:/");
        homeView.addObject("title", "Home");
        return homeView;
    }


}
